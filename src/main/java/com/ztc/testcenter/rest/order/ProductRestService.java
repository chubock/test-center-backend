package com.ztc.testcenter.rest.order;

import com.ztc.testcenter.domain.order.Product;
import com.ztc.testcenter.dto.order.ProductDTO;
import com.ztc.testcenter.repository.order.ProductRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 4/3/17.
 */

@RestController
@RequestMapping("/products")
public class ProductRestService {

    private final ProductRepository repository;
    private final ManagerService managerService;

    @Autowired
    public ProductRestService(ProductRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PRODUCT_REST_SERVICE__GET_PRODUCTS')")
    public Page<ProductDTO> getProducts(Pageable pageable) {
        return repository.findAll(pageable).map(ProductDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PRODUCT_REST_SERVICE__GET_PRODUCT')")
    public ProductDTO getProduct(@PathVariable Long id) {
        return ProductDTO.valueOf(repository.findOne(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('PRODUCT_REST_SERVICE__SAVE_PRODUCT')")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        Product product;
        if (productDTO.getId() != null) {
            product = repository.getOne(productDTO.getId());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setCount(productDTO.getCount());
            product.setDiscount(productDTO.getDiscount());
            product.setType(productDTO.getType());
            product.setState(productDTO.getState());
        } else {
            product = new Product(productDTO.getName(), productDTO.getType(), productDTO.getCount(), productDTO.getPrice());
        }
        return ProductDTO.valueOf(managerService.save(product));
    }

}

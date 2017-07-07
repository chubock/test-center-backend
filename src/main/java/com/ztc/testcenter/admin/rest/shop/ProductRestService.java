package com.ztc.testcenter.admin.rest.shop;

import com.ztc.testcenter.shop.domain.Product;
import com.ztc.testcenter.shop.dto.ProductDTO;
import com.ztc.testcenter.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 4/3/17.
 */

@RestController
@RequestMapping("/admin/products")
public class ProductRestService {

    private final ShopService shopService;

    @Autowired
    public ProductRestService(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PRODUCT_REST_SERVICE__GET_PRODUCTS')")
    public Page<ProductDTO> getProducts(Pageable pageable) {
        return shopService.findAllProducts(pageable).map(ProductDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PRODUCT_REST_SERVICE__GET_PRODUCT')")
    public ProductDTO getProduct(@PathVariable Long id) {
        return ProductDTO.valueOf(shopService.findProduct(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('PRODUCT_REST_SERVICE__SAVE_PRODUCT')")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        Product product;
        if (productDTO.getId() != null) {
            product = shopService.getProduct(productDTO.getId());
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
        return ProductDTO.valueOf(shopService.save(product));
    }

}

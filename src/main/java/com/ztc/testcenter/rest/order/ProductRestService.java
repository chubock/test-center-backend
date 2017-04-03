package com.ztc.testcenter.rest.order;

import com.ztc.testcenter.dto.order.ProductDTO;
import com.ztc.testcenter.repository.order.ProductRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Page<ProductDTO> getProducts(Pageable pageable) {
        return repository.findAll(pageable).map(ProductDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductDTO getProduct(@PathVariable Long id) {
        return ProductDTO.valueOf(repository.findOne(id));
    }
}

package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.order.Product;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by yubar on 4/4/17.
 */

@Service
public class ProductsGenerator {

    private final ManagerService managerService;

    @Autowired
    public ProductsGenerator(ManagerService managerService) {
        this.managerService = managerService;
    }

    private void createProduct(String name, Product.Type type, Integer count, BigDecimal price) {
        Product product = new Product(name, type, count, price);
        managerService.save(product);
    }

    public void createProducts() {
        createProduct("Package A", Product.Type.GRE_TEST, 6, BigDecimal.valueOf(200000));
        createProduct("Package B", Product.Type.GRE_TEST, 4, BigDecimal.valueOf(150000));
        createProduct("Package C", Product.Type.GRE_TEST, 2, BigDecimal.valueOf(100000));
    }
}

package com.ztc.testcenter.shop.generator;

import com.ztc.testcenter.shop.domain.Product;
import com.ztc.testcenter.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by yubar on 4/4/17.
 */

@Service
public class ProductsGenerator {

    private final ShopService shopService;

    @Autowired
    public ProductsGenerator(ShopService shopService) {
        this.shopService = shopService;
    }

    private void createProduct(String name, Product.Type type, Integer count, BigDecimal price) {
        Product product = new Product(name, type, count, price);
        shopService.save(product);
    }

    public void createProducts() {
        createProduct("Package A", Product.Type.GRE_TEST, 6, BigDecimal.valueOf(200000));
        createProduct("Package B", Product.Type.GRE_TEST, 4, BigDecimal.valueOf(150000));
        createProduct("Package C", Product.Type.GRE_TEST, 2, BigDecimal.valueOf(100000));
    }
}

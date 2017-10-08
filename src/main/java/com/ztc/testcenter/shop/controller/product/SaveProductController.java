package com.ztc.testcenter.shop.controller.product;

import com.ztc.testcenter.shop.domain.Product;
import com.ztc.testcenter.shop.dto.ProductDTO;
import com.ztc.testcenter.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class SaveProductController {

    private final ShopService shopService;

    @Autowired
    public SaveProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/shop-service/products")
    @PreAuthorize("hasAuthority('SALE')")
    public ProductDTO handle(@RequestBody ProductDTO productDTO) {
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

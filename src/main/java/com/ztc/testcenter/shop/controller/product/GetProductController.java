package com.ztc.testcenter.shop.controller.product;

import com.ztc.testcenter.shop.dto.ProductDTO;
import com.ztc.testcenter.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class GetProductController {

    private final ShopService shopService;

    @Autowired
    public GetProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop-service/products/{id}")
    @PreAuthorize("isAuthenticated()")
    public ProductDTO handle(@PathVariable Long id) {
        return ProductDTO.valueOf(shopService.findProduct(id));
    }


}

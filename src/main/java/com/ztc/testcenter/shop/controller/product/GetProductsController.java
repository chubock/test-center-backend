package com.ztc.testcenter.shop.controller.product;

import com.ztc.testcenter.shop.dto.ProductDTO;
import com.ztc.testcenter.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class GetProductsController {

    private final ShopService shopService;

    @Autowired
    public GetProductsController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop-service/products")
    @PreAuthorize("isAuthenticated()")
    public Page<ProductDTO> handle(Pageable pageable, Authentication authentication) {
        //return shopService.findActiveProducts().stream().map(ProductDTO::valueOf).collect(Collectors.toList());
        return shopService.findAllProducts(pageable).map(ProductDTO::valueOf);
    }
}

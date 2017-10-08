package com.ztc.testcenter.shop.controller.order;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.shop.dto.OrderDTO;
import com.ztc.testcenter.shop.service.ShopService;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.util.SecurityUtil;
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
public class GetOrdersController {

    private final ShopService shopService;

    @Autowired
    public GetOrdersController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop-service/orders")
    @PreAuthorize("isAuthenticated()")
    private Page<OrderDTO> handle(Authentication authentication, Pageable pageable) {
        return shopService.getUserOrders(SecurityUtil.getUsername(authentication), pageable).map(OrderDTO::valueOf);
    }
}

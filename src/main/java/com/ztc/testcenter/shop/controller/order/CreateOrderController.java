package com.ztc.testcenter.shop.controller.order;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.shop.domain.Order;
import com.ztc.testcenter.shop.domain.OrderItem;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class CreateOrderController {

    private final ShopService shopService;

    @Autowired
    public CreateOrderController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/shop-service/orders")
    @PreAuthorize("hasAuthority('STUDENT')")
    public OrderDTO handle(@RequestBody OrderDTO orderDTO, Authentication authentication) {

        Map<Long, Integer> products = new HashMap<>();
        orderDTO.getOrderItems()
                .forEach(item -> products.put(item.getId(), item.getCount()));

        Order order = shopService.createOrder(SecurityUtil.getUsername(authentication), products);

        return OrderDTO.valueOf(order);
    }
}

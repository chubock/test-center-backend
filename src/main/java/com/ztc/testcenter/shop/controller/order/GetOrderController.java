package com.ztc.testcenter.shop.controller.order;

import com.ztc.testcenter.shop.domain.Order;
import com.ztc.testcenter.shop.dto.OrderDTO;
import com.ztc.testcenter.shop.dto.OrderItemDTO;
import com.ztc.testcenter.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class GetOrderController {

    private final ShopService shopService;

    @Autowired
    public GetOrderController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping(value = "/shop-service/orders/{id}")
    @PreAuthorize("isAuthenticated()")
    public OrderDTO handle(@PathVariable Long id) {
        Order order = shopService.getWithProducts(id);
        OrderDTO ret = OrderDTO.valueOf(order);
        order.getOrderItems().forEach(orderItem -> ret.getOrderItems().add(OrderItemDTO.valueOf(orderItem)));
        return ret;
    }
}

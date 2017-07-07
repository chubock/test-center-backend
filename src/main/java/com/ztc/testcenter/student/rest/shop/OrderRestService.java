package com.ztc.testcenter.student.rest.shop;

import com.ztc.testcenter.shop.service.ShopService;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.shop.domain.Order;
import com.ztc.testcenter.shop.domain.OrderItem;
import com.ztc.testcenter.shop.dto.OrderDTO;
import com.ztc.testcenter.shop.dto.OrderItemDTO;
import com.ztc.testcenter.shop.dto.ProductDTO;
import com.ztc.testcenter.config.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yubar on 4/5/17.
 */

@RestController
@RequestMapping("/student/orders")
public class OrderRestService {

    private final ShopService shopService;

    @Autowired
    public OrderRestService(ShopService shopService) {
        this.shopService = shopService;
    }

    private User getUser(Authentication authentication) {
        return ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ORDER_REST_SERVICE__GET_ORDERS', 'ORDER_REST_SERVICE__GET_ORDERS_ALL')")
    private Page<OrderDTO> getOrders(Authentication authentication, Pageable pageable) {
        User user = getUser(authentication);
        return shopService.getUserOrders(user, pageable).map(OrderDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ORDER_REST_SERVICE__GET_ORDER', 'ORDER_REST_SERVICE__GET_ORDER_ALL')")
    public OrderDTO getOrder(@PathVariable Long id) {
        Order order = shopService.getWithProducts(id);
        OrderDTO ret = OrderDTO.valueOf(order);
        order.getOrderItems().forEach(orderItem -> ret.getOrderItems().add(OrderItemDTO.valueOf(orderItem)));
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ORDER_REST_SERVICE__CREATE_ORDER')")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO, Authentication authentication) {
        User user = getUser(authentication);
        Order order = new Order(user);
        orderDTO.getOrderItems().forEach(orderItemDTO -> order.getOrderItems().add(new OrderItem(order, shopService.findProduct(orderItemDTO.getProduct().getId()), orderItemDTO.getCount())));
        return OrderDTO.valueOf(shopService.createOrder(order));
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ORDER_REST_SERVICE__GET_AVAILABLE_PRODUCTS')")
    public List<ProductDTO> getAvailableProducts() {
        return shopService.findActiveProducts().stream().map(ProductDTO::valueOf).collect(Collectors.toList());
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ORDER_REST_SERVICE__GET_PRODUCT')")
    public ProductDTO getProduct(@PathVariable Long id) {
        return ProductDTO.valueOf(shopService.findProduct(id));
    }
}

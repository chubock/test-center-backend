package com.ztc.testcenter.rest.order;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.order.Order;
import com.ztc.testcenter.domain.order.OrderItem;
import com.ztc.testcenter.domain.order.Product;
import com.ztc.testcenter.dto.order.OrderDTO;
import com.ztc.testcenter.dto.order.OrderItemDTO;
import com.ztc.testcenter.dto.order.ProductDTO;
import com.ztc.testcenter.repository.user.UserRepository;
import com.ztc.testcenter.repository.order.OrderRepository;
import com.ztc.testcenter.repository.order.ProductRepository;
import com.ztc.testcenter.security.ApplicationUserDetails;
import com.ztc.testcenter.service.UserService;
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
@RequestMapping("/orders")
public class OrderRestService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public OrderRestService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    private User getUser(Authentication authentication) {
        return ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ORDER_REST_SERVICE__GET_ORDERS', 'ORDER_REST_SERVICE__GET_ORDERS_ALL')")
    private Page<OrderDTO> getOrders(Authentication authentication, Pageable pageable) {
        User user = getUser(authentication);
        return orderRepository.findByUser(user, pageable).map(OrderDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ORDER_REST_SERVICE__GET_ORDER', 'ORDER_REST_SERVICE__GET_ORDER_ALL')")
    public OrderDTO getOrder(@PathVariable Long id) {
        Order order = orderRepository.findWithOrderItems(id);
        OrderDTO ret = OrderDTO.valueOf(order);
        order.getOrderItems().forEach(orderItem -> ret.getOrderItems().add(OrderItemDTO.valueOf(orderItem)));
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ORDER_REST_SERVICE__CREATE_ORDER')")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO, Authentication authentication) {
        User user = getUser(authentication);
        Order order = new Order(user);
        orderDTO.getOrderItems().forEach(orderItemDTO -> order.getOrderItems().add(new OrderItem(order, productRepository.findOne(orderItemDTO.getProduct().getId()), orderItemDTO.getCount())));
        return OrderDTO.valueOf(userService.createOrder(order));
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ORDER_REST_SERVICE__GET_AVAILABLE_PRODUCTS')")
    public List<ProductDTO> getAvailableProducts() {
        return productRepository.findByState(Product.State.ACTIVE).stream().map(ProductDTO::valueOf).collect(Collectors.toList());
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ORDER_REST_SERVICE__GET_PRODUCT')")
    public ProductDTO getProduct(@PathVariable Long id) {
        return ProductDTO.valueOf(productRepository.findOne(id));
    }
}

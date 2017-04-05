package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.order.Order;
import com.ztc.testcenter.repository.order.OrderItemRepository;
import com.ztc.testcenter.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 4/5/17.
 */

@Service
@Transactional
public class UserService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public UserService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}

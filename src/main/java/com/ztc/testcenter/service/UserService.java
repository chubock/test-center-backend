package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.order.Order;
import com.ztc.testcenter.domain.user.ActionCode;
import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.repository.user.ActionCodeRepository;
import com.ztc.testcenter.repository.user.UserRepository;
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

    private final UserRepository userRepository;
    private final ActionCodeRepository actionCodeRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public UserService(UserRepository userRepository, ActionCodeRepository actionCodeRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.actionCodeRepository = actionCodeRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public ActionCode register(User user) {
        user.setEnabled(false);
        user = userRepository.save(user);
        ActionCode actionCode = new ActionCode(user, ActionCode.Action.ACTIVATE_USER);
        actionCodeRepository.save(actionCode);
        return actionCode;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}

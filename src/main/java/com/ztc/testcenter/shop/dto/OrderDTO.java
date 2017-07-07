package com.ztc.testcenter.shop.dto;

import com.ztc.testcenter.shop.domain.Order;
import com.ztc.testcenter.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yubar on 4/3/17.
 */
public class OrderDTO {

    private Long id;
    private Date orderDate = new Date();
    private List<OrderItemDTO> orderItems = new ArrayList<>();
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    private void copy(Order order) {
        setId(order.getId());
        setOrderDate(order.getOrderDate());
    }

    public static OrderDTO valueOf(Order order) {
        if (order == null)
            return null;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.copy(order);
        return orderDTO;
    }
}

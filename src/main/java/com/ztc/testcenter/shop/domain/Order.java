package com.ztc.testcenter.shop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yubar on 4/3/17.
 */

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    private Long id;
    private Date orderDate = new Date();
    private List<OrderItem> orderItems = new ArrayList<>();
    private String username;

    protected Order() {
    }

    public Order(String username) {
        setUsername(username);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NotNull
    public String getUsername() {
        if (username == null)
            throw new IllegalArgumentException();
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }
}

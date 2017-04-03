package com.ztc.testcenter.domain.order;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by yubar on 4/3/17.
 */

@Entity
public class OrderItem implements Serializable {

    private Long id;
    private Order order;
    private Product product;
    private Integer count;

    protected OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer count) {
        this.order = order;
        this.product = product;
        this.count = count;
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

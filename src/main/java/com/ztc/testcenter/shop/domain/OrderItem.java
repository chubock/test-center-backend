package com.ztc.testcenter.shop.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yubar on 4/3/17.
 */

@Entity
public class OrderItem implements Serializable {

    private Long id;
    private Order order;
    private Product product;
    private Integer count;
    private Product.Type productType;
    private Integer productCount;
    private BigDecimal productPrice;
    private Integer productDiscount;

    protected OrderItem() {
    }

    public OrderItem(Order order, Product product, Integer count) {
        setOrder(order);
        setProduct(product);
        setCount(count);
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

    private void setProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException();
        this.product = product;
        setProductType(product.getType());
        setProductCount(product.getCount());
        setProductPrice(product.getPrice());
        setProductDiscount(product.getDiscount());
    }

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count == null || count < 1)
            throw new IllegalArgumentException();
        this.count = count;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Order getOrder() {
        return order;
    }

    private void setOrder(Order order) {
        if (order == null)
            throw new IllegalArgumentException();
        this.order = order;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Product.Type getProductType() {
        return productType;
    }

    private void setProductType(Product.Type productType) {
        if (productType == null)
            throw new IllegalArgumentException();
        this.productType = productType;
    }

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public Integer getProductCount() {
        return productCount;
    }

    private void setProductCount(Integer productCount) {
        if (productCount == null || productCount < 1)
            throw new IllegalArgumentException();
        this.productCount = productCount;
    }

    @NotNull
    @Min(0)
    @Column(nullable = false)
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    private void setProductPrice(BigDecimal productPrice) {
        if (productPrice == null || productPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException();
        this.productPrice = productPrice;
    }

    @NotNull
    @Column(nullable = false)
    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        if (productDiscount == null || productDiscount < 0)
            throw new IllegalArgumentException();
        this.productDiscount = productDiscount;
    }
}

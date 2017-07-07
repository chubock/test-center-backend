package com.ztc.testcenter.shop.dto;

import com.ztc.testcenter.shop.domain.Order;
import com.ztc.testcenter.shop.domain.OrderItem;
import com.ztc.testcenter.shop.domain.Product;

import java.math.BigDecimal;

/**
 * Created by yubar on 4/3/17.
 */

public class OrderItemDTO {

    private Long id;
    private Order order;
    private ProductDTO product;
    private Integer count;
    private Product.Type productType;
    private Integer productCount;
    private BigDecimal productPrice;
    private Integer productDiscount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product.Type getProductType() {
        return productType;
    }

    public void setProductType(Product.Type productType) {
        this.productType = productType;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        this.productDiscount = productDiscount;
    }

    private void copy(OrderItem orderItem) {
        setId(orderItem.getId());
        setCount(orderItem.getCount());
        setProductCount(orderItem.getProductCount());
        setProductPrice(orderItem.getProductPrice());
        setProductDiscount(orderItem.getProductDiscount());
        setProductType(orderItem.getProductType());
    }

    public static OrderItemDTO valueOf(OrderItem orderItem) {
        if (orderItem == null)
            return null;
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.copy(orderItem);
        return orderItemDTO;
    }
}

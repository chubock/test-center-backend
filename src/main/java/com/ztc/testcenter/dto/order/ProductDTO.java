package com.ztc.testcenter.dto.order;

import com.ztc.testcenter.domain.order.Product;

import java.math.BigDecimal;

/**
 * Created by yubar on 4/3/17.
 */
public class ProductDTO {

    private Long id;
    private String name;
    private Product.Type type;
    private String description;
    private Integer count;
    private BigDecimal price;
    private Integer discount = 0;
    private Product.State state = Product.State.ACTIVE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product.Type getType() {
        return type;
    }

    public void setType(Product.Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Product.State getState() {
        return state;
    }

    public void setState(Product.State state) {
        this.state = state;
    }

    private void copy(Product product) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setType(product.getType());
        setCount(product.getCount());
        setPrice(product.getPrice());
        setState(product.getState());
    }

    public static ProductDTO valueOf(Product product) {
        if (product == null)
            return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO.copy(product);
        return productDTO;
    }
}

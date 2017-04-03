package com.ztc.testcenter.domain.order;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yubar on 4/3/17.
 */

@Entity
public class Product implements Serializable {

    private Long id;
    private String name;
    private ProductType productType;
    private String description;
    private Integer count;
    private BigDecimal price;

    protected Product() {
    }

    public Product(String name, ProductType productType, Integer count, BigDecimal price) {
        if (name == null || productType == null || count == null || price == null)
            throw new IllegalArgumentException();
        this.name = name;
        this.productType = productType;
        this.count = count;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    private void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @NotNull
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Min(0)
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull
    @Min(1)
    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    private void setCount(Integer count) {
        this.count = count;
    }

    public enum ProductType {
        GRE_TEST
    }
}

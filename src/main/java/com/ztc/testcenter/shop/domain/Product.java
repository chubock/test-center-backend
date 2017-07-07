package com.ztc.testcenter.shop.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
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
    private Type type;
    private String description;
    private Integer count;
    private BigDecimal price;
    private Integer discount = 0;
    private State state = State.ACTIVE;

    protected Product() {
    }

    public Product(String name, Type type, Integer count, BigDecimal price) {
        setName(name);
        setType(type);
        setCount(count);
        setPrice(price);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if (type == null)
            throw new IllegalArgumentException();
        this.type = type;
    }

    @NotNull
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException();
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

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException();
        this.price = price;
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
    @Min(0)
    @Max(99)
    @Column(nullable = false)
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        if (discount == null || discount < 0 || discount > 99)
            throw new IllegalArgumentException();
        this.discount = discount;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (state == null)
            throw new IllegalArgumentException();
        this.state = state;
    }

    public enum Type {
        GRE_TEST
    }
    
    public enum State {
        ACTIVE,
        DEACTIVE
    }
}

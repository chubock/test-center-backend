package com.ztc.testcenter.dto.order;

import com.ztc.testcenter.domain.order.Product;

import java.math.BigDecimal;

/**
 * Created by yubar on 4/3/17.
 */
public class ProductDTO {

    private Long id;
    private String name;
    private Product.ProductType productType;
    private String description;
    private Integer count;
    private BigDecimal price;

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

    public Product.ProductType getProductType() {
        return productType;
    }

    public void setProductType(Product.ProductType productType) {
        this.productType = productType;
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

    private void copy(Product product) {
        setId(product.getId());
        setName(product.getName());
        setDescription(product.getDescription());
        setProductType(product.getProductType());
        setCount(product.getCount());
        setPrice(product.getPrice());
    }

    public static ProductDTO valueOf(Product product) {
        if (product == null)
            return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO.copy(product);
        return productDTO;
    }
}

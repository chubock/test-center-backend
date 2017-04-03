package com.ztc.testcenter.dto.order;

import com.ztc.testcenter.domain.order.OrderItem;

/**
 * Created by yubar on 4/3/17.
 */

public class OrderItemDTO {

    private Long id;
//    private Order order;
    private ProductDTO product;
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    private void copy(OrderItem orderItem) {
        setId(orderItem.getId());
        setCount(orderItem.getCount());
    }

    public static OrderItemDTO valueOf(OrderItem orderItem) {
        if (orderItem == null)
            return null;
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.copy(orderItem);
        return orderItemDTO;
    }
}

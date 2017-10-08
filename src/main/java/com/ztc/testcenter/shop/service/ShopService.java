package com.ztc.testcenter.shop.service;

import com.ztc.testcenter.shop.domain.Order;
import com.ztc.testcenter.shop.domain.OrderItem;
import com.ztc.testcenter.shop.domain.Product;
import com.ztc.testcenter.shop.repository.OrderRepository;
import com.ztc.testcenter.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by yubar on 7/3/17.
 */

@Service
@Transactional
public class ShopService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShopService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(String username, Map<Long, Integer> products) {

        Order order = new Order(username);
        products.keySet()
                .forEach(productId -> order.getOrderItems().add(new OrderItem(order,
                        productRepository.getOne(productId),
                        products.get(productId)))
                );

        return orderRepository.save(order);
    }

    public Order getWithProducts(Long id) {
        return orderRepository.findWithOrderItems(id);
    }

    public Page<Order> getUserOrders(String username, Pageable pageable) {
        return orderRepository.findByUsername(username, pageable);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findProduct(Long id) {
        return productRepository.findOne(id);
    }

    public Product getProduct(Long id) {
        return productRepository.getOne(id);
    }

    public List<Product> findActiveProducts() {
        return productRepository.findByState(Product.State.ACTIVE);
    }

    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}

package com.ztc.testcenter.repository.order;

import com.ztc.testcenter.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 4/3/17.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

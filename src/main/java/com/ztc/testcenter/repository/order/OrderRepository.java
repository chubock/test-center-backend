package com.ztc.testcenter.repository.order;

import com.ztc.testcenter.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 4/3/17.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}

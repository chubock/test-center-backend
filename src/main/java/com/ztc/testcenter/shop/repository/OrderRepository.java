package com.ztc.testcenter.shop.repository;

import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.shop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by yubar on 4/3/17.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.orderItems where o.id = :id")
    Order findWithOrderItems(@Param("id") Long id);

    Page<Order> findByUsername(String user, Pageable pageable);

}

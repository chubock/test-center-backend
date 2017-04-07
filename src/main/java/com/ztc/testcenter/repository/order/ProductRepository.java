package com.ztc.testcenter.repository.order;

import com.ztc.testcenter.domain.order.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yubar on 4/3/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByState(Product.State state);

}

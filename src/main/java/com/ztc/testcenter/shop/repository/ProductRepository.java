package com.ztc.testcenter.shop.repository;

import com.ztc.testcenter.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yubar on 4/3/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByState(Product.State state);

}

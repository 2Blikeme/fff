package com.mysite.demo.repo;

import com.mysite.demo.entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<cart, Integer> {
    void deleteById(int id);
}

package com.mysite.demo.repo;

import com.mysite.demo.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<product, Integer> {
    product findById(int id);
    product findByName(String name);
}

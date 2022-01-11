package com.mysite.demo.repo;

import com.mysite.demo.entity.playstation_preorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaystationPreorderRepository extends JpaRepository<playstation_preorder, Integer> {
}

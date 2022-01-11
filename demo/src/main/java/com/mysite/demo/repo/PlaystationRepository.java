package com.mysite.demo.repo;

import com.mysite.demo.entity.playstation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaystationRepository extends JpaRepository<playstation, Integer> {
    playstation findById(int id);
    playstation findByName(String name);
}

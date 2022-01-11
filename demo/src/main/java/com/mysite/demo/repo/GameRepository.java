package com.mysite.demo.repo;

import com.mysite.demo.entity.game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<game, Integer> {
    game findByName(String name);
    game findById(int id);
}

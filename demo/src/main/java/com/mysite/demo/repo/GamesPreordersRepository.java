package com.mysite.demo.repo;

import com.mysite.demo.entity.games_preorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesPreordersRepository extends JpaRepository<games_preorder, Integer> {
}

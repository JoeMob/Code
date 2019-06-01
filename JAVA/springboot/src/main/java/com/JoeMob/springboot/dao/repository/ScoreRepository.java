package com.JoeMob.springboot.dao.repository;

import com.JoeMob.springboot.dao.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    public List<ScoreEntity> findById(long i);
}

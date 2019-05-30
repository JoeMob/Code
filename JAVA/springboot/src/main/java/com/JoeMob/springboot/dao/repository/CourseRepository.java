package com.JoeMob.springboot.dao.repository;

import com.JoeMob.springboot.dao.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    public List<CourseEntity> findById(long i);
}

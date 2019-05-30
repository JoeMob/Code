package com.JoeMob.springboot.dao.repository;

import com.JoeMob.springboot.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    public List<StudentEntity> findById(long i);
}

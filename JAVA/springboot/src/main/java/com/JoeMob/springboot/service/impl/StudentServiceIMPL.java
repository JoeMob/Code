package com.JoeMob.springboot.service.impl;

import com.JoeMob.springboot.controller.bean.StudentBean;
import com.JoeMob.springboot.dao.entity.StudentEntity;
import com.JoeMob.springboot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JoeMob.springboot.dao.repository.StudentRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceIMPL implements StudentService {
    @Autowired
    private StudentRepository entityRepo;

    @Override
    public List<StudentBean> findAll() {
        List<StudentEntity> entities = this.entityRepo.findAll();
        List<StudentBean> students = entities.stream().map(entity -> StudentBean.of(entity)).collect(Collectors.toList());
        return students;
    }

    @Override
    public StudentBean create(StudentBean studentBean) {
        StudentEntity entity = StudentBean.of(studentBean);
        entity.setCreatedTime(new Date());
        entity.setUpdatedTime(new Date());
        entity = this.entityRepo.save(entity);
        StudentBean bean = StudentBean.of(entity);
        return bean;
    }

    @Override
    public boolean delete(Long id) {
        this.entityRepo.deleteById(id);
        return true;
    }

    @Override
    public StudentBean edit(StudentBean bean) {
        StudentEntity entity = this.entityRepo.findById(bean.getId()).get();
        entity.setStudentId(bean.getStudentId());
        entity.setStudentName(bean.getStudentName());
        entity.setGender(bean.getGender());
        entity.setAge(bean.getAge());
        entity.setUpdatedTime(new Date());
        this.entityRepo.save(entity);
        StudentBean editedBean = StudentBean.of(entity);
        return editedBean;
    }

    @Override
    public StudentBean findById(Long id) {
        StudentEntity entity = this.entityRepo.findById(id).get();
        StudentBean bean = StudentBean.of(entity);
        return bean;
    }
}

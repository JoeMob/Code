package com.JoeMob.springboot.service;

import com.JoeMob.springboot.controller.bean.StudentBean;

import java.util.List;

public interface StudentService {
    public List<StudentBean> findAll();

    public StudentBean create(StudentBean studentBean);

    public boolean delete(Long id);

    public StudentBean edit(StudentBean studentBean);

    public StudentBean findById(Long id);
}

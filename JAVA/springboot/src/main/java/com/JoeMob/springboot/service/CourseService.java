package com.JoeMob.springboot.service;


import com.JoeMob.springboot.controller.bean.CourseBean;

import java.util.List;

public interface CourseService {
    public List<CourseBean> findAll();

    public CourseBean create(CourseBean courseBean);

    public boolean delete(CourseBean courseBean);

    public CourseBean edit(CourseBean courseBean);

    public CourseBean findById(CourseBean courseBean);
}

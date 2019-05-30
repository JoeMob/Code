package com.JoeMob.springboot.controller.bean;

import com.JoeMob.springboot.dao.entity.CourseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class CourseBean {
    private long ID;
    private int CourseID;
    private String CourseName;
    private String Teacher;
    private int Lesson;
    private Date CreatedTime;
    private Date UpdatedTime;

    public static CourseEntity of(CourseBean bean) {
        CourseEntity entity = new CourseEntity();
        entity.setId(bean.getID());
        entity.setCourseID(bean.getCourseID());
        entity.setCourseName(bean.getCourseName());
        entity.setTeacher(bean.getTeacher());
        entity.setLesson(bean.getLesson());
        entity.setCreatedTime(bean.getCreatedTime());
        entity.setUpdatedTime(bean.getUpdatedTime());
        return entity;
    }

    public static CourseBean of(CourseEntity entity) {
        CourseBean bean = new CourseBean();
        bean.setID(entity.getId());
        bean.setCourseID(entity.getCourseID());
        bean.setCourseName(entity.getCourseName());
        bean.setTeacher(entity.getTeacher());
        bean.setLesson(entity.getLesson());
        bean.setCreatedTime(entity.getCreatedTime());
        bean.setUpdatedTime(entity.getUpdatedTime());
        return bean;
    }
}

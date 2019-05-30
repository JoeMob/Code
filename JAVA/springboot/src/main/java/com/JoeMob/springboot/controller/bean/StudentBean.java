package com.JoeMob.springboot.controller.bean;

import com.JoeMob.springboot.dao.entity.StudentEntity;
import lombok.Data;

import java.util.Date;

@Data
public class StudentBean {
    private Long id;
    private Integer studentId;
    private String studentName;
    private Integer age;
    private String gender;
    private Date createdTime;
    private Date updatedTime;

    public static StudentEntity of(StudentBean bean) {
        StudentEntity entity = new StudentEntity();
        entity.setId(bean.getId());
        entity.setStudentId(bean.getStudentId());
        entity.setStudentName(bean.getStudentName());
        entity.setAge(bean.getAge());
        entity.setGender(bean.getGender());
        entity.setCreatedTime(bean.getCreatedTime());
        entity.setUpdatedTime(bean.getUpdatedTime());
        return entity;
    }

    public static StudentBean of(StudentEntity entity) {
        StudentBean bean = new StudentBean();
        bean.setId(entity.getId());
        bean.setStudentName(entity.getStudentName());
        bean.setStudentId(entity.getStudentId());
        bean.setAge(entity.getAge());
        bean.setGender(entity.getGender());
        bean.setCreatedTime(entity.getCreatedTime());
        bean.setUpdatedTime(entity.getUpdatedTime());
        return bean;
    }
}

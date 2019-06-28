package com.JoeMob.springboot.controller.bean;

import com.JoeMob.springboot.dao.entity.StudentEntity;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class StudentBean {
    private Long id;
    private Long studentId;
    private String studentName;
    private Integer age;
    private String gender;
    private String createdTime;
    private String updatedTime;
    private Date createdTimeDate;
    private Date updatedTimeDate;

    public static StudentEntity of(StudentBean bean) {
        DateFormat simpleDate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StudentEntity entity = new StudentEntity();
        entity.setId(bean.getId());
        entity.setStudentId(bean.getStudentId());
        entity.setStudentName(bean.getStudentName());
        entity.setAge(bean.getAge());
        entity.setGender(bean.getGender());
        entity.setCreatedTime(bean.getCreatedTimeDate());
        entity.setUpdatedTime(bean.getUpdatedTimeDate());
        return entity;
    }

    public static StudentBean of(StudentEntity entity) {
        DateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StudentBean bean = new StudentBean();
        bean.setId(entity.getId());
        bean.setStudentName(entity.getStudentName());
        bean.setStudentId(entity.getStudentId());
        bean.setAge(entity.getAge());
        bean.setGender(entity.getGender());
        bean.setCreatedTime(simpleDate.format(entity.getCreatedTime()));
        bean.setUpdatedTime(simpleDate.format(entity.getUpdatedTime()));
        bean.setCreatedTimeDate(entity.getCreatedTime());
        bean.setUpdatedTimeDate(entity.getUpdatedTime());
        return bean;
    }
}

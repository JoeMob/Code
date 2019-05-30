package com.JoeMob.springboot.controller.bean;

import com.JoeMob.springboot.dao.entity.ScoreEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ScoreBean {
    private long ID;
    private int StudentID;
    private int CourseID;
    private String CourseName;
    private int Score;
    private Date CreatedTime;
    private Date UpdatedTime;

    public static ScoreEntity of(ScoreBean bean) {
        ScoreEntity entity = new ScoreEntity();
        entity.setId(bean.getID());
        entity.setStudentID(bean.getStudentID());
        entity.setCourseID(bean.getCourseID());
        entity.setCourseName(bean.getCourseName());
        entity.setScore(bean.getScore());
        entity.setCreatedTime(bean.getCreatedTime());
        entity.setUpdatedTime(bean.getUpdatedTime());
        return entity;
    }

    public static ScoreBean of(ScoreEntity entity) {
        ScoreBean bean = new ScoreBean();
        bean.setID(entity.getId());
        bean.setStudentID(entity.getStudentID());
        bean.setCourseID(entity.getCourseID());
        bean.setCourseName(entity.getCourseName());
        bean.setScore(entity.getScore());
        bean.setCreatedTime(entity.getCreatedTime());
        bean.setUpdatedTime(entity.getUpdatedTime());
        return bean;
    }
}

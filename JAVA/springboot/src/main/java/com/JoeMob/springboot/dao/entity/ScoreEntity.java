package com.JoeMob.springboot.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Score")
@Entity
@Data
public class ScoreEntity extends BaseEntity implements java.io.Serializable {
    @Column(name = "course_id")
    private int CourseID;
    @Column(name = "student_id")
    private int StudentID;
    @Column(name = "course_name")
    private String CourseName;
    @Column(name = "score")
    private int Score;
}

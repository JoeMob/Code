package com.JoeMob.springboot.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Course")
@Entity
@Data
public class CourseEntity extends BaseEntity implements java.io.Serializable {
    @Column(name = "courser_id")
    private int CourseID;
    @Column(name = "course_name")
    private String CourseName;
    @Column(name = "teacher")
    private String Teacher;
    @Column(name = "lesson")
    private int Lesson;
}

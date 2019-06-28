package com.JoeMob.springboot.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Table(name = "Student")
@Entity
@Data
public class StudentEntity extends BaseEntity implements java.io.Serializable {
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gender")
    private String gender;
}

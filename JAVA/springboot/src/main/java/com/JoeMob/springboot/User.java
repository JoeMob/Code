package com.JoeMob.springboot;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    User() {
    }

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    int getAge() {
        return this.age;
    }

    String getName() {
        return this.name;
    }
}
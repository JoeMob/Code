package com.JoeMob.springboot.controller;

import com.JoeMob.springboot.controller.bean.StudentBean;
import com.JoeMob.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

//    @PostMapping("/test")
//    public void test(@RequestBody StudentEntity studentEntity){
//        System.out.println(studentEntity);
//
//    }


    @GetMapping(value = "/student")
    public List<StudentBean> StudentQry() {
        return studentService.findAll();
    }

    @PostMapping("/student")
    public void StudentAdd(@RequestBody StudentBean student) {
        studentService.create(student);
    }

    @PutMapping("/student/id")
    public void StudentUpdate(@RequestBody StudentBean student) {
        studentService.edit(student);
    }

    @DeleteMapping("/student/id")
    public void StudentDel(@RequestBody StudentBean student) {
        studentService.delete(student.getId());
    }

    @GetMapping(value = "/student/id/")
    public StudentBean StudentSearch(@RequestParam("id") Long id) {
        return studentService.findById(id);
    }
}

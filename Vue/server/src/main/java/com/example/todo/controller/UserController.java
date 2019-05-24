package com.example.todo.controller;


import com.example.todo.model.User;
import com.example.todo.model.UserForm;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/v1")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 增加
     * @param user
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 改
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public int updateTodo(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * 删除
     * @param user
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.DELETE)
    public int deleteTodo(@RequestBody User user) {
        return userService.delete(user);
    }

    /**
     * 得到所有用户
     * @return
     */
    /*
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public List<User> getTodos() {
        return userService.getAll();
    }
    */

    /**
     * 登录
     * @param userName
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public boolean login(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword) {
        UserForm userForm=new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return userService.isUserExist(userForm);
    }


}

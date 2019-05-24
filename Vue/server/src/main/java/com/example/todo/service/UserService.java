package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.model.UserForm;

import java.util.List;

public interface UserService {
    int add(User user);

    int delete(User user);

    int update(User user);

    List<User> getAll();

    int getCount();

    boolean isUserExist(UserForm userForm);

    User getUser(UserForm userForm);
}

package com.example.todo.service.impl;


import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.model.UserForm;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoService;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public int add(User user) {
        return userRepository.add(user);
    }

    @Override
    public int delete(User user) {
        return userRepository.delete(user);
    }

    @Override
    public int update(User user) {
        return userRepository.update(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public int getCount() {
        return userRepository.getCount();
    }

    @Override
    public boolean isUserExist(UserForm userForm) {
        return userRepository.isUserExist(userForm);
    }

    @Override
    public User getUser(UserForm userForm) {
        return userRepository.getUser(userForm);
    }


}

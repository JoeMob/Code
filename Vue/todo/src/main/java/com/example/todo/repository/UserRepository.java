package com.example.todo.repository;


import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.model.UserForm;

import java.util.List;

public interface UserRepository {
    int add(User user);

    int delete(User user);

    int update(User user);

    List<User> getAll();

    int getCount();

    boolean isUserExist(UserForm userForm);
    User getUser(UserForm userForm);


}

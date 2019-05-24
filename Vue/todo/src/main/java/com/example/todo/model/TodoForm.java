package com.example.todo.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TodoForm {
    private User user;
    private Todo todo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}

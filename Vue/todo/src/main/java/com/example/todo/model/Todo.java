package com.example.todo.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Todo {
    private String id;
    private String userId;
    private String title;
    private String content;
    private Date beginDate;
    private Date deadline;
    private int property;
    private int complete;
    private String completeDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }


    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public String getCompleteDesc() {
        return completeDesc;
    }

    public void setCompleteDesc(String completeDesc) {
        this.completeDesc = completeDesc;
    }
}

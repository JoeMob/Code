package com.example.todo.service;


import com.example.todo.model.Todo;
import com.example.todo.model.TodoForm;
import com.example.todo.model.UserForm;

import java.util.Date;
import java.util.List;

public interface TodoService {

    int add(TodoForm todoForm);



    int deleteById(UserForm userForm,String todo_id);

    /**
     * @param userForm
     * @return
     */
    int deleteByUser(UserForm userForm);


    /**
     * @param todoForm
     * @return
     */
    int update(TodoForm todoForm);

    /**
     * 更新标题
     * @param todo_title
     * @param todo_id
     * @return
     */
    int updateTitle(UserForm userForm,String todo_title, String todo_id);
    int updateTitle(TodoForm todoForm);

    /**更新内容
     * @param todo_content
     * @param todo_id
     * @return
     */
    int updateContent(UserForm userForm,String todo_content, String todo_id);
    int updateContent(TodoForm todoForm);

    /**更新任务开始时间
     * @param todo_begin_datetime
     * @param todo_id
     * @return
     */
    int updateBeginDate(UserForm userForm,Date todo_begin_datetime, String todo_id);
    int updateBeginDate(TodoForm todoForm);

    /**更新截止日期
     * @param todo_deadline
     * @param todo_id
     * @return
     */
    int updateDeadline(UserForm userForm,Date todo_deadline, String todo_id);
    int updateDeadline(TodoForm todoForm);

    /**
     * @param todo_property
     * @param todo_id
     * @return
     */
    int updateProperty(UserForm userForm,int todo_property, String todo_id);
    int updateProperty(TodoForm todoForm);

    /**
     * @param todo_complete
     * @param todo_id
     * @return
     */
    int updateComplete(UserForm userForm,int todo_complete, String todo_id);
    int updateComplete(TodoForm todoForm);

    /**
     * @param todo_complete_desc
     * @param todo_id
     * @return
     */
    int updateCompleteDesc(UserForm userForm,String todo_complete_desc, String todo_id);
    int updateCompleteDesc(TodoForm todoForm);

    /**
     * @param userForm
     * @return
     */
    List<Todo> getAll(UserForm userForm);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param end
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getAll(UserForm userForm,Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**
     * 根据条件获得用户的Todos
     * @param userForm
     * @param title
     * @return
     */
    List<Todo> getTodosByTitle(UserForm userForm, String title);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @return
     */
    List<Todo> getTodosByDate(UserForm userForm, Date begin);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param end
     * @return
     */
    List<Todo> getTodosByDate(UserForm userForm, Date begin, Date end);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_deadline
     * @return
     */
    List<Todo> getTodosByDeadline(UserForm userForm, Date todo_deadline);

    /**
     * @param userForm
     * @param todo_deadline
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    List<Todo> getTodosByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_deadline
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_deadline
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getTodosByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete_begin, int todo_complete_end);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getTodosByDeadline(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByProperty(UserForm userForm, int todo_property);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByProperty(UserForm userForm, Date begin, int todo_property);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param end
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByProperty(UserForm userForm, Date begin, Date end, int todo_property);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param end
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    List<Todo> getTodosByProperty(UserForm userForm, Date begin, Date end, int todo_property_begin, int todo_property_end);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByComplete(UserForm userForm, int todo_complete);

    /**
     * @param userForm
     * @param begin
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByComplete(UserForm userForm, Date begin, int todo_complete);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param end
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByComplete(UserForm userForm, Date begin, Date end, int todo_complete);

    /**根据条件获得用户的Todos
     * @param userForm
     * @param begin
     * @param end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getTodosByComplete(UserForm userForm, Date begin, Date end, int todo_complete_begin, int todo_complete_end);


    /**求指定用户的todo条数
     * @param userForm
     * @return
     */
    int getCount(UserForm userForm);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param end
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCount(UserForm userForm, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**
     * 根据条件求指定用户的todo条数
     * @param userForm
     * @param title
     * @return
     */
    int getCountByTitle(UserForm userForm, String title);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @return
     */
    int getCountByDate(UserForm userForm, Date begin);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param end
     * @return
     */
    int getCountByDate(UserForm userForm, Date begin, Date end);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_deadline
     * @return
     */
    int getCountByDeadline(UserForm userForm, Date todo_deadline);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_deadline
     * @param todo_property
     * @return
     */
    int getCountByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    int getCountByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_deadline
     * @param todo_complete
     * @return
     */
    int getCountByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_deadline
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCountByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete_begin, int todo_complete_end);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCountByDeadline(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_property
     * @return
     */
    int getCountByProperty(UserForm userForm, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param todo_property
     * @return
     */
    int getCountByProperty(UserForm userForm, Date begin, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param end
     * @param todo_property
     * @return
     */
    int getCountByProperty(UserForm userForm, Date begin, Date end, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param end
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    int getCountByProperty(UserForm userForm, Date begin, Date end, int todo_property_begin, int todo_property_end);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param todo_complete
     * @return
     */
    int getCountByComplete(UserForm userForm, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param todo_complete
     * @return
     */
    int getCountByComplete(UserForm userForm, Date begin, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param end
     * @param todo_complete
     * @return
     */
    int getCountByComplete(UserForm userForm, Date begin, Date end, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param userForm
     * @param begin
     * @param end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCountByComplete(UserForm userForm, Date begin, Date end, int todo_complete_begin, int todo_complete_end);


}

package com.example.todo.repository;


import com.example.todo.model.Todo;

import java.util.Date;
import java.util.List;

public interface TodoRepository {
    /**
     * @param todo
     * @return
     */
    int add(Todo todo);

    /**
     * @param todo
     * @return
     */
    int delete(Todo todo);

    /**
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * @param t_user_id
     * @return
     */
    int deleteByUserId(String t_user_id);

    /**
     * @return
     */
    int deleteAll();

    /**
     * @param todo
     * @return
     */
    int update(Todo todo);

    /**
     *
     * @param todo_title
     * @param id
     * @return
     */
    int updateTitle(String todo_title, String id);

    /**
     * @param todo_content
     * @param id
     * @return
     */
    int updateContent(String todo_content, String id);

    /**
     * @param todo_begin_datetime
     * @param id
     * @return
     */
    int updateBeginDate(Date todo_begin_datetime, String id);

    /**
     * @param todo_deadline
     * @param id
     * @return
     */
    int updateDeadline(Date todo_deadline, String id);

    /**
     * @param todo_property
     * @param id
     * @return
     */
    int updateProperty(int todo_property, String id);

    /**
     * @param todo_complete
     * @param id
     * @return
     */
    int updateComplete(int todo_complete, String id);

    /**
     * @param todo_complete_desc
     * @param id
     * @return
     */
    int updateCompleteDesc(String todo_complete_desc, String id);

    /**
     * 指定id的todo是否属于t_user_id用户
     * @param id
     * @param t_user_id
     * @return
     */
    boolean isCorrectUser(String id,String t_user_id);


    /**获得用户所有的的Todos
     * @param t_user_id
     * @return
     */
    List<Todo> getAll(String t_user_id);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getAll(String t_user_id, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**
     * 根据条件获得用户的Todos
     * @param t_user_id
     * @param title
     * @return
     */
    List<Todo> getTodosByTitle(String t_user_id, String title);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @return
     */
    List<Todo> getTodosByDate(String t_user_id, Date begin);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param end
     * @return
     */
    List<Todo> getTodosByDate(String t_user_id, Date begin, Date end);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_deadline
     * @return
     */
    List<Todo> getTodosByDeadline(String t_user_id, Date todo_deadline);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_deadline
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    List<Todo> getTodosByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_deadline
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_deadline
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getTodosByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete_begin, int todo_complete_end);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getTodosByDeadline(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByProperty(String t_user_id, int todo_property);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByProperty(String t_user_id, Date begin, int todo_property);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_property
     * @return
     */
    List<Todo> getTodosByProperty(String t_user_id, Date begin, Date end, int todo_property);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    List<Todo> getTodosByProperty(String t_user_id, Date begin, Date end, int todo_property_begin, int todo_property_end);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByComplete(String t_user_id, int todo_complete);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByComplete(String t_user_id, Date begin, int todo_complete);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_complete
     * @return
     */
    List<Todo> getTodosByComplete(String t_user_id, Date begin, Date end, int todo_complete);

    /**根据条件获得用户的Todos
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    List<Todo> getTodosByComplete(String t_user_id, Date begin, Date end, int todo_complete_begin, int todo_complete_end);


    /**求指定用户的todo条数
     * @param t_user_id
     * @return
     */
    int getCount(String t_user_id);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCount(String t_user_id, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**
     * 根据条件求指定用户的todo条数
     * @param t_user_id
     * @param title
     * @return
     */
    int getCountByTitle(String t_user_id, String title);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @return
     */
    int getCountByDate(String t_user_id, Date begin);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param end
     * @return
     */
    int getCountByDate(String t_user_id, Date begin, Date end);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_deadline
     * @return
     */
    int getCountByDeadline(String t_user_id, Date todo_deadline);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_deadline
     * @param todo_property
     * @return
     */
    int getCountByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    int getCountByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_deadline
     * @param todo_complete
     * @return
     */
    int getCountByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_deadline
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCountByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete_begin, int todo_complete_end);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCountByDeadline(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_property
     * @return
     */
    int getCountByProperty(String t_user_id, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param todo_property
     * @return
     */
    int getCountByProperty(String t_user_id, Date begin, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_property
     * @return
     */
    int getCountByProperty(String t_user_id, Date begin, Date end, int todo_property);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    int getCountByProperty(String t_user_id, Date begin, Date end, int todo_property_begin, int todo_property_end);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param todo_complete
     * @return
     */
    int getCountByComplete(String t_user_id, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param todo_complete
     * @return
     */
    int getCountByComplete(String t_user_id, Date begin, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_complete
     * @return
     */
    int getCountByComplete(String t_user_id, Date begin, Date end, int todo_complete);

    /**根据条件求指定用户的todo条数
     * @param t_user_id
     * @param begin
     * @param end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    int getCountByComplete(String t_user_id, Date begin, Date end, int todo_complete_begin, int todo_complete_end);


}

package com.example.todo.controller;


import com.example.todo.model.Todo;
import com.example.todo.model.TodoForm;
import com.example.todo.model.UserForm;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("todo/v1")
public class TodoController {
    @Autowired
    TodoService todoService;

    /**
     * 增加
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo", method = RequestMethod.POST)
    public int addTodo(@RequestBody TodoForm todoForm) {

        return todoService.add(todoForm);
    }


    /**
     * 删除用户指定的todo
     */
    @RequestMapping(value = "todo/{id}", method = RequestMethod.DELETE)
    public int deleteTodoById(@RequestBody UserForm userForm, @PathVariable("id") String id) {
        return todoService.deleteById(userForm, id);
    }

    /**
     * 删除指定用户的所有todo
     *
     * @param userForm
     */
    @RequestMapping(value = "todo", method = RequestMethod.DELETE)
    public int deleteTodo(@RequestBody UserForm userForm) {
        return todoService.deleteByUser(userForm);
    }


    /**
     * 修改
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo", method = RequestMethod.PUT)
    public int updateTodo(@RequestBody TodoForm todoForm) {
        return todoService.update(todoForm);
    }

    /**
     * 根据id修改开始日期
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/begindate", method = RequestMethod.PUT)
    public int updateBeginDate(@RequestBody TodoForm todoForm) {
        return todoService.updateBeginDate(todoForm);
    }

    /**
     * 根据id修改截止日期
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/deadline", method = RequestMethod.PUT)
    public int updateDeadline(@RequestBody TodoForm todoForm) {
        return todoService.updateDeadline(todoForm);
    }

    /**
     * 根据id修改标题
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/title", method = RequestMethod.PUT)
    public int updateTitle(@RequestBody TodoForm todoForm) {
        return todoService.updateTitle(todoForm);
    }

    /**
     * 根据id修改内容
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/content", method = RequestMethod.PUT)
    public int updateContent(@RequestBody TodoForm todoForm) {
        return todoService.updateContent(todoForm);
    }

    /**
     * 根据id修改属性
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/property", method = RequestMethod.PUT)
    public int updateProperty(@RequestBody TodoForm todoForm) {
        return todoService.updateProperty(todoForm);
    }

    /**
     * 根据id修改完成情况
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/complete", method = RequestMethod.PUT)
    public int updateComplete(@RequestBody TodoForm todoForm) {
        return todoService.updateComplete(todoForm);
    }

    /**
     * 根据id修改完成情况描述
     *
     * @param todoForm
     */
    @RequestMapping(value = "todo/completesecs", method = RequestMethod.PUT)
    public int updateCompleteDesc(@RequestBody TodoForm todoForm) {
        return todoService.updateCompleteDesc(todoForm);
    }

    /**
     * 根据条件获得用户的Todos
     * 得到指定用户的所有todo
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "todos", method = RequestMethod.GET)
    public List<Todo> getTodos(@RequestParam("userName") String userName,
                               @RequestParam("userPassword") String userPassword) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getAll(userForm);
    }


    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "todos/all", method = RequestMethod.GET)
    List<Todo> getAll(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                      @RequestParam("begin") Date begin, @RequestParam("end") Date end,
                      @RequestParam("todo_deadline") Date todo_deadline,
                      @RequestParam("todo_property_begin") int todo_property_begin,
                      @RequestParam("todo_property_end") int todo_property_end,
                      @RequestParam("todo_complete_begin") int todo_complete_begin,
                      @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getAll(userForm, begin, end, todo_deadline, todo_property_begin, todo_property_end,
                                  todo_complete_begin, todo_complete_end);
    }

    @RequestMapping(value = "todos/title", method = RequestMethod.GET)
    List<Todo> getTodosByTitle(@RequestParam("userName") String userName,
                               @RequestParam("userPassword") String userPassword,
                               @RequestParam("todo_title") String todo_title) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByTitle(userForm, todo_title);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @return
     */
    @RequestMapping(value = "todos/date", params = {"begin"}, method = RequestMethod.GET)
    List<Todo> getTodosByDate(@RequestParam("userName") String userName,
                              @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDate(userForm, begin);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @return
     */
    @RequestMapping(value = "todos/date", params = {"begin", "end"}, method = RequestMethod.GET)
    List<Todo> getTodosByDate(@RequestParam("userName") String userName,
                              @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                              @RequestParam("end") Date end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDate(userForm, begin, end);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @return
     */
    @RequestMapping(value = "todos/deadline", params = {"todo_deadline"}, method = RequestMethod.GET)
    List<Todo> getTodosByDeadline(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword,
                                  @RequestParam("todo_deadline") Date todo_deadline) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDeadline(userForm, todo_deadline);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "todos/deadline", params = {"todo_deadline", "todo_property"}, method = RequestMethod.GET)
    List<Todo> getTodosByDeadlineProperty(@RequestParam("userName") String userName,
                                          @RequestParam("userPassword") String userPassword,
                                          @RequestParam("todo_deadline") Date todo_deadline,
                                          @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDeadlineProperty(userForm, todo_deadline, todo_property);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    @RequestMapping(value = "todos/deadline", params = {"todo_deadline", "todo_property_begin", "todo_property_end"}, method = RequestMethod.GET)
    List<Todo> getTodosByDeadlineProperty(@RequestParam("userName") String userName,
                                          @RequestParam("userPassword") String userPassword,
                                          @RequestParam("todo_deadline") Date todo_deadline,
                                          @RequestParam("todo_property_begin") int todo_property_begin,
                                          @RequestParam("todo_property_end") int todo_property_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDeadlineProperty(userForm, todo_deadline, todo_property_begin, todo_property_end);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "todos/deadline", params = {"todo_deadline", "todo_complete"}, method = RequestMethod.GET)
    List<Todo> getTodosByDeadlineComplete(@RequestParam("userName") String userName,
                                          @RequestParam("userPassword") String userPassword,
                                          @RequestParam("todo_deadline") Date todo_deadline,
                                          @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDeadlineComplete(userForm, todo_deadline, todo_complete);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "todos/deadline", params = {"todo_deadline", "todo_complete_begin", "todo_complete_end"}, method = RequestMethod.GET)
    List<Todo> getTodosByDeadlineComplete(@RequestParam("userName") String userName,
                                          @RequestParam("userPassword") String userPassword,
                                          @RequestParam("todo_deadline") Date todo_deadline,
                                          @RequestParam("todo_complete_begin") int todo_complete_begin,
                                          @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDeadlineComplete(userForm, todo_deadline, todo_complete_begin, todo_complete_end);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "todos/deadline", params = {"todo_deadline", "todo_property_begin", "todo_property_end", "todo_complete_begin", "todo_complete_end"}, method = RequestMethod.GET)
    List<Todo> getTodosByDeadline(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword,
                                  @RequestParam("todo_deadline") Date todo_deadline,
                                  @RequestParam("todo_property_begin") int todo_property_begin,
                                  @RequestParam("todo_property_end") int todo_property_end,
                                  @RequestParam("todo_complete_begin") int todo_complete_begin,
                                  @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByDeadline(userForm, todo_deadline, todo_property_begin, todo_property_end,
                                              todo_complete_begin, todo_complete_end);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "todos/property", params = {"todo_property"}, method = RequestMethod.GET)
    List<Todo> getTodosByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword,
                                  @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByProperty(userForm, todo_property);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "todos/property", params = {"begin", "todo_property"}, method = RequestMethod.GET)
    List<Todo> getTodosByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByProperty(userForm, begin, todo_property);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "todos/property", params = {"begin", "end", "todo_property"}, method = RequestMethod.GET)
    List<Todo> getTodosByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end, @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByProperty(userForm, begin, end, todo_property);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    @RequestMapping(value = "todos/property", params = {"begin", "end", "todo_property_begin", "todo_property_end"}, method = RequestMethod.GET)
    List<Todo> getTodosByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end,
                                  @RequestParam("todo_property_begin") int todo_property_begin,
                                  @RequestParam("todo_property_end") int todo_property_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByProperty(userForm, begin, end, todo_property_begin, todo_property_end);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "todos/complete", params = {"todo_complete"}, method = RequestMethod.GET)
    List<Todo> getTodosByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword,
                                  @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByComplete(userForm, todo_complete);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "todos/complete", params = {"begin", "todo_complete"}, method = RequestMethod.GET)
    List<Todo> getTodosByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByComplete(userForm, begin, todo_complete);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "todos/complete", params = {"begin", "end", "todo_complete"}, method = RequestMethod.GET)
    List<Todo> getTodosByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end, @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByComplete(userForm, begin, end, todo_complete);
    }

    /**
     * 根据条件获得用户的Todos
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "todos/complete", params = {"begin", "todo_complete_begin", "todo_complete_end"}, method = RequestMethod.GET)
    List<Todo> getTodosByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end,
                                  @RequestParam("todo_complete_begin") int todo_complete_begin,
                                  @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getTodosByComplete(userForm, begin, end, todo_complete_begin, todo_complete_end);
    }

    /**
     * 得到指定用户的todo总数
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public int getTodoCounts(@RequestParam("userName") String userName,
                             @RequestParam("userPassword") String userPassword) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCount(userForm);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "count/all", method = RequestMethod.GET)
    public int getCount(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                        @RequestParam("begin") Date begin, @RequestParam("end") Date end,
                        @RequestParam("todo_deadline") Date todo_deadline,
                        @RequestParam("todo_property_begin") int todo_property_begin,
                        @RequestParam("todo_property_end") int todo_property_end,
                        @RequestParam("todo_complete_begin") int todo_complete_begin,
                        @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCount(userForm, begin, end, todo_deadline, todo_property_begin, todo_property_end,
                                    todo_complete_begin, todo_complete_end);
    }

    /**
     * @param userName
     * @param userPassword
     * @param todo_title
     * @return
     */
    public @RequestMapping(value = "count/title", params = {"todo_title"}, method = RequestMethod.GET)
    int getCountByTitle(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                        @RequestParam("todo_title") String todo_title) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByTitle(userForm, todo_title);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @return
     */
    public @RequestMapping(value = "count/date", params = {"begin"}, method = RequestMethod.GET)
    int getCountByDate(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                       @RequestParam("begin") Date begin) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDate(userForm, begin);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @return
     */
    public @RequestMapping(value = "count/date", params = {"begin", "end"}, method = RequestMethod.GET)
    int getCountByDate(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                       @RequestParam("begin") Date begin, @RequestParam("end") Date end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDate(userForm, begin, end);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @return
     */
    public @RequestMapping(value = "count/deadline", params = {"todo_deadline"}, method = RequestMethod.GET)
    int getCountByDeadline(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
                           @RequestParam("todo_deadline") Date todo_deadline) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDeadline(userForm, todo_deadline);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_property
     * @return
     */
    public @RequestMapping(value = "count/deadline", params = {"todo_deadline", "todo_property"}, method = RequestMethod.GET)
    int getCountByDeadlineProperty(@RequestParam("userName") String userName,
                                   @RequestParam("userPassword") String userPassword,
                                   @RequestParam("todo_deadline") Date todo_deadline,
                                   @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDeadlineProperty(userForm, todo_deadline, todo_property);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    public @RequestMapping(value = "count/deadline", params = {"todo_deadline", "todo_property_begin", "todo_property_end"}, method = RequestMethod.GET)
    int getCountByDeadlineProperty(@RequestParam("userName") String userName,
                                   @RequestParam("userPassword") String userPassword,
                                   @RequestParam("todo_deadline") Date todo_deadline,
                                   @RequestParam("todo_property_begin") int todo_property_begin,
                                   @RequestParam("todo_property_end") int todo_property_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDeadlineProperty(userForm, todo_deadline, todo_property_begin, todo_property_end);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_complete
     * @return
     */
    public @RequestMapping(value = "count/deadline", params = {"todo_deadline", "todo_complete"}, method = RequestMethod.GET)
    int getCountByDeadlineComplete(@RequestParam("userName") String userName,
                                   @RequestParam("userPassword") String userPassword,
                                   @RequestParam("todo_deadline") Date todo_deadline,
                                   @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDeadlineComplete(userForm, todo_deadline, todo_complete);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "count/deadline", params = {"todo_deadline", "todo_complete_begin", "todo_complete_end"}, method = RequestMethod.GET)
    public int getCountByDeadlineComplete(@RequestParam("userName") String userName,
                                          @RequestParam("userPassword") String userPassword,
                                          @RequestParam("todo_deadline") Date todo_deadline,
                                          @RequestParam("todo_complete_begin") int todo_complete_begin,
                                          @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDeadlineComplete(userForm, todo_deadline, todo_complete_begin, todo_complete_end);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_deadline
     * @param todo_property_begin
     * @param todo_property_end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "count/deadline", params = {"todo_property_begin", "todo_property_end", "todo_complete_begin", "todo_complete_end"}, method = RequestMethod.GET)
    public int getCountByDeadline(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, Date todo_deadline,
                                  @RequestParam("todo_property_begin") int todo_property_begin,
                                  @RequestParam("todo_property_end") int todo_property_end,
                                  @RequestParam("todo_complete_begin") int todo_complete_begin,
                                  @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByDeadline(userForm, todo_deadline, todo_property_begin, todo_property_end,
                                              todo_complete_begin, todo_complete_end);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "count/property", params = {"todo_property"}, method = RequestMethod.GET)
    public int getCountByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword,
                                  @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByProperty(userForm, todo_property);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "count/property", params = {"begin", "todo_property"}, method = RequestMethod.GET)
    public int getCountByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByProperty(userForm, begin, todo_property);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_property
     * @return
     */
    @RequestMapping(value = "count/property", params = {"begin", "end", "todo_property"}, method = RequestMethod.GET)
    public int getCountByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end, @RequestParam("todo_property") int todo_property) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByProperty(userForm, begin, end, todo_property);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_property_begin
     * @param todo_property_end
     * @return
     */
    @RequestMapping(value = "count/property", params = {"begin", "end", "todo_property_begin", "todo_property_end"}, method = RequestMethod.GET)
    public int getCountByProperty(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end,
                                  @RequestParam("todo_property_begin") int todo_property_begin,
                                  @RequestParam("todo_property_end") int todo_property_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByProperty(userForm, begin, end, todo_property_begin, todo_property_end);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "count/complete", params = {"todo_complete"}, method = RequestMethod.GET)
    public int getCountByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword,
                                  @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByComplete(userForm, todo_complete);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "count/complete", params = {"begin", "todo_complete"}, method = RequestMethod.GET)
    public int getCountByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByComplete(userForm, begin, todo_complete);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_complete
     * @return
     */
    @RequestMapping(value = "count/complete", params = {"begin", "end", "todo_complete"}, method = RequestMethod.GET)
    public int getCountByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end, @RequestParam("todo_complete") int todo_complete) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByComplete(userForm, begin, end, todo_complete);
    }

    /**
     * 根据条件求指定用户的todo条数
     *
     * @param userName
     * @param userPassword
     * @param begin
     * @param end
     * @param todo_complete_begin
     * @param todo_complete_end
     * @return
     */
    @RequestMapping(value = "count/complete", params = {"begin", "end", "todo_complete_begin", "todo_complete_end"}, method = RequestMethod.GET)
    public int getCountByComplete(@RequestParam("userName") String userName,
                                  @RequestParam("userPassword") String userPassword, @RequestParam("begin") Date begin,
                                  @RequestParam("end") Date end,
                                  @RequestParam("todo_complete_begin") int todo_complete_begin,
                                  @RequestParam("todo_complete_end") int todo_complete_end) {
        UserForm userForm = new UserForm();
        userForm.setUserName(userName);
        userForm.setUserPassword(userPassword);
        return todoService.getCountByComplete(userForm, begin, end, todo_complete_begin, todo_complete_end);
    }


}

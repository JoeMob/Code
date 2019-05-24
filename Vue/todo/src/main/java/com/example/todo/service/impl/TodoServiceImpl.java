package com.example.todo.service.impl;


import com.example.todo.model.Todo;
import com.example.todo.model.TodoForm;
import com.example.todo.model.User;
import com.example.todo.model.UserForm;
import com.example.todo.repository.TodoRepository;
import com.example.todo.service.TodoService;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserService userService;

    /**
     * 用户是否合法
     * @param userForm
     * @return 合法为true，非法为false
     */
    private boolean isCorrectUser(UserForm userForm){
        if(userService.isUserExist(userForm))
            return true;
        return false;
    }

    /**
     * 用户是否合法
     * @param userId User Id
     * @param pwd password
     * @return 合法为true，非法为false
     */
    private  boolean isCorrectUser(String userId,String  pwd){
        UserForm userForm=new UserForm();
        userForm.setUserName(userId);
        userForm.setUserPassword(pwd);
        return isCorrectUser(userForm);
    }

    /**
     * 获得用户
     * @param userForm
     * @return
     */
    private User getUser(UserForm userForm){
        return userService.getUser(userForm);
    }

    /**
     * 获得用户
     * @param userId
     * @param pwd
     * @return
     */
    private User getUser(String userId,String  pwd){
        UserForm userForm=new UserForm();
        userForm.setUserName(userId);
        userForm.setUserPassword(pwd);
        return userService.getUser(userForm);
    }

    @Override
    public int add(TodoForm todoForm) {

        if(todoForm.getUser()==null|| todoForm.getTodo()==null )
            return 0;
        User user=getUser(todoForm.getUser().getUserName(), todoForm.getUser().getUserPassword());
        if(user==null)
            return 0;

        Todo todo=new Todo();
        todo.setUserId(user.getId());
        todo.setTitle((todoForm.getTodo().getTitle()));
        todo.setContent(todoForm.getTodo().getContent());
        todo.setBeginDate(todoForm.getTodo().getBeginDate());
        todo.setDeadline(todoForm.getTodo().getDeadline());
        todo.setProperty(todoForm.getTodo().getProperty());
        todo.setComplete(todoForm.getTodo().getComplete());
        todo.setCompleteDesc((todoForm.getTodo().getCompleteDesc()));
        return todoRepository.add(todo);
    }





    @Override
    public int deleteById(UserForm userForm, String todo_id) {
        User user=getUser(userForm);
        if(user==null)
            return 0;

        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;

        return todoRepository.deleteById(todo_id);
    }

    @Override
    public int deleteByUser(UserForm userForm) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.deleteByUserId(user.getId());
    }


    @Override
    public int update(TodoForm todoForm) {

        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;

        User user=getUser(todoForm.getUser().getUserName(), todoForm.getUser().getUserPassword());
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todoForm.getTodo().getId(), user.getId()))
            return 0;



        Todo todo=new Todo();
        todo.setId(todoForm.getTodo().getId());
        todo.setUserId(user.getId());
        todo.setTitle((todoForm.getTodo().getTitle()));
        todo.setContent(todoForm.getTodo().getContent());
        todo.setBeginDate(todoForm.getTodo().getBeginDate());
        todo.setDeadline(todoForm.getTodo().getDeadline());
        todo.setProperty(todoForm.getTodo().getProperty());
        todo.setComplete(todoForm.getTodo().getComplete());
        todo.setCompleteDesc((todoForm.getTodo().getCompleteDesc()));
        return todoRepository.update(todo);
    }

    @Override
    public int updateTitle(UserForm userForm,String todo_title, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;

        return todoRepository.updateTitle(todo_title,todo_id);
    }

    @Override
    public int updateTitle(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateTitle(userForm, todoForm.getTodo().getTitle(), todoForm.getTodo().getId());
    }

    @Override
    public int updateContent(UserForm userForm, String todo_content, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;

        return todoRepository.updateContent(todo_content,todo_id);
    }

    @Override
    public int updateContent(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateContent(userForm, todoForm.getTodo().getContent(), todoForm.getTodo().getId());
    }

    @Override
    public int updateBeginDate(UserForm userForm, Date todo_begin_datetime, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;

        return todoRepository.updateBeginDate(todo_begin_datetime,todo_id);
    }

    @Override
    public int updateBeginDate(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateBeginDate(userForm, todoForm.getTodo().getBeginDate(), todoForm.getTodo().getId());
    }

    @Override
    public int updateDeadline(UserForm userForm, Date todo_deadline, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;

        return todoRepository.updateDeadline(todo_deadline,todo_id);
    }

    @Override
    public int updateDeadline(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateDeadline(userForm, todoForm.getTodo().getDeadline(), todoForm.getTodo().getId());
    }

    @Override
    public int updateProperty(UserForm userForm, int todo_property, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;

        return todoRepository.updateProperty(todo_property,todo_id);
    }

    @Override
    public int updateProperty(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateProperty(userForm, todoForm.getTodo().getProperty(), todoForm.getTodo().getId());
    }

    @Override
    public int updateComplete(UserForm userForm, int todo_complete, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;
        return todoRepository.updateComplete(todo_complete,todo_id);
    }

    @Override
    public int updateComplete(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateComplete(userForm, todoForm.getTodo().getComplete(), todoForm.getTodo().getId());
    }

    @Override
    public int updateCompleteDesc(UserForm userForm, String todo_complete_desc, String todo_id) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return 0;

        //验证该条todo是否属于该用户
        if(!todoRepository.isCorrectUser(todo_id,user.getId()))
            return 0;
        return todoRepository.updateCompleteDesc(todo_complete_desc,todo_id);
    }

    @Override
    public int updateCompleteDesc(TodoForm todoForm) {
        if(todoForm ==null || todoForm.getUser()==null || todoForm.getTodo()==null)
            return 0;
        UserForm userForm=new UserForm();
        userForm.setUserName(todoForm.getUser().getUserName());
        userForm.setUserPassword(todoForm.getUser().getUserPassword());

        return updateCompleteDesc(userForm, todoForm.getTodo().getCompleteDesc(), todoForm.getTodo().getId());
    }

    @Override
    public List<Todo> getAll(UserForm userForm) {
        //验证用户是否正确
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getAll(user.getId());
    }

    @Override
    public List<Todo> getAll(UserForm userForm, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getAll(user.getId(),begin,end,todo_deadline,todo_property_begin,todo_property_end,todo_complete_begin,todo_complete_end);
    }

    @Override
    public List<Todo> getTodosByTitle(UserForm userForm, String title) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByTitle(user.getId(),title);
    }

    @Override
    public List<Todo> getTodosByDate(UserForm userForm, Date begin) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDate(user.getId(),begin);
    }

    @Override
    public List<Todo> getTodosByDate(UserForm userForm, Date begin, Date end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDate(user.getId(),begin,end);
    }

    @Override
    public List<Todo> getTodosByDeadline(UserForm userForm, Date todo_deadline) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDeadline(user.getId(),todo_deadline);
    }

    @Override
    public List<Todo> getTodosByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDeadlineProperty(user.getId(),todo_deadline,todo_property);
    }

    @Override
    public List<Todo> getTodosByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDeadlineProperty(user.getId(),todo_deadline,todo_property_begin,todo_property_end);
    }

    @Override
    public List<Todo> getTodosByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDeadlineComplete(user.getId(),todo_deadline,todo_complete);
    }

    @Override
    public List<Todo> getTodosByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDeadlineComplete(user.getId(),todo_deadline,todo_complete_begin,todo_complete_end);
    }

    @Override
    public List<Todo> getTodosByDeadline(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByDeadline(user.getId(),todo_deadline,todo_property_begin,todo_property_end,todo_complete_begin,todo_complete_end);
    }

    @Override
    public List<Todo> getTodosByProperty(UserForm userForm, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByProperty(user.getId(),todo_property);
    }

    @Override
    public List<Todo> getTodosByProperty(UserForm userForm, Date begin, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByProperty(user.getId(),begin,todo_property);
    }

    @Override
    public List<Todo> getTodosByProperty(UserForm userForm, Date begin, Date end, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByProperty(user.getId(),begin,end,todo_property);
    }

    @Override
    public List<Todo> getTodosByProperty(UserForm userForm, Date begin, Date end, int todo_property_begin, int todo_property_end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByProperty(user.getId(),begin,end,todo_property_begin,todo_property_end);
    }

    @Override
    public List<Todo> getTodosByComplete(UserForm userForm, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByComplete(user.getId(),todo_complete);
    }

    @Override
    public List<Todo> getTodosByComplete(UserForm userForm, Date begin, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByComplete(user.getId(),begin,todo_complete);
    }

    @Override
    public List<Todo> getTodosByComplete(UserForm userForm, Date begin, Date end, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByComplete(user.getId(),begin,end,todo_complete);
    }

    @Override
    public List<Todo> getTodosByComplete(UserForm userForm, Date begin, Date end, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return null;
        return todoRepository.getTodosByComplete(user.getId(),begin,end,todo_complete_begin,todo_complete_end);
    }

    @Override
    public int getCount(UserForm userForm) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCount(user.getId());
    }

    @Override
    public int getCount(UserForm userForm, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCount(user.getId(),begin,end,todo_deadline,todo_property_begin,todo_property_end,todo_complete_begin,todo_complete_end);
    }

    @Override
    public int getCountByTitle(UserForm userForm, String title) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByTitle(user.getId(),title);
    }

    @Override
    public int getCountByDate(UserForm userForm, Date begin) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDate(user.getId(),begin);
    }

    @Override
    public int getCountByDate(UserForm userForm, Date begin, Date end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDate(user.getId(),begin,end);
    }

    @Override
    public int getCountByDeadline(UserForm userForm, Date todo_deadline) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDeadline(user.getId(),todo_deadline);
    }

    @Override
    public int getCountByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDeadlineProperty(user.getId(),todo_deadline,todo_property);
    }

    @Override
    public int getCountByDeadlineProperty(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDeadlineProperty(user.getId(),todo_deadline,todo_property_begin,todo_property_end);
    }

    @Override
    public int getCountByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDeadlineComplete(user.getId(),todo_deadline,todo_complete);
    }

    @Override
    public int getCountByDeadlineComplete(UserForm userForm, Date todo_deadline, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDeadlineComplete(user.getId(),todo_deadline,todo_complete_begin,todo_complete_end);
    }

    @Override
    public int getCountByDeadline(UserForm userForm, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByDeadline(user.getId(),todo_deadline,todo_property_begin,todo_property_end,todo_complete_begin,todo_complete_end);
    }

    @Override
    public int getCountByProperty(UserForm userForm, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByProperty(user.getId(),todo_property);
    }

    @Override
    public int getCountByProperty(UserForm userForm, Date begin, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByProperty(user.getId(),begin,todo_property);
    }

    @Override
    public int getCountByProperty(UserForm userForm, Date begin, Date end, int todo_property) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByProperty(user.getId(),begin,end,todo_property);
    }

    @Override
    public int getCountByProperty(UserForm userForm, Date begin, Date end, int todo_property_begin, int todo_property_end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByProperty(user.getId(),begin,end,todo_property_begin,todo_property_end);
    }

    @Override
    public int getCountByComplete(UserForm userForm, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByComplete(user.getId(),todo_complete);
    }

    @Override
    public int getCountByComplete(UserForm userForm, Date begin, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByComplete(user.getId(),begin,todo_complete);
    }

    @Override
    public int getCountByComplete(UserForm userForm, Date begin, Date end, int todo_complete) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByComplete(user.getId(),begin,end,todo_complete);
    }

    @Override
    public int getCountByComplete(UserForm userForm, Date begin, Date end, int todo_complete_begin, int todo_complete_end) {
        User user=getUser(userForm);
        if(user==null)
            return 0;
        return todoRepository.getCountByComplete(user.getId(),begin,end,todo_complete_begin,todo_complete_end);
    }


}

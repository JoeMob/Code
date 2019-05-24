package com.example.todo.repository.impl;


import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.utils.GUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final class TodoMapper implements RowMapper<Todo> {

        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();

            todo.setId(rs.getString("id"));
            todo.setUserId(rs.getString("t_user_id"));
            todo.setTitle(rs.getString("todo_title"));
            todo.setContent(rs.getString("todo_content"));
            todo.setBeginDate(rs.getDate("todo_begin_datetime"));
            todo.setDeadline(rs.getDate("todo_deadline"));
            todo.setProperty(rs.getInt("todo_property"));
            todo.setComplete(rs.getInt("todo_complete"));
            todo.setCompleteDesc(rs.getString("todo_complete_desc"));


            return todo;
        }
    }


    @Override
    public int add(Todo todo) {
        if (todo == null)
            return 0;


        return jdbcTemplate.update("insert into todo(id,t_user_id,todo_title,todo_content,todo_begin_datetime,todo_deadline,todo_property,todo_complete,todo_complete_desc) values(?,?,?,?,?,?,?,?,?)",
                GUID.getGUID(), todo.getUserId(), todo.getTitle(), todo.getContent(), todo.getBeginDate(), todo.getDeadline(), todo.getProperty(), todo.getComplete(), todo.getCompleteDesc());
    }


    @Override
    public int delete(Todo todo) {
        if (todo == null || todo.getId().length() == 0)
            return 0;
        return jdbcTemplate.update("DELETE from  todo where id=?", todo.getId());
    }

    @Override
    public int deleteById(String id) {
        return jdbcTemplate.update("DELETE from  todo where id=?", id);
    }

    @Override
    public int deleteByUserId(String t_user_id) {
        return jdbcTemplate.update("DELETE from  todo where t_user_id=?", t_user_id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from  todo");
    }


    @Override
    public int update(Todo todo) {
        if (todo == null)
            return 0;



        return jdbcTemplate.update("UPDATE todo set t_user_id=?,todo_title=?,todo_content=?,todo_begin_datetime=?,todo_deadline=?,todo_property=?,todo_complete=?,todo_complete_desc=? where id=?",
                todo.getUserId(), todo.getTitle(), todo.getContent(), todo.getBeginDate(), todo.getDeadline(), todo.getProperty(), todo.getComplete(), todo.getCompleteDesc(), todo.getId());
    }

    @Override
    public int updateTitle(String todo_title, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_title=? where id=?", todo_title,id);
    }

    @Override
    public int updateContent(String todo_content, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_content=? where id=?", todo_content,id);
    }

    @Override
    public int updateBeginDate(Date todo_begin_datetime, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_begin_datetime=? where id=?", todo_begin_datetime, id);
    }

    @Override
    public int updateDeadline(Date todo_deadline, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_deadline=? where id=?", todo_deadline, id);
    }

    @Override
    public int updateProperty(int todo_property, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_property=? where id=?", todo_property, id);
    }

    @Override
    public int updateComplete(int todo_complete, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_complete=? where id=?", todo_complete, id);
    }

    @Override
    public int updateCompleteDesc(String todo_complete_desc, String id) {
        return jdbcTemplate.update("UPDATE todo set todo_complete_desc=? where id=?", todo_complete_desc, id);
    }

    @Override
    public boolean isCorrectUser(String id, String t_user_id) {
        return jdbcTemplate.queryForObject("select count(*) from todo where id=? and  t_user_id=?", new Object[]{id,t_user_id}, Integer.class)>0;
    }


    /**
     * 所有
     *
     * @return
     */
    @Override
    public List<Todo> getAll(String t_user_id) {
        if (getCount(t_user_id) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=?", new Object[]{t_user_id}, new TodoMapper());

    }


    @Override
    public List<Todo> getAll(String t_user_id, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        if (getCount(t_user_id, begin, end, todo_deadline, todo_property_begin, todo_property_end, todo_complete_begin, todo_complete_end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_deadline<? and todo_property>=? and todo_property<? and todo_complete=? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, begin, end, todo_deadline, todo_property_begin, todo_property_end, todo_complete_begin, todo_complete_end}, new TodoMapper());

    }

    @Override
    public List<Todo> getTodosByTitle(String t_user_id, String title) {
        if (getCountByTitle(t_user_id, title) == 0)
            return null;

        title="%"+title+"%";

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_title like ?", new Object[]{t_user_id, title}, new TodoMapper());

    }

    @Override
    public List<Todo> getTodosByDate(String t_user_id, Date begin) {
        if (getCountByDate(t_user_id, begin) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=?", new Object[]{t_user_id, begin}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByDate(String t_user_id, Date begin, Date end) {
        if (getCountByDate(t_user_id, begin, end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?", new Object[]{t_user_id, begin, end}, new TodoMapper());


    }


    @Override
    public List<Todo> getTodosByDeadline(String t_user_id, Date todo_deadline) {
        if (getCountByDeadline(t_user_id, todo_deadline) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_deadline<?=?", new Object[]{t_user_id, todo_deadline}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property) {
        if (getCountByDeadlineProperty(t_user_id, todo_deadline, todo_property) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_deadline<? and todo_property=?", new Object[]{t_user_id, todo_deadline, todo_property}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end) {
        if (getCountByDeadlineProperty(t_user_id, todo_deadline, todo_property_begin, todo_property_end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_deadline<? and todo_property>=? and todo_property<?", new Object[]{t_user_id, todo_deadline, todo_property_begin, todo_property_end}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete) {
        if (getCountByDeadlineComplete(t_user_id, todo_deadline, todo_complete) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_deadline<? and todo_complete=?", new Object[]{t_user_id, todo_deadline, todo_complete}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete_begin, int todo_complete_end) {
        if (getCountByDeadlineComplete(t_user_id, todo_deadline, todo_complete_begin, todo_complete_end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_deadline<? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, todo_deadline, todo_complete_begin, todo_complete_end}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByDeadline(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        if (getCountByDeadline(t_user_id, todo_deadline, todo_property_begin, todo_property_end, todo_complete_begin, todo_complete_end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_deadline<? and todo_property>=? and todo_property<? and todo_complete>=? and todo_complete<?",
                new Object[]{t_user_id, todo_deadline, todo_property_begin, todo_property_end, todo_complete_begin, todo_complete_end}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByProperty(String t_user_id, int todo_property) {
        if (getCountByProperty(t_user_id, todo_property) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_property=?", new Object[]{t_user_id, todo_property}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByProperty(String t_user_id, Date begin, int todo_property) {
        if (getCountByProperty(t_user_id, begin, todo_property) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_property=?", new Object[]{t_user_id, begin, todo_property}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByProperty(String t_user_id, Date begin, Date end, int todo_property) {
        if (getCountByProperty(t_user_id, begin, end, todo_property) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<? and todo_property=?", new Object[]{t_user_id, begin, end, todo_property}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByProperty(String t_user_id, Date begin, Date end, int todo_property_begin, int todo_property_end) {
        if (getCountByProperty(t_user_id, begin, end, todo_property_begin, todo_property_end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_property>=? and todo_property<?", new Object[]{t_user_id, begin, end, todo_property_begin, todo_property_end}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByComplete(String t_user_id, int todo_complete) {
        if (getCountByComplete(t_user_id, todo_complete) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_complete=?", new Object[]{t_user_id, todo_complete}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByComplete(String t_user_id, Date begin, int todo_complete) {
        if (getCountByComplete(t_user_id, begin, todo_complete) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=?  and todo_complete=?", new Object[]{t_user_id, begin, todo_complete}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByComplete(String t_user_id, Date begin, Date end, int todo_complete) {
        if (getCountByComplete(t_user_id, begin, end, todo_complete) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_complete=?", new Object[]{t_user_id, begin, end, todo_complete}, new TodoMapper());


    }

    @Override
    public List<Todo> getTodosByComplete(String t_user_id, Date begin, Date end, int todo_complete_begin, int todo_complete_end) {
        if (getCountByComplete(t_user_id, begin, end, todo_complete_begin, todo_complete_end) == 0)
            return null;

        return this.jdbcTemplate.query(
                "select * from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_complete=? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, begin, end, todo_complete_begin, todo_complete_end}, new TodoMapper());


    }


    @Override
    public int getCount(String t_user_id) {

        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=?", new Object[]{t_user_id}, Integer.class);
    }


    @Override
    public int getCount(String t_user_id, Date begin, Date end, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_deadline<? and todo_property>=? and todo_property<? and todo_complete=? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, begin, end, todo_deadline, todo_property_begin, todo_property_end, todo_complete_begin, todo_complete_end}, Integer.class);
    }

    @Override
    public int getCountByTitle(String t_user_id, String title) {
        title="%"+title+"%";
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_title like ?",
                                           new Object[]{t_user_id,title},
                                           Integer.class);
    }


    @Override
    public int getCountByDate(String t_user_id, Date begin) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=?", new Object[]{t_user_id, begin}, Integer.class);
    }

    @Override
    public int getCountByDate(String t_user_id, Date begin, Date end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?", new Object[]{t_user_id, begin, end}, Integer.class);
    }

    @Override
    public int getCountByDeadline(String t_user_id, Date todo_deadline) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_deadline<?", new Object[]{t_user_id, todo_deadline}, Integer.class);
    }

    @Override
    public int getCountByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_deadline<? and todo_property=?", new Object[]{t_user_id, todo_deadline, todo_property}, Integer.class);
    }

    @Override
    public int getCountByDeadlineProperty(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_deadline<? and todo_property>=? and todo_property<?", new Object[]{t_user_id, todo_deadline, todo_property_begin, todo_property_end}, Integer.class);
    }

    @Override
    public int getCountByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_deadline<? and todo_complete=?", new Object[]{t_user_id, todo_deadline, todo_complete}, Integer.class);
    }

    @Override
    public int getCountByDeadlineComplete(String t_user_id, Date todo_deadline, int todo_complete_begin, int todo_complete_end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_deadline<? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, todo_deadline, todo_complete_begin, todo_complete_end}, Integer.class);
    }

    @Override
    public int getCountByDeadline(String t_user_id, Date todo_deadline, int todo_property_begin, int todo_property_end, int todo_complete_begin, int todo_complete_end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_deadline<? and todo_property>=? and todo_property<? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, todo_deadline, todo_property_begin, todo_property_end, todo_complete_begin, todo_complete_end}, Integer.class);
    }

    @Override
    public int getCountByProperty(String t_user_id, int todo_property) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_property=?", new Object[]{t_user_id, todo_property}, Integer.class);
    }

    @Override
    public int getCountByProperty(String t_user_id, Date begin, int todo_property) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_property=?", new Object[]{t_user_id, begin, todo_property}, Integer.class);
    }


    @Override
    public int getCountByProperty(String t_user_id, Date begin, Date end, int todo_property) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<? and todo_property=?", new Object[]{t_user_id, begin, end, todo_property}, Integer.class);
    }


    @Override
    public int getCountByProperty(String t_user_id, Date begin, Date end, int todo_property_begin, int todo_property_end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_property>=? and todo_property<?", new Object[]{t_user_id, begin, end, todo_property_begin, todo_property_end}, Integer.class);
    }


    @Override
    public int getCountByComplete(String t_user_id, int todo_complete) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_complete=?", new Object[]{t_user_id}, Integer.class);
    }


    @Override
    public int getCountByComplete(String t_user_id, Date begin, int todo_complete) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=?  and todo_complete=?", new Object[]{t_user_id, todo_complete}, Integer.class);
    }


    @Override
    public int getCountByComplete(String t_user_id, Date begin, Date end, int todo_complete) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_complete=?", new Object[]{t_user_id, begin, end, todo_complete}, Integer.class);
    }


    @Override
    public int getCountByComplete(String t_user_id, Date begin, Date end, int todo_complete_begin, int todo_complete_end) {
        return jdbcTemplate.queryForObject("select count(*) from todo where t_user_id=? and todo_begin_datetime>=? and todo_begin_datetime<?  and todo_complete=? and todo_complete>=? and todo_complete<?", new Object[]{t_user_id, begin, end, todo_complete_begin, todo_complete_end}, Integer.class);
    }


}

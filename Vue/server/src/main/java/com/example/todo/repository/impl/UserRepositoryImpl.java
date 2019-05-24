package com.example.todo.repository.impl;


import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.model.UserForm;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.utils.EncoderHandler;
import com.example.todo.utils.GUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getString("id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserPassword(rs.getString("user_pwd"));


            return user;
        }
    }


    /**
     * user_id是否存在
     * @param user_name
     * @return
     */
    public boolean isUserIdExist(String user_name) {
        if (user_name == null ||user_name.length()==0)
            return true;

        return jdbcTemplate.queryForObject("select count(*) from user where user_name=? ", new Object[]{user_name}, Integer.class) > 0;
    }

    /**
     * 增加
     *
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        if (user == null
                || user.getUserName() == null || user.getUserName().length() < 3 || user.getUserName().length() > 100
                || user.getUserPassword() == null || user.getUserPassword().length() < 3 || user.getUserPassword().length() > 100)
            return 0;

        //用户name不能存在才能将该用户id加入到表中
        if(isUserIdExist(user.getUserName()))
            return 0;

        String password = (EncoderHandler.encode(user.getUserPassword()));


        return jdbcTemplate.update("insert into user(id,user_name,user_pwd) values(?,?,?)", GUID.getGUID(), user.getUserName(), password);
    }

    /**
     * 删除
     *
     * @param user
     * @return
     */
    @Override
    public int delete(User user) {
        if (user == null || user.getId() == null)
            return 0;
        return jdbcTemplate.update("DELETE from  user where id=?", user.getId());
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        if (user == null
                || user.getUserName() == null || user.getUserName().length() < 3 || user.getUserName().length() > 100
                || user.getUserPassword() == null || user.getUserPassword().length() < 3 || user.getUserPassword().length() > 100)
            return 0;

        String password = (EncoderHandler.encode(user.getUserPassword()));
        return jdbcTemplate.update("UPDATE user set user_name=? user_pwd=? where id=?", user.getUserName(), password, user.getId());
    }

    /**
     * 所有
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        if (getCount() == 0)
            return null;

        List<User> userList = this.jdbcTemplate.query(
                "select * from todo", new UserMapper());

        return userList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public int getCount() {

        return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    }


    @Override
    public boolean isUserExist(UserForm userForm) {
        if (userForm == null || userForm.getUserName() == null || userForm.getUserPassword() == null)
            return false;
        String password = (EncoderHandler.encode(userForm.getUserPassword()));
        return jdbcTemplate.queryForObject("select count(*) from user where user_name=? and user_pwd=?", new Object[]{userForm.getUserName(), password}, Integer.class) > 0;
    }

    @Override
    public User getUser(UserForm userForm) {
        if(!isUserExist(userForm))
            return null;

        String password = (EncoderHandler.encode(userForm.getUserPassword()));

        return jdbcTemplate.queryForObject("select * from user where  user_name=? and user_pwd =?", new
                Object[]{userForm.getUserName(), password}, new UserMapper());
    }


}

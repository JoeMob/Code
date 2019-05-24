package service;

import dao.UserDao;
import domain.User;

public class UserService{
	private UserDao userDao = new UserDao();

	public Boolean registerUser(User user) {
		if (userDao.find(user.getName(),user.getPwd())==null) {
			userDao.add(user);
			return true;
		}
		else
			return false;
	}

	public User loginUser(String userName, String userPwd) {
		return userDao.find(userName, userPwd);
	}
}

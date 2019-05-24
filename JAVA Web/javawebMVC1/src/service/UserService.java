package service;

import dao.UserDao;
import daomain.User;

public class UserService {
	
	
	
	/**
	 * 实现登录功能
	 * @param userSno
	 * @param password
	 * @return
	 */
	public User login(User loginUser) {
		UserDao userDao = new UserDao();
		return userDao.find(loginUser);
	}
}

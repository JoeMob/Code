package service;

import dao.UserDao;
import daomain.User;

public class UserService {
	
	
	
	/**
	 * ʵ�ֵ�¼����
	 * @param userSno
	 * @param password
	 * @return
	 */
	public User login(User loginUser) {
		UserDao userDao = new UserDao();
		return userDao.find(loginUser);
	}
}

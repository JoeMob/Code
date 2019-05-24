package junit.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.UserDao;
import domain.User;

public class UserDaoTest {
	//创建待测试类的对象
	private static UserDao userDao = new UserDao();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFind() {
		String name = "1";
		String psw = "1";
		User user = userDao.find(name, psw);
		//User expectedUser = null;
		User expectedUser = new User();
		expectedUser.setName(name);
		expectedUser.setAge(1);
		expectedUser.setSex("1");
		expectedUser.setUserID(1);
		expectedUser.setPwd(psw);
		
		assertEquals(user.getName(),expectedUser.getName());
		assertEquals(user.getAge(),expectedUser.getAge());
		assertEquals(user.getPwd(),expectedUser.getPwd());
		assertEquals(user.getUserID(),expectedUser.getUserID());
		assertEquals(user.getSex(),expectedUser.getSex());
	//	assertEquals(expectedUser, user);
	}

	@Test
	public void testAdd() {
		User user = new User();
		user.setName("张三");
		user.setAge(22);
		user.setPwd("111");
		user.setSex("男");
		user.setUserID(5);
		userDao.add(user);
		User userExpected = userDao.find(user.getName(), user.getPwd());
		assertEquals(user.getName(),userExpected.getName());
		assertEquals(user.getAge(),userExpected.getAge());
		assertEquals(user.getPwd(),userExpected.getPwd());
		assertEquals(user.getUserID(),userExpected.getUserID());
		assertEquals(user.getSex(),userExpected.getSex());
	
		//assertEquals(userExpected, user);
	}
}

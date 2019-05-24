package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daomain.User;
import util.Conn;

public class UserDao {
	//登录操作
	private Connection con ;
	private PreparedStatement pstmt;
	private ResultSet rs;
	/**
	 * 
	 * @param userSno  用户名
	 * @param password  用户密码
	 * @return
	 */
	public User find(User loginUser) {
		User user = null;
		try {
			
			Conn conn = new Conn();
			      //默认登录用户为空
			con = conn.getCon();
			pstmt = null;
			rs = null;
			System.out.println("检验从前端传来的数据userDao");
			System.out.println("userSno:"+loginUser.getUserSno());
			System.out.println("password:"+loginUser.getPassword());
			
			
			//预编译 ,查找是否存在
			//String sql = "select * from t_user where userSno=? and password=?";
			pstmt = con.prepareStatement("select * from t_user where userSno=? and password=?");
			pstmt.setString(1, loginUser.getUserSno());
			pstmt.setInt(2, loginUser.getPassword());
			rs = pstmt.executeQuery();     //返回结果集
			
			System.out.println("--start UserDao--");
			System.out.println("--end UserDao--");
			//如果不为空说明查到了
			if(rs.next()) {
				return loginUser;
			}
			conn.close(pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("预编译出错");
			
		}
		return user;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

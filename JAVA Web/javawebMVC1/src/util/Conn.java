package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conn {
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/mawanlin_javaweb?useSSL=true";
	
	private static String dbUserName = "root";

	private static String dbPassword = "123456";

	private static String jdbcName = "com.mysql.jdbc.Driver";
	//获取数据库连接
	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName(jdbcName);
			try {
				con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				System.out.println("连接数据库mawanlin_javaweb成功。");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("连接数据库mawanlin_javaweb失败!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("加载驱动失败");
		}
		return con;
	}
	
	
	//关闭数据库连接 
	public void close(Connection con) {
		try {
			con.close();
			System.out.println("关闭连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param pstmt
	 * @param con
	 */
	public void close(PreparedStatement pstmt,Connection con) {
		try {
			pstmt.close();
			con.close();
			System.out.println("关闭连接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭连接失败");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

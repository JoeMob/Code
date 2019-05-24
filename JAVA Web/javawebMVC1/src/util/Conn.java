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
	//��ȡ���ݿ�����
	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName(jdbcName);
			try {
				con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				System.out.println("�������ݿ�mawanlin_javaweb�ɹ���");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("�������ݿ�mawanlin_javawebʧ��!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��������ʧ��");
		}
		return con;
	}
	
	
	//�ر����ݿ����� 
	public void close(Connection con) {
		try {
			con.close();
			System.out.println("�ر����ӳɹ�");
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
			System.out.println("�ر����ӳɹ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ر�����ʧ��");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

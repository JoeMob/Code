package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daomain.User;
import util.Conn;

public class UserDao {
	//��¼����
	private Connection con ;
	private PreparedStatement pstmt;
	private ResultSet rs;
	/**
	 * 
	 * @param userSno  �û���
	 * @param password  �û�����
	 * @return
	 */
	public User find(User loginUser) {
		User user = null;
		try {
			
			Conn conn = new Conn();
			      //Ĭ�ϵ�¼�û�Ϊ��
			con = conn.getCon();
			pstmt = null;
			rs = null;
			System.out.println("�����ǰ�˴���������userDao");
			System.out.println("userSno:"+loginUser.getUserSno());
			System.out.println("password:"+loginUser.getPassword());
			
			
			//Ԥ���� ,�����Ƿ����
			//String sql = "select * from t_user where userSno=? and password=?";
			pstmt = con.prepareStatement("select * from t_user where userSno=? and password=?");
			pstmt.setString(1, loginUser.getUserSno());
			pstmt.setInt(2, loginUser.getPassword());
			rs = pstmt.executeQuery();     //���ؽ����
			
			System.out.println("--start UserDao--");
			System.out.println("--end UserDao--");
			//�����Ϊ��˵���鵽��
			if(rs.next()) {
				return loginUser;
			}
			conn.close(pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Ԥ�������");
			
		}
		return user;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

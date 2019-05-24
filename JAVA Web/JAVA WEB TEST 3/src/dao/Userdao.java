package dao;

import java.sql.*;

import static java.lang.System.out;

public class Userdao {
    String ur = "jdbc:mysql://localhost:3306/JAVA_WEB_TEST";
    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public void createConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }//注册和加载驱动器
        try {
            con = DriverManager.getConnection(ur, "root", "Wyx19980626");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//与数据库建立连接
    }
    public boolean Test() {

        String sql = "SELECT * FROM t_User";
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //statement对象用于将sql语句发送到数据库
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//执行statement对象，返回单个及结果集
        try {
            if (rs.next()) //ResultSet中有游标返回当前数据行，next()游标下移
                return true;
            else
                return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public String Verify(String username, String password) {
        String sql = "SELECT Membership FROM t_User WHERE Username = '" + username + "' AND Password = md5('" + password + "')";
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            while(rs.next()){
                return rs.getString("Membership");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
    public void Register(String username, String password){
        String sql = "INSERT INTO t_User VALUES ('" + username + "',md5('" + password + "'),'User')";
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            boolean a = stmt.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void Delete(String username){
        String sql = "DELETE FROM t_User WHERE Username = ('" + username + "')";
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            boolean a = stmt.execute(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
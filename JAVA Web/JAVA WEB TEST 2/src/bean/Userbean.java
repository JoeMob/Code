package bean;

import java.sql.*;

public class Userbean {
    //登录验证
    String ur = "jdbc:mysql://localhost:3306/JAVA_WEB_TEST";//连接数据库
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void createConn() {

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

    public boolean valid(String username, String password) {

        String sql = "SELECT Username FROM t_User WHERE Username = '" + username + "' AND Password = md5('" + password + "')";
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
}

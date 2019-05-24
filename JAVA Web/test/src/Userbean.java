import java.sql.*;

public class Userbean {
    String ur = "jdbc:mysql://localhost:3306/JAVA_WEB_TEST";
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void CreateConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(ur, "root", "Wyx19980626");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String Verify(String username, String password) {

        String sql = "SELECT Membership FROM User WHERE Username = '" + username + "' AND Password = md5('" + password + "')";
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
            String M = rs.getString("Membership");
            System.out.println(M);
            return M;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.out.println("错误");
            return "";
        }
    }

    public static void main() {
        Userbean userbean = new Userbean();
        String i = userbean.Verify("JoeWyx", "Wyx19980626");
        System.out.println(i);
    }
}
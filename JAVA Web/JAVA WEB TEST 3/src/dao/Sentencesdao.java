package dao;

import controller.SendSentences;
import domain.Sentences;

import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.out;
import static java.sql.JDBCType.NULL;

public class Sentencesdao extends HttpServlet {
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
        }
        try {
            con = DriverManager.getConnection(ur, "root", "Wyx19980626");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void InsertSentences(String Sentences, String User) {
        String sql = "INSERT INTO Sentences VALUES ('" + Sentences + "','" + User + "','','','')";
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            boolean a = stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("execute");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*public json SeekSentences(String User) {
        String sql = "SELECT * FROM Sentences WHERE SenUser = '" + User + "'";
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
            while (rs.next()) {
                Sentences SenSeek = new Sentences();
                return SenSeek;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Sentences S = new Sentences();
        return S;
    }*/
}

package controller;

import dao.Sentencesdao;
import domain.Sentences;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class Seek extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seek() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Sentences SeekSen = new Sentences();
        SeekSen.setUser(request.getParameter("usernameSeek"));
        Sentencesdao sentencesDao = new Sentencesdao();
        sentencesDao.createConnection();
        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        Sentences Seeker = new Sentences();
        while (true) {
            //Seeker = sentencesDao.SeekSentences(SeekSen.getUser());
            out.println("Sentence:" + Seeker.getSentences());
            out.println("<br />");
            out.println("User:" + Seeker.getUser());
            out.println("<br />");
            out.println("Sign1:" + Seeker.getSign1() + "-");
            out.println("Sign2:" + Seeker.getSign2() + "-");
            out.println("Sign3:" + Seeker.getSign3());
            out.println("<br />");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

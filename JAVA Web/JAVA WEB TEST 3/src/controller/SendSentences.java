package controller;

import dao.Sentencesdao;
import domain.Sentences;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendSentences extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendSentences() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Sentences SendSen = new Sentences();
        SendSen.setSentences(request.getParameter("sentences"));
        SendSen.setUser(request.getParameter("usernameSen"));
        Sentencesdao sentencesDao = new Sentencesdao();
        sentencesDao.createConnection();
        sentencesDao.InsertSentences(SendSen.getSentences(), SendSen.getUser());
        response.setContentType("text/html; charset=UTF-8");
        response.sendRedirect("/index.jsp");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

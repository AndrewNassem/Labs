/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Quiz;

/**
 *
 * @author iradukundajado
 */
public class QuizServlet extends HttpServlet {

    int totalScore = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session.getAttribute("quiz") == null) {
            session.setAttribute("quiz", new Quiz());
            session.setAttribute("questionN", 0);
        }

        Quiz q = (Quiz) session.getAttribute("quiz");
        int questionNumber = (int) session.getAttribute("questionN");

        if (questionNumber < q.getQuestions().length) {
            out.println("<html><head><title>Calculator</title></head><body>");
            out.println("<h1>The Number Quiz</h1>");
            out.println("<p> Your current score is " + totalScore + "</p>");
            out.println("<p>Guess The next number in the Sequence.</p>");
            out.println("<p>" + q.getQuestions()[questionNumber] + "</p>");
            out.println("<form action='QuizServlet' method='POST'>");
            out.println("Your Answer :  <input type='text' name='value' size='5'/>");
            out.println("<input type='submit' value='Submit'/>");
            out.println("</form>");
            out.print("</body></html>");
        } else {
            out.println("<html><head><title>Calculator</title></head><body>");
            out.println("<h1>The Number Quiz</h1>");
            out.println("<p> Your current score is " + totalScore);
            out.println("<p>You have completed the number quiz with score of " + totalScore + " out of " + q.getAnswer().length + "</p>");
            totalScore = 0;
            session.invalidate();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession s = request.getSession();
        Quiz q = (Quiz) s.getAttribute("quiz");
        int questionNumber = (int) s.getAttribute("questionN");

        int value = Integer.parseInt(request.getParameter("value"));

        if (q.getAnswer()[questionNumber] == value) {
            totalScore++;
        }
        s.setAttribute("questionN", ++questionNumber);

        doGet(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

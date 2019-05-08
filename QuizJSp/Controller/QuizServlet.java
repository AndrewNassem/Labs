/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Quiz;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andy
 */
@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {

        int totalScore = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        HttpSession session = request.getSession();
        if (session.getAttribute("quiz") == null) {
            session.setAttribute("quiz", new Quiz());
            session.setAttribute("questionN", 0);
            session.setAttribute("totalScore", totalScore);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession s = request.getSession();

        Quiz q = (Quiz) s.getAttribute("quiz");
        int questionNumber = (int) s.getAttribute("questionN");
        int value = Integer.parseInt(request.getParameter("value"));
        RequestDispatcher dispatcher = null;

        if (q.getAnswer()[questionNumber] == value) {
            if (s.getAttribute("trio") == null) {
                totalScore += 10;
            } else {
                int trio = (int) s.getAttribute("trio");

                if (trio == 1) {
                    totalScore += 5;
                } else if (trio == 0) {
                    totalScore += 10;
                } else {
                    totalScore += 2;
                }
            }
            s.setAttribute("trio", 0);
            ++questionNumber;
        } else {
            if (s.getAttribute("trio") == null) {
                s.setAttribute("trio", 1);
            } else {
                int trio = (int) s.getAttribute("trio");
                if (trio < 3) {
                    s.setAttribute("trio", ++trio);
                } else {
                    ++questionNumber;
                }
            }
        }

        s.setAttribute("questionN", questionNumber);
        s.setAttribute("totalScore", totalScore);

        if (questionNumber < q.getQuestions().length) {
            dispatcher = request.getRequestDispatcher("index.jsp");
        } else {
            request.setAttribute("totalScore", totalScore);
            if (totalScore <= 50 && totalScore >= 45) {
                request.setAttribute("score", "A");
            } else if (totalScore >= 35 && totalScore <= 44) {
                request.setAttribute("score", "B");
            } else if (totalScore > 25 && totalScore <= 34) {
                request.setAttribute("score", "C");
            } else {
                request.setAttribute("score", "NC");
            }
            dispatcher = request.getRequestDispatcher("finish.jsp");
            s.invalidate();
        }

        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
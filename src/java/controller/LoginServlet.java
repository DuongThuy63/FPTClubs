/*
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author minhdq
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usn = request.getParameter("username");
        String pw = request.getParameter("password");

        
        HttpSession session = request.getSession();
        
         if (session.getAttribute("account") != null){
            request.setAttribute("message", "You have already logged in");
            request.getRequestDispatcher("result.jsp").forward(request, response);
            return;
        } 
        try {
            User user = User.login(usn, pw);
            
            if (user == null){
                throw new RuntimeException();
            }
            session.setAttribute("account", user);     //set session account
            request.setAttribute("message", "Welcome " + user);
            request.getRequestDispatcher("result.jsp").forward(request, response);
//            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", "Invalid username/ password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

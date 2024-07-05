/*
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;
import dao.Validation;

/**
 *
 * @author minhdq
 */
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usn = request.getParameter("username");
        String fn = request.getParameter("full-name");

        String gd = request.getParameter("gender");
        String db = request.getParameter("dob");

        String pw = request.getParameter("password");
        String cpw = request.getParameter("confirm-pass");
        String email = request.getParameter("email");

        if (Validation.isFullNameEmpty(fn)) {
            fn = usn;
        }

        if (Validation.checkUniqueUser(usn, email) == false) {
            request.setAttribute("errorUserMail", "This username/ email has been used");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;

        }
        if (Validation.isValidUsername(usn) == false) {
            request.setAttribute("errorUsername", "Username does not contain special characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;

        }
        if (Validation.isValidUsernameLength(usn) == false) {
            request.setAttribute("errorUsernameLength", "Username must have at least 4 characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;

        }
        if (Validation.isPasswordsMatch(pw, cpw) == false) {
            request.setAttribute("errorConfirmPass", "Password does not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;

        }
        if (Validation.isValidPassword(pw) == false) {
            request.setAttribute("errorPass", "Password does not contain white spaces");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;

        }
        if (Validation.isValidPasswordLength(pw) == false) {
            request.setAttribute("errorPassLength", "Password must have at least 6 characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;

        }

        try {
            User us = new User(usn, pw, fn, gd, email, db);
            us = UserDAO.register(us);
            request.setAttribute("message", us + " Register Succecced");
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (java.lang.NullPointerException ex) {
            request.setAttribute("errorDate", "Invalid date");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

//        User us =  (User) request.getSession().getAttribute("user");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

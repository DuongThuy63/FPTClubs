/*
 */
package controller;

import dao.UserDAO;
import dao.Validation;
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
public class EditUserProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditUserProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("userInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
            // Retrieve form data
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");

            if (Validation.isValidPassword(password) == false) {
                request.setAttribute("errorPass", "Password does not contain white spaces");
                request.getRequestDispatcher("userInfo.jsp").forward(request, response);
                return;

            }
            if (Validation.isValidPasswordLength(password) == false) {
                request.setAttribute("errorPassLength", "Password must have at least 6 characters");
                request.getRequestDispatcher("userInfo.jsp").forward(request, response);
                return;

            }


            // Create UserProfile object
            User userProfile = new User();
            userProfile.setUsername(username);
            userProfile.setPassword(password);
            userProfile.setFullName(fullName);
            userProfile.setDob(dob);
            userProfile.setPhone(phone);
            userProfile.setEmail(email);
            userProfile.setGender(gender);


            // Update user profile
            boolean success = UserDAO.updateUserProfile(userProfile);

            if (success) {
                request.setAttribute("message", "User profile updated successfully.");
                request.getRequestDispatcher("result.jsp").forward(request, response);

            } else {
                request.setAttribute("message", "Failed to update user profile. Please try again.");
                request.getRequestDispatcher("error.jsp").forward(request, response);

            }
        

        // Forward the request to the appropriate JSP
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

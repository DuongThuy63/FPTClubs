/*
 */
package controller;

import dao.ClubDAO;
import dao.UserDAO;
import dao.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author minhdq
 */
public class AddMemberServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddMemberServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddMemberServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add_member.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));

        String[] selectedUsers = request.getParameterValues("selectedUsers");

        System.out.println("Selected Users: " + Arrays.toString(selectedUsers));
        System.out.println("Club ID: " + clubId);

        boolean usersExist = false;

        if (selectedUsers != null) {
            for (String username : selectedUsers) {
                if (Validation.checkIfExistClubMembership(username, clubId)) {
                    usersExist = true;
                    break;
                }
            }
            if (!usersExist) {
                ClubDAO.addMembersToClub(clubId, selectedUsers);
                System.out.println("Added " + Arrays.toString(selectedUsers));
                request.setAttribute("message", "Added User: " + Arrays.toString(selectedUsers));
                request.getRequestDispatcher("result.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Users are already members of the club.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "No users selected.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

            

//        response.sendRedirect("add_member?club_id=" + clubId);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

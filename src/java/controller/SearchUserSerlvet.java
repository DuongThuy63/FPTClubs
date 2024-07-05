/*
 */
package controller;

import dao.ClubDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import dao.UserDAO;
import java.util.Arrays;

/**
 *
 * @author minhdq
 */
public class SearchUserSerlvet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchMemberSerlvet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchMemberSerlvet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("search");
        String clubId = request.getParameter("club_id");

        ArrayList<User> list = UserDAO.searchByUsername(username);
        System.out.println(username);
        for (User us : list) {
            System.out.println(us.getUsername());

        }
        if (list == null) {
            System.out.println("none");
        } else {
            request.setAttribute("searchList", list);
            request.setAttribute("clubId", clubId);
            request.getRequestDispatcher("search_member.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

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
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author minhdq
 */
public class ViewYourClubsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewYourClubsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewYourClubsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        request.setAttribute("username", user.getUsername());

        // Get the list of club IDs associated with the user
        ArrayList<Integer> clubIds = ClubDAO.getClubIdsFromUser(user);

        // Create a list to hold the club objects
        ArrayList<Club> clubList = new ArrayList<>();

        // Fetch club information for each club ID
        for (int clubId : clubIds) {
            Club club = ClubDAO.getById(clubId).get(0);
            clubList.add(club);
        }

        request.setAttribute("clubList", clubList);

        request.getRequestDispatcher("your_club.jsp").forward(request, response);
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

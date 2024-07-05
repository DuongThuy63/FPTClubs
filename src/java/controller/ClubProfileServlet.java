package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Club;
import dao.ClubDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import model.User;

public class ClubProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        request.setAttribute("username", user.getUsername());
        ArrayList<Club> clubList = ClubDAO.getById((ClubDAO.getClubIdsFromUser(user).get(0)));
        
        if (clubList != null){
            request.setAttribute("club", clubList.get(0));
            request.getRequestDispatcher("Profileclub.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Y");
            request.getRequestDispatcher("Profileclub.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            String clubName = request.getParameter("clubName");
            String clubDescription = request.getParameter("clubDescription");
            String clubLogo = request.getParameter("clubLogo");
            String clubEmail = request.getParameter("clubEmail");
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            Club club = new Club(clubId, clubName, clubDescription, clubLogo, clubEmail, categoryId);

            boolean updated = ClubDAO.updateClub(club);
            if (updated) {
//                response.sendRedirect(request.getContextPath() + "/clubProfile?club_id=" + clubId);
                request.setAttribute("message", "Club profile updated successfully.");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            } else {
//                response.getWriter().println("Failed to update club profile.");
                request.setAttribute("message", "FFailed to update club profile.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if ("delete".equals(action)) {
            boolean deleted = ClubDAO.deleteClub(clubId);
            if (deleted) {
//                response.sendRedirect(request.getContextPath() + "/clubs"); // Chuyển hướng đến trang danh sách các club
                request.setAttribute("message", "Club deleted successfully.");
                request.getRequestDispatcher("result.jsp").forward(request, response);
            } else {
//                response.getWriter().println("Failed to delete club.");
                request.setAttribute("message", "Failed to delete club.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}

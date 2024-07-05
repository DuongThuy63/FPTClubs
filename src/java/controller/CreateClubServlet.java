/*
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Club;
import dao.ClubDAO;
import dao.Validation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;

/**
 *
 * @author minhdq
 */
public class CreateClubServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateClubServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateClubServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("create_club.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clbName = request.getParameter("name");
        String clbDes = request.getParameter("description");
        String clbEmail = request.getParameter("email");
        int clbCate = Integer.parseInt(request.getParameter("category"));
//        Part part = request.getPart("fileImage");
        
        HttpSession session = request.getSession();
        User us = (User) session.getAttribute("account");
        
            if (Validation.checkUniqueClub(clbName) == false) {
            request.setAttribute("errorName", "This name has been used");
            request.getRequestDispatcher("create_club.jsp").forward(request, response);
            return;

        }
        if (Validation.isValidClubNameLength(clbName) == false) {
            request.setAttribute("errorNameLength", "Club's name must have at least 3 characters");
            request.getRequestDispatcher("create_club.jsp").forward(request, response);
            return;
        }

//        String realPath = request.getServletContext().getRealPath("/images/club");
//        String fileName = "";
//        if (part != null) {
//            fileName = extractFileName(part);
//            String filePath = realPath + File.separator + fileName;
//
//            try (OutputStream out = new FileOutputStream(filePath)) {
//                // Copy the content of the part to the output stream
//                InputStream fileContent = part.getInputStream();
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = fileContent.read(buffer)) != -1) {
//                    out.write(buffer, 0, length);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            Club clb = new Club(clbName, clbDes, clbEmail, null, clbCate);
            clb = ClubDAO.createClub(clb);
            ClubDAO.addAsAmin(clb.getClubId(), us.getUsername());
            request.setAttribute("message", clb.getClubName() + " Create Succecced ");
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } catch (Exception ex) {
//            request.setAttribute("error", "Create failed"); 
//            request.getRequestDispatcher("create_club.jsp").forward(request, response);
            request.setAttribute("message", "error: " + ex);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }

//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] tokens = contentDisp.split(";");
//        for (String token : tokens) {
//            if (token.trim().startsWith("filename")) {
//                return token.substring(token.indexOf("=") + 2, token.length() - 1);
//            }
//        }
//        return "";
//    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

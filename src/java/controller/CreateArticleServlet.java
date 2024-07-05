/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticleDAO;
import dao.ClubDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Article;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.User;

//@WebServlet("/CreatePostServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

/**
 *
 * @author DuongPhan
 */
public class CreateArticleServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createArticle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createArticle at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("create_post.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // Lấy tệp hình ảnh được tải lên
        Part part = request.getPart("fileImage");

        // Lưu hình ảnh vào thư mục trên máy chủ
        String realPath = request.getServletContext().getRealPath("/images/article");
        String fileName = "";
        if (part != null) {
            fileName = extractFileName(part);
            String filePath = realPath + File.separator + fileName;

            try (OutputStream out = new FileOutputStream(filePath)) {
                // Copy the content of the part to the output stream
                InputStream fileContent = part.getInputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fileContent.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        HttpSession session = request.getSession();

        User us = (User) session.getAttribute("account");
        String id = request.getParameter("articleId");

        // Tạo một đối tượng Article từ dữ liệu nhận được
        Article article = new Article();
        article.setUsername(us.getUsername());
        article.setClubID(Integer.parseInt(id));
        article.setTitle(title);
        article.setContent(content);
        article.setImage(fileName); // Lưu tên tệp hình ảnh vào article

        // Lưu bài viết vào cơ sở dữ liệu (giả sử đã có một lớp DAO để thực hiện điều này)
        ArticleDAO.createArticle(article);
        session.setAttribute("newArticle", article);

        // Chuyển hướng người dùng đến trang hiển thị bài viết mới tạo
        response.sendRedirect("article?article_id=" + article.getArticleID() + "&club_id=" + id);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

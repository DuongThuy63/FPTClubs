package controller;

import dao.ArticleDAO;
import dao.ClubDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Article;
import model.Comment;
import model.User;

public class ArticleServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ArticleServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
//
        Article art = (Article) session.getAttribute("newArticle");
        if (art != null) {
            Article a = ArticleDAO.getPostById(art.getArticleID());
            request.setAttribute("article_post", a);

        }
//        ArrayList<Comment> listAllComment = ClubDAO.getAllCommentByArticleID(4);
//        int countAllComment = listAllComment.size();
//        ArrayList<User> listAllAcount = UserDAO.getAllAccount();

//        request.setAttribute("listAllComment", listAllComment);
//        request.setAttribute("listAllAcount", listAllAcount);
//        request.setAttribute("countAllComment", countAllComment);
        request.getRequestDispatcher("article.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

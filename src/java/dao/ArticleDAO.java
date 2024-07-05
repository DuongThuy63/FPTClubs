/*
 */
package dao;

import static dao.DBInfo.dbPass;
import static dao.DBInfo.dbUrl;
import static dao.DBInfo.dbUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Article;
import model.Comment;

/**
 *
 * @author minhdq
 */
public class ArticleDAO implements DBInfo{
    
    
         
    public  static ArrayList<Article>  getListArticle(int clubID){        
            String query = "select * from Article where ClubID = ?";
            try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, clubID);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Article> list = new ArrayList<>();
            while(rs.next()){
                Article s = new Article(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
                list.add(s);
            }
            
            return list;
        }catch(Exception e){
            
        }
        return null;
    }         
    public  static Article createArticle(Article c) {
        new Article();
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement("Insert into Article(Username, ClubID, Title ,Content, Image)output inserted.ArticleID values(?,?,?,?,?)");
            stmt.setString(1, c.getUsername());
            stmt.setInt(2, c.getClubID());
            stmt.setString(3, "" + c.getTitle());
            stmt.setString(4, c.getContent());
            stmt.setString(5, c.getImage());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                c.setArticleID(rs.getInt(1));
            } else {
                throw new RuntimeException("Insert fail..");
            }
            con.close();
        } catch (SQLException ex) {
            
            throw new RuntimeException(ex.getMessage());
        }
        return c;
    }
    
     public static Article getPostById(int articleId) {
        String query = "SELECT * FROM Article WHERE ArticleID = ?";
        Article article = null;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, articleId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("Username");
                    int clubID = rs.getInt("ClubID");
                    String title = rs.getString("Title");
                    String content = rs.getString("Content");
                    String image = rs.getString("Image");
                    Date dateCr = rs.getDate("DateCreate");

                    article = new Article(articleId,  username, clubID, title, content, image, dateCr);
                    return article;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
     public static ArrayList<Article> getAllPosts() {
    String query = "SELECT * FROM Article";
    ArrayList<Article> articles = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
        PreparedStatement statement = conn.prepareStatement(query);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int articleId = rs.getInt("ArticleID");
                String username = rs.getString("Username");
                int clubID = rs.getInt("ClubID");
                String title = rs.getString("Title");
                String content = rs.getString("Content");
                String image = rs.getString("Image");
                Date dateCr = rs.getDate("DateCreate");

                Article article = new Article(articleId,  username, clubID, title, content, image, dateCr);
                articles.add(article);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return articles;
}

     public Comment createComment(Comment c) {
          new Comment();
        String query = "INSERT INTO Comment(Username, ArticleID,  Content)\n" +
                        "VALUES (?, ?, ?)";
                        
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, c.getUsername());
            stmt.setInt(2, c.getArticleID());
            stmt.setString(3, "" + c.getContent());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                c.setCommentID(rs.getInt(1));
            } else {
                throw new RuntimeException("Insert fail..");
            }
            con.close();
        } catch (SQLException ex) {
          
        }
        return c;
    }
      public Comment getNewComment(String username, int articleID) {
        String query = "select * from Comment where Username = ? and ArticleID = ? ";
        Comment comment= null;
        
        
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setInt(2, articleID);
             try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String content = rs.getString("content");
                    Date dateCr = rs.getDate("dateCreate");
                    comment = new Comment(username, articleID, content, dateCr);
                    return comment;
                }
            }    
        } catch (Exception e) {
        }
        return null;
    }
    
      public static ArrayList<Comment> getAllCommentByArticleID(int articleID) {
        String query = "select * from Comment where [ArticleID] = ?";
         try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass)){
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, articleID);
            ArrayList<Comment> list = new ArrayList<>();
            
             try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    String content = rs.getString("content");
                    Date dateCr = rs.getDate("dateCreate");
                    Comment comment = new Comment(username, articleID, content, dateCr);
                    list.add(comment);
                }
                return list;
            }    
        } catch (Exception e) {
        }
        return null;
    }
    
}

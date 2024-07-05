/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author DuongPhan
 */
public class Comment {
    private int commentID;
    private String username;
    private int articleID;
    private String content;
    private Date date_Create;

    public Comment(String username, int articleID, String content, Date date_Create) {
        this.username = username;
        this.articleID =  articleID;
        this.content = content;
        this.date_Create = date_Create;
    }
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    public Comment() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate_Create() {
        return date_Create;
    }

    public void setDate_Create(Date date_Create) {
        this.date_Create = date_Create;
    }

    @Override
    public String toString() {
        return "Comment{" + "username=" + username + ", articleID=" + articleID + ", content=" + content + ", date_Create=" + date_Create + '}';
    }
    
    
    
    
    
}

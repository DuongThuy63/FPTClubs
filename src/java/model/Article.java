/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DuongPhan
 */
import java.util.Date;

public class Article {
    private int articleID;
    private String username;
    private int ClubID;
    private String title;
    private String content;
    private String image;
    private Date dateCreate;
    private Date timeApprove;

    // Constructor
    public Article() {
    }

    public Article(int articleID, String username, int ClubID, String title, String content, String image, Date dateCreate, Date timeApprove) {
        this.articleID = articleID;
        this.username = username;
        this.ClubID = ClubID;
        this.title = title;
        this.content = content;
        this.image = image;
        this.dateCreate = dateCreate;
        this.timeApprove = timeApprove;
    }

    public Article(int articleID, String username, int ClubID, String title, String content, String image, Date dateCreate) {
        this.articleID = articleID;
        this.username = username;
        this.ClubID = ClubID;
        this.title = title;
        this.content = content;
        this.image = image;
        this.dateCreate = dateCreate;
    }

    public Article(int articleID, String username, int ClubID, String title, String content, String image) {
        this.articleID = articleID;
        this.username = username;
        this.ClubID = ClubID;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getTimeApprove() {
        return timeApprove;
    }

    public void setTimeApprove(Date timeApprove) {
        this.timeApprove = timeApprove;
    }

    @Override
    public String toString() {
        return "Article{" + "articleID=" + articleID + ", username=" + username + ", ClubID=" + ClubID + ", title=" + title + ", content=" + content + ", image=" + image + ", dateCreate=" + dateCreate + ", timeApprove=" + timeApprove + '}';
    }
    
    
    
}
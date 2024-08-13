/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class Comment {
    private String idComment;
    private Movie idFilm;
    private User idAccount;
    private boolean status;
    private String content;
    private Date dateCreate;
    private int rating;
    public Comment() {
    }

    public Comment(String idComment, Movie idFilm, User idAccount, boolean status, String content, Date dateCreate, int rating) {
        this.idComment = idComment;
        this.idFilm = idFilm;
        this.idAccount = idAccount;
        this.status = status;
        this.content = content;
        this.dateCreate = dateCreate;
        this.rating = rating;
    }

  

   

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public Movie getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Movie idFilm) {
        this.idFilm = idFilm;
    }

    public User getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(User idAccount) {
        this.idAccount = idAccount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

  


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    
}

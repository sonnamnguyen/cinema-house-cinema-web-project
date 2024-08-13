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
public class Movie {
    private String idMovie;
    private String movieName;
    private String summary;
    private String director;
    private String actor;
    private String language;
    private String trailer;
    private String ageLimit;
    private String coverImage;
    private String category;
    private String nationalOrigin;
    private Date effectiveDateFrom;
    private Date effectiveDateTo;
    private boolean status;
    private int duration;

  


    public Movie(String idMovie, String movieName, String summary, String director, String actor, String language, String trailer, String ageLimit, String coverImage, String category, String nationalOrigin, Date effectiveDateFrom, Date effectiveDateTo, boolean status, int duration) {
        this.idMovie = idMovie;
        this.movieName = movieName;
        this.summary = summary;
        this.director = director;
        this.actor = actor;
        this.language = language;
        this.trailer = trailer;
        this.ageLimit = ageLimit;
        this.coverImage = coverImage;
        this.category = category;
        this.nationalOrigin = nationalOrigin;
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.status = status;
        this.duration = duration;
    }

     public Movie(String idMovie, String movieName, String coverImage, String category, String nationalOrigin) {
        this.idMovie = idMovie;
        this.movieName = movieName;
        this.coverImage = coverImage;
        this.category = category;
        this.nationalOrigin = nationalOrigin;
    }


    public Movie(String idMovie) {
        this.idMovie = idMovie;

    }

    public Movie(String idMovie, String movieName) {
        this.idMovie = idMovie;
        this.movieName = movieName;
    }

    public Movie() {
    }

    
    
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNationalOrigin() {
        return nationalOrigin;
    }

    public void setNationalOrigin(String nationalOrigin) {
        this.nationalOrigin = nationalOrigin;
    }

    public Date getEffectiveDateFrom() {
        return effectiveDateFrom;
    }

    public void setEffectiveDateFrom(Date effectiveDateFrom) {
        this.effectiveDateFrom = effectiveDateFrom;
    }

    public Date getEffectiveDateTo() {
        return effectiveDateTo;
    }

    public void setEffectiveDateTo(Date effectiveDateTo) {
        this.effectiveDateTo = effectiveDateTo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

 
    
    
}

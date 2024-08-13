/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author admin
 */
public class MovieScreeningSession {
    
    private String idMoviesession;
    private Movie idMovie;
    private Date screeningDate;
    private Time startTime;
    private Time endTime;
    private CinemaRoom idMovieRoom;
    private boolean status;

    public MovieScreeningSession(String idMoviesession, Movie idMovie, Date screeningDate, Time startTime, Time endTime, CinemaRoom idMovieRoom, boolean status) {
        this.idMoviesession = idMoviesession;
        this.idMovie = idMovie;
        this.screeningDate = screeningDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idMovieRoom = idMovieRoom;
        this.status = status;
    }

    public MovieScreeningSession(String idMoviesession, Movie idMovie, Date screeningDate, CinemaRoom idMovieRoom) {
        this.idMoviesession = idMoviesession;
        this.idMovie = idMovie;
        this.screeningDate = screeningDate;
        this.idMovieRoom = idMovieRoom;
    }
    
    

    public MovieScreeningSession(String idMoviesession, Movie idMovie) {
        this.idMoviesession = idMoviesession;
        this.idMovie = idMovie;
    }

    public MovieScreeningSession(String idMoviesession) {
        this.idMoviesession = idMoviesession;
    }

    public MovieScreeningSession(String idMoviesession, Movie idMovie, CinemaRoom idMovieRoom) {
        this.idMoviesession = idMoviesession;
        this.idMovie = idMovie;
        this.idMovieRoom = idMovieRoom;
    }

   

  
    

    public String getIdMoviesession() {
        return idMoviesession;
    }

    public void setIdMoviesession(String idMoviesession) {
        this.idMoviesession = idMoviesession;
    }

    public Movie getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Movie idMovie) {
        this.idMovie = idMovie;
    }

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

   

    public CinemaRoom getIdMovieRoom() {
        return idMovieRoom;
    }

    public void setIdMovieRoom(CinemaRoom idMovieRoom) {
        this.idMovieRoom = idMovieRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
    
    
}

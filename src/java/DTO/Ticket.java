/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author admin
 */

public class Ticket {
    private String idTicket;
    private MovieScreeningSession idMovieScreeningSession;
    private Seat idSeat;   
    private boolean status;

    public Ticket(String idTicket, MovieScreeningSession idMovieScreeningSession, Seat idSeat, boolean status) {
        this.idTicket = idTicket;
        this.idMovieScreeningSession = idMovieScreeningSession;
        this.idSeat = idSeat;
        this.status = status;
    }

    public Ticket(String idTicket, MovieScreeningSession idMovieScreeningSession, Seat idSeat) {
        this.idTicket = idTicket;
        this.idMovieScreeningSession = idMovieScreeningSession;
        this.idSeat = idSeat;
    }

    public Ticket() {
    }

   
    public Ticket(String idTicket, MovieScreeningSession idMovieScreeningSession) {
        this.idTicket = idTicket;
        this.idMovieScreeningSession = idMovieScreeningSession;
    }

    
    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public MovieScreeningSession getIdMovieScreeningSession() {
        return idMovieScreeningSession;
    }

    public void setIdMovieScreeningSession(MovieScreeningSession idMovieScreeningSession) {
        this.idMovieScreeningSession = idMovieScreeningSession;
    }

    public Seat getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Seat idSeat) {
        this.idSeat = idSeat;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
    
}


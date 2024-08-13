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
public class Seat {
    private String idSeat;
    private SeatCategory idSeatCategory;
    private CinemaRoom idCinemaRoom;
    private String row;
    private int column;
    private boolean status;

    public Seat(String idSeat, SeatCategory idSeatCategory, CinemaRoom idCinemaRoom, String row, int column, boolean status) {
        this.idSeat = idSeat;
        this.idSeatCategory = idSeatCategory;
        this.idCinemaRoom = idCinemaRoom;
        this.row = row;
        this.column = column;
        this.status = status;
    }

    public Seat(String idSeat, CinemaRoom idCinemaRoom) {
        this.idSeat = idSeat;
        this.idCinemaRoom = idCinemaRoom;
    }

    public Seat(String idSeat) {
        this.idSeat = idSeat;
    }

    public String getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(String idSeat) {
        this.idSeat = idSeat;
    }

    public SeatCategory getIdSeatCategory() {
        return idSeatCategory;
    }

    public void setIdSeatCategory(SeatCategory idSeatCategory) {
        this.idSeatCategory = idSeatCategory;
    }

    public CinemaRoom getIdCinemaRoom() {
        return idCinemaRoom;
    }

    public void setIdCinemaRoom(CinemaRoom idCinemaRoom) {
        this.idCinemaRoom = idCinemaRoom;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   

    
   
    
}

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
public class CinemaRoom {
    private String idCinemaRoom;
    private Cinema idCinema;
    private RoomCategory idRoomCategory;
    private String roomName;
    private boolean status;

    public CinemaRoom(String idCinemaRoom) {
        this.idCinemaRoom = idCinemaRoom;
    }

  
 
    
    
    public CinemaRoom(String idCinemaRoom, Cinema idCinema, RoomCategory idRoomCategory, String roomName, boolean status) {
        this.idCinemaRoom = idCinemaRoom;
        this.idCinema = idCinema;
        this.idRoomCategory = idRoomCategory;
        this.roomName = roomName;
        this.status = status;
    }

    public CinemaRoom(String idCinemaRoom, Cinema idCinema, String roomName) {
        this.idCinemaRoom = idCinemaRoom;
        this.idCinema = idCinema;
        this.roomName = roomName;
    }

    public CinemaRoom(String idCinemaRoom, String roomName) {
        this.idCinemaRoom = idCinemaRoom;
        this.roomName = roomName;
    }

    

 
    

    public String getIdCinemaRoom() {
        return idCinemaRoom;
    }

    public void setIdCinemaRoom(String idCinemaRoom) {
        this.idCinemaRoom = idCinemaRoom;
    }

    public Cinema getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(Cinema idCinema) {
        this.idCinema = idCinema;
    }

    public RoomCategory getIdRoomCategory() {
        return idRoomCategory;
    }

    public void setIdRoomCategory(RoomCategory idRoomCategory) {
        this.idRoomCategory = idRoomCategory;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
    
    
}

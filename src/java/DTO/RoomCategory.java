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
public class RoomCategory {
    private String idRoomCategory;
    private String roomName;
    private boolean status;

    public RoomCategory(String idRoomCategory, String roomName, boolean status) {
        this.idRoomCategory = idRoomCategory;
        this.roomName = roomName;
        this.status = status;
    }

    public String getIdRoomCategory() {
        return idRoomCategory;
    }

    public void setIdRoomCategory(String idRoomCategory) {
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

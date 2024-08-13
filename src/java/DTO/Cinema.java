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
public class Cinema {
    private String idCinema;
    private String cinemaName;
    private String address;
    private boolean status;

    public Cinema(String idCinema, String cinemaName, String address, boolean status) {
        this.idCinema = idCinema;
        this.cinemaName = cinemaName;
        this.address = address;
        this.status = status;
    }

    public Cinema(String idCinema, String cinemaName) {
        this.idCinema = idCinema;
        this.cinemaName = cinemaName;
    }

 

    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   
   
    
}

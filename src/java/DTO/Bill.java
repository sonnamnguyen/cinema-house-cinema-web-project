/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Bill {
    private String idBill;
    private Ticket idTicket;
     private User idAccount;
    private Date dateCreated;
   private Time timeCreated;
   private float totalMoney;
   private boolean status;


    public Bill(String idBill, Ticket idTicket, User idAccount, Date dateCreated, Time timeCreated, float totalMoney, boolean status) {
        this.idBill = idBill;
        this.idTicket = idTicket;
        this.idAccount = idAccount;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    public Bill() {
        this.idBill = idBill;
        this.idTicket = idTicket;
        this.idAccount = idAccount;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.totalMoney = totalMoney;
        this.status = status;
    }
    
    

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public Ticket getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Ticket idTicket) {
        this.idTicket = idTicket;
    }

    public User getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(User idAccount) {
        this.idAccount = idAccount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Time getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Time timeCreated) {
        this.timeCreated = timeCreated;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
 

  

    
   
}

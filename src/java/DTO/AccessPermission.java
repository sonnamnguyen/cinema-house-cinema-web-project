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
public class AccessPermission {
    private String idPermission;
    private User idAccount;
    private Role idRole;

    public AccessPermission(String idPermission, User idAccount, Role idRole) {
        this.idPermission = idPermission;
        this.idAccount = idAccount;
        this.idRole = idRole;
    }

    public String getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(String idPermission) {
        this.idPermission = idPermission;
    }

    public User getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(User idAccount) {
        this.idAccount = idAccount;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    
    
    
}

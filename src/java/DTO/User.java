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
public class User {

    private String idAccount;
    private String avatar;
    private String accountName;
    private String firstname;
    private String lastname;
    private String gender;
    private String address;
    private String phoneNumber;
    private Date yob;
    private String email;
    private boolean receiveEmail;
    private String password;
    private String verificationCode;
    private Date effectiveTime;
    private boolean authentication;
    private boolean isAdmin;
    public User() {
    }

    public User(String idAccount, String accountName, String firstname, String lastname, String gender, String address, String phoneNumber, Date yob, String email, boolean receiveEmail, String password) {
        this.idAccount = idAccount;
        this.accountName = accountName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.email = email;
        this.receiveEmail = receiveEmail;
        this.password = password;
    }

    public User(String idAccount, String accountName, String firstname, String lastname, String address, String phoneNumber, Date yob, String email, boolean receiveEmail, String password, String verificationCode, Date effectiveTime, boolean authentication) {
        this.idAccount = idAccount;
        this.accountName = accountName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.email = email;
        this.receiveEmail = receiveEmail;
        this.password = password;
        this.verificationCode = verificationCode;
        this.effectiveTime = effectiveTime;
        this.authentication = authentication;
    }

    public User(String idAccount, String avatar, String accountName, String firstname, String lastname, String gender, String address, String phoneNumber, Date yob, String email, boolean receiveEmail, String password, String verificationCode, Date effectiveTime, boolean authentication, boolean isAdmin) {
        this.idAccount = idAccount;
        this.avatar = avatar;
        this.accountName = accountName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.email = email;
        this.receiveEmail = receiveEmail;
        this.password = password;
        this.verificationCode = verificationCode;
        this.effectiveTime = effectiveTime;
        this.authentication = authentication;
        this.isAdmin = isAdmin;
    }

    

    public User(String idAccount, String avatar, String accountName, String firstname, String lastname, String gender, String address, String phoneNumber, Date yob, String email, boolean receiveEmail, String password, boolean isAdmin) {
   this.idAccount = idAccount;
        this.avatar = avatar;
        this.accountName = accountName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.email = email;
        this.receiveEmail = receiveEmail;
        this.password = password;
         this.isAdmin = isAdmin;
    }

    public User(String idAccount, String accountName, String firstname, String lastname, String gender, String address, String phoneNumber, Date yob, String email, boolean receiveEmail) {
        this.idAccount = idAccount;
        this.accountName = accountName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.yob = yob;
        this.email = email;
        this.receiveEmail = receiveEmail;
    }

    public User(String idAccount, String accountName, String phoneNumber, String email) {
        this.idAccount = idAccount;
        this.accountName = accountName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(String idAccount, String accountName) {
        this.idAccount = idAccount;
        this.accountName = accountName;
    }

    
    public User(String idAccount) {
        this.idAccount = idAccount;
    }
  

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    

    public boolean isReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(boolean receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getYob() {
        return yob;
    }

    public void setYob(Date yob) {
        this.yob = yob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

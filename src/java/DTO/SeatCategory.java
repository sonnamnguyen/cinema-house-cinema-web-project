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
public class SeatCategory {
   private String idCategory;
   private String categoryName;
   private boolean status;
   private int price;

    public SeatCategory(String idCategory) {
        this.idCategory = idCategory;
    }

   
    public SeatCategory(String idCategory, String categoryName, boolean status, int price) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.status = status;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    
    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    } 
}

    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CinemaRoom;
import DTO.Seat;
import DTO.SeatCategory;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ADMIN
 */
public class SeatDAO {
    
    
    
  public List<Seat> selectAllSeatsByCinemaRoomName(String roomname) throws SQLException, ClassNotFoundException {
    List<Seat> seats = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        con = DBUtil.getConnection();
        String sql = "SELECT s.id_seat, s.row, s.[column], s.status, sc.id_seatcategory, sc.categoryname, sc.price, c.id_cinemaroom, c.roomname " +
                     "FROM seat s " +
                     "JOIN seatcategory sc ON s.id_seatcategory = sc.id_seatcategory " +
                     "JOIN cinemaroom c ON s.id_cinemaroom = c.id_cinemaroom " +
                     "WHERE c.roomname = ?";
        
        stm = con.prepareStatement(sql);
        stm.setString(1, roomname);
        rs = stm.executeQuery();
        
        while (rs.next()) {
            String idSeat = rs.getString("id_seat");
            String row = rs.getString("row");
            int column = rs.getInt("column");
            boolean status = rs.getBoolean("status");
            
            String idCategory = rs.getString("id_seatcategory");
            String categoryName = rs.getString("categoryname");
            int price = rs.getInt("price");
            SeatCategory seatCategory = new SeatCategory(idCategory, categoryName, true, price);
            
            String idCinemaRoom = rs.getString("id_cinemaroom");
            String roomName = rs.getString("roomname");
            CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, roomName);
            
            // Construct Seat object
            Seat seat = new Seat(idSeat, seatCategory, cinemaRoom, row, column, status);
            seats.add(seat);
        }
    } finally {
        // Close resources in a finally block to ensure they are always closed
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    return seats;
}
public List<Seat> getAllSeatsByBillId(String billId) throws SQLException, ClassNotFoundException {
    List<Seat> seats = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT s.id_seat "
                       + "FROM seat s "
                       + "INNER JOIN ticket t ON s.id_seat = t.id_seat "
                       + "INNER JOIN bill b ON t.id_ticket = b.id_ticket "
                       + "WHERE b.id_bill = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, billId);
            rs = stm.executeQuery();

            while (rs.next()) {
                String idSeat = rs.getString("id_seat");
                Seat seat = new Seat(idSeat);
                seats.add(seat);
            }
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    return seats;
}

}




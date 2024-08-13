/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.User;
import DTO.Bill;
import DTO.CinemaRoom;
import DTO.Movie;
import DTO.MovieScreeningSession;
import DTO.Seat;
import DTO.Ticket;
import Database.DBUtil;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class BillDAO {

    public List<Bill> selectAllBill(int offset, int limit) throws SQLException, ClassNotFoundException {
    List<Bill> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM ("
                    + "SELECT b.id_bill, b.datecreated, b.timecreated, b.totalmoney, b.status, "
                    + "t.id_ticket, "
                    + "a.id_account, a.accountname, a.phonenumber, a.email, "
                    + "ms.id_moviescreeningsession AS ms_id_moviescreeningsession, "
                    + "m.id_movie, m.moviename, "
                    + "ROW_NUMBER() OVER (PARTITION BY b.id_bill ORDER BY b.datecreated DESC, b.timecreated DESC) as rn "
                    + "FROM bill b "
                    + "INNER JOIN account a ON b.id_account = a.id_account "
                    + "INNER JOIN ticket t ON b.id_ticket = t.id_ticket "
                    + "INNER JOIN moviescreeningsession ms ON t.id_moviescreeningsession = ms.id_moviescreeningsession "
                    + "INNER JOIN movie m ON ms.id_movie = m.id_movie "
                    + ") sub "
                    + "WHERE sub.rn = 1 "
                    + "ORDER BY sub.datecreated DESC, sub.timecreated DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            stm = con.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, limit);
        }
        rs = stm.executeQuery();

        while (rs.next()) {
            String idBill = rs.getString("id_bill");
            Date dateCreated = rs.getDate("datecreated");
            Time timeCreated = rs.getTime("timecreated");
            float totalMoney = rs.getFloat("totalmoney");
            boolean status = rs.getBoolean("status");

            String idMovie = rs.getString("id_movie");
            String movieName = rs.getString("moviename");
            Movie movie = new Movie(idMovie, movieName);

            String idMovieScreeningSession = rs.getString("ms_id_moviescreeningsession");
            MovieScreeningSession movieSession = new MovieScreeningSession(idMovieScreeningSession, movie);

            String idTicket = rs.getString("id_ticket");
            Ticket ticket = new Ticket(idTicket, movieSession);

            String idAccount = rs.getString("id_account");
            String accountName = rs.getString("accountname");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phonenumber");
            User account = new User(idAccount, accountName, phoneNumber, email);

            Bill bill = new Bill(idBill, ticket, account, dateCreated, timeCreated, totalMoney, status);
            result.add(bill);
        }

    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        }
    }

    return result;
}


    public List<Bill> selectAllBillForUser(String userId, int offset, int limit) throws SQLException, ClassNotFoundException {
    List<Bill> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM ("
                    + "SELECT b.id_bill, b.datecreated, b.timecreated, b.totalmoney, b.status, "
                    + "t.id_ticket, "
                    + "a.id_account, a.accountname, a.phonenumber, a.email, "
                    + "cm.id_cinemaroom, cm.roomname, "
                    + "ms.id_moviescreeningsession AS ms_id_moviescreeningsession, "
                    + "m.id_movie, m.moviename, "
                    + "ROW_NUMBER() OVER (PARTITION BY b.id_bill ORDER BY b.datecreated DESC, b.timecreated DESC) as rn "
                    + "FROM bill b "
                    + "INNER JOIN account a ON b.id_account = a.id_account "
                    + "INNER JOIN ticket t ON b.id_ticket = t.id_ticket "
                    + "INNER JOIN moviescreeningsession ms ON t.id_moviescreeningsession = ms.id_moviescreeningsession "
                    + "INNER JOIN movie m ON ms.id_movie = m.id_movie "
                    + "INNER JOIN cinemaroom cm ON ms.id_cinemaroom = cm.id_cinemaroom "
                    + "WHERE a.id_account = ? "
                    + ") sub "
                    + "WHERE sub.rn = 1 "
                    + "ORDER BY sub.datecreated DESC, sub.timecreated DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            stm = con.prepareStatement(sql);
            stm.setString(1, userId);
            stm.setInt(2, offset);
            stm.setInt(3, limit);
        }
        rs = stm.executeQuery();

        while (rs.next()) {
            String idBill = rs.getString("id_bill");
            Date dateCreated = rs.getDate("datecreated");
            Time timeCreated = rs.getTime("timecreated");
            float totalMoney = rs.getFloat("totalmoney");
            boolean status = rs.getBoolean("status");

            String idMovie = rs.getString("id_movie");
            String movieName = rs.getString("moviename");
            Movie movie = new Movie(idMovie, movieName);

            String idCinemaroom = rs.getString("id_cinemaroom");
            String roomname = rs.getString("roomname");
            CinemaRoom cinemaroom = new CinemaRoom(idCinemaroom, roomname);

            String idMovieScreeningSession = rs.getString("ms_id_moviescreeningsession");
            MovieScreeningSession movieSession = new MovieScreeningSession(idMovieScreeningSession, movie, cinemaroom);

            String idTicket = rs.getString("id_ticket");
            Ticket ticket = new Ticket(idTicket, movieSession);

            String idAccount = rs.getString("id_account");
            String accountName = rs.getString("accountname");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phonenumber");
            User account = new User(idAccount, accountName, phoneNumber, email);

            Bill bill = new Bill(idBill, ticket, account, dateCreated, timeCreated, totalMoney, status);
            result.add(bill);
        }

    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            // Log or handle the exception as needed
            e.printStackTrace();
        }
    }

    return result;
}


    public int getTotalRecords() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(*) AS total FROM bill";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("total");
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
        return count;
    }

    public int getTotalRecordsByUser(String userId) throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(*) AS total FROM bill Where id_account = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("total");
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
        return count;
    }

    public List<Bill> searchInformation(String value, Date screenDate) throws SQLException, ClassNotFoundException {
        List<Bill> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT b.id_bill, b.datecreated, b.timecreated, b.totalmoney, b.status, "
                        + "t.id_ticket, t.id_moviescreeningsession, "
                        + "a.id_account, a.accountname, a.phonenumber, a.email, "
                        + "ms.id_moviescreeningsession, "
                        + "m.id_movie, m.moviename "
                        + "FROM bill b "
                        + "INNER JOIN account a ON b.id_account = a.id_account "
                        + "INNER JOIN ticket t ON b.id_ticket = t.id_ticket "
                        + "INNER JOIN moviescreeningsession ms ON t.id_moviescreeningsession = ms.id_moviescreeningsession "
                        + "INNER JOIN movie m ON ms.id_movie = m.id_movie "
                        + "WHERE (a.accountname LIKE ? OR a.phonenumber LIKE ? OR a.email LIKE ? OR m.moviename LIKE ?) "
                        + "OR b.datecreated = ?";

                stm = con.prepareStatement(sql);
                String queryValue = "%" + value + "%";
                stm.setString(1, queryValue);
                stm.setString(2, queryValue);
                stm.setString(3, queryValue);
                stm.setString(4, queryValue);

                stm.setDate(5, screenDate);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String idBill = rs.getString("id_bill");
                    Date dateCreated = rs.getDate("datecreated");
                    Time timeCreated = rs.getTime("timecreated");
                    float totalMoney = rs.getFloat("totalmoney");
                    boolean status = rs.getBoolean("status");
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    Movie movie = new Movie(idMovie, movieName);
                    String idMovieScreeningSession = rs.getString("id_moviescreeningsession");
                    String idTicket = rs.getString("id_ticket");
                    MovieScreeningSession movieSession = new MovieScreeningSession(idMovieScreeningSession, movie);
                    Ticket ticket = new Ticket(idTicket, movieSession);
                    String idAccount = rs.getString("id_account");
                    String accountName = rs.getString("accountname");
                    String email = rs.getString("email");
                    String phonenumber = rs.getString("phonenumber");
                    User account = new User(idAccount, accountName, phonenumber, email);
                    Bill bill = new Bill(idBill, ticket, account, dateCreated, timeCreated, totalMoney, status);
                    result.add(bill);
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
        return result;
    }

    public int update(String idBill, boolean status) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE bill SET status=? WHERE id_bill=?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, idBill);

                result = stm.executeUpdate();

                System.out.println("Executed: " + sql);
                System.out.println(result + " rows affected.");
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public int insertBill(Bill bill) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO bill (id_bill, id_ticket, id_account, datecreated, totalmoney, status, timecreated) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, bill.getIdBill());
                stm.setString(2, bill.getIdTicket().getIdTicket());
                stm.setString(3, bill.getIdAccount().getIdAccount());
                stm.setDate(4, bill.getDateCreated());
                stm.setFloat(5, bill.getTotalMoney());
                stm.setBoolean(6, bill.isStatus());
                stm.setTime(7, bill.getTimeCreated());
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

     public Bill getBillById(String billId) throws SQLException, ClassNotFoundException {

        Bill bill = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT b.id_bill, b.datecreated, b.timecreated, b.totalmoney, b.status, "
                        + "t.id_ticket, t.id_seat, t.id_moviescreeningsession, "
                        + "a.id_account, a.accountname, a.phonenumber, a.email, "
                        + "ms.id_moviescreeningsession, ms.screeningdate, "
                        + "mr.id_cinemaroom, mr.roomname, "
                        + "m.id_movie, m.moviename "
                        + "FROM bill b "
                        + "INNER JOIN account a ON b.id_account = a.id_account "
                        + "INNER JOIN ticket t ON b.id_ticket = t.id_ticket "
                        + "INNER JOIN seat s ON t.id_seat = s.id_seat "
                        + "INNER JOIN moviescreeningsession ms ON t.id_moviescreeningsession = ms.id_moviescreeningsession "
                        + "INNER JOIN cinemaroom mr ON ms.id_cinemaroom = mr.id_cinemaroom "
                        + "INNER JOIN movie m ON ms.id_movie = m.id_movie "
                        + "WHERE b.id_bill = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, billId);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String idBill = rs.getString("id_bill");
                    Date dateCreated = rs.getDate("datecreated");
                    Time timeCreated = rs.getTime("timecreated");
                    float totalMoney = rs.getFloat("totalmoney");
                    boolean status = rs.getBoolean("status");
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    Movie movie = new Movie(idMovie, movieName);
                    String idMovieScreeningSession = rs.getString("id_moviescreeningsession");
                    Date screendate = rs.getDate("screeningdate");
                    String idTicket = rs.getString("id_ticket");
                    String idSeat = rs.getString("id_seat");
                    Seat seat = new Seat(idSeat);
                    String idCinemaroom = rs.getString("id_cinemaroom");
                    String roomname = rs.getString("roomname");
                    CinemaRoom cinemaroom = new CinemaRoom(idCinemaroom, roomname);
                    MovieScreeningSession movieSession = new MovieScreeningSession(idMovieScreeningSession, movie, screendate, cinemaroom);
                    Ticket ticket = new Ticket(idTicket, movieSession, seat);
                    String idAccount = rs.getString("id_account");
                    String accountName = rs.getString("accountname");
                    String email = rs.getString("email");
                    String phonenumber = rs.getString("phonenumber");
                    User account = new User(idAccount, accountName, phonenumber, email);
                    bill = new Bill(idBill, ticket, account, dateCreated, timeCreated, totalMoney, status);
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
        return bill;
    }


    public User getUserByBillId(String billId) throws SQLException, ClassNotFoundException {
        User user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT a.id_account, a.accountname, a.phonenumber, a.email "
                        + "FROM bill b "
                        + "INNER JOIN account a ON b.id_account = a.id_account "
                        + "WHERE b.id_bill = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, billId);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String idAccount = rs.getString("id_account");
                    String accountName = rs.getString("accountname");
                    String email = rs.getString("email");
                    String phonenumber = rs.getString("phonenumber");
                    user = new User(idAccount, accountName, phonenumber, email);
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
        return user;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Cinema;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CinemaDAO {

    public List<Cinema> getAll() throws SQLException, ClassNotFoundException {
        List<Cinema> cinemas = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM cinema";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String idCinema = rs.getString("id_cinema");
                    String cinemaName = rs.getString("cinemaname");
                    String address = rs.getString("address");
                    boolean status = rs.getBoolean("status");

                    Cinema cinema = new Cinema(idCinema, cinemaName, address, status);
                    cinemas.add(cinema);
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
        return cinemas;
    }

    public List<Cinema> getAllByStatus(boolean status) throws SQLException, ClassNotFoundException {
        List<Cinema> cinemas = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM cinema WHERE status = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String idCinema = rs.getString("id_cinema");
                    String cinemaName = rs.getString("cinemaname");
                    String address = rs.getString("address");
                    boolean status1 = rs.getBoolean("status");
                    Cinema cinema = new Cinema(idCinema, cinemaName, address, status1);
                    cinemas.add(cinema);
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
        return cinemas;
    }
    public int insert(Cinema cinema) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO cinema (id_cinema, cinemaname, address, status) VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, cinema.getIdCinema());
                stm.setString(2, cinema.getCinemaName());
                stm.setString(3, cinema.getAddress());
                stm.setBoolean(4, cinema.isStatus());

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

    public int delete(Cinema cinema) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "DELETE FROM cinema WHERE id_cinema=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, cinema.getIdCinema());
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

    public int update(Cinema cinema) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE cinema SET cinemaname=?, address=?, status=? WHERE id_cinema=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, cinema.getCinemaName());
                stm.setString(2, cinema.getAddress());
                stm.setBoolean(3, cinema.isStatus());
                stm.setString(4, cinema.getIdCinema());

                System.out.println(sql);
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
    public Cinema selectById(String idCinema) throws SQLException, ClassNotFoundException {
    Cinema result = null;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM cinema WHERE id_cinema=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, idCinema);
            System.out.println(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String idCinematic = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                String address = rs.getString("address");
                boolean status = rs.getBoolean("status");
                result = new Cinema(idCinematic, cinemaName, address, status);
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

}

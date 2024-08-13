package DAO;

import DTO.Cinema;
import DTO.CinemaRoom;
import DTO.Movie;
import DTO.MovieScreeningSession;
import DTO.RoomCategory;
import DTO.Seat;
import DTO.SeatCategory;
import DTO.Ticket;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO {

    public List<String> selectByNameTop5(String name) throws SQLException, ClassNotFoundException {
        List<String> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 5 "
                        + "    a.accountname "
                        + "FROM "
                        + "    bill b "
                        + "INNER JOIN "
                        + "    account a ON b.id_account = a.id_account "
                        + "WHERE "
                        + "    a.accountname LIKE ? "
                        + "GROUP BY "
                        + "    a.accountname ";

                stm = con.prepareStatement(sql);
                stm.setString(1, name + "%");

                rs = stm.executeQuery();
                while (rs.next()) {

                    String accountName = rs.getString("accountname");
                    ketQua.add(accountName);
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
        return ketQua;
    }

    public int insertTicket(Ticket ticket) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO ticket (id_ticket, id_moviescreeningsession, id_seat, status) VALUES (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, ticket.getIdTicket());
                stm.setString(2, ticket.getIdMovieScreeningSession().getIdMoviesession());
                stm.setString(3, ticket.getIdSeat().getIdSeat());
                stm.setBoolean(4, ticket.isStatus());
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

    public boolean doesTicketExist(String seatId, String movieScreeningSessionId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection(); // Replace with your method to get connection
            if (con != null) {
                String sql = "SELECT * FROM ticket WHERE id_seat = ? AND id_moviescreeningsession = ? And status = 1"; // mới thêm ở đây status
                stm = con.prepareStatement(sql);
                stm.setString(1, seatId);
                stm.setString(2, movieScreeningSessionId);

                rs = stm.executeQuery();

                return rs.next(); // Returns true if a ticket exists for the given seat and session
            }
        } finally {
            // Close resources in finally block to ensure they are always closed
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

        return false; // Return false if connection fails or no ticket found
    }
}

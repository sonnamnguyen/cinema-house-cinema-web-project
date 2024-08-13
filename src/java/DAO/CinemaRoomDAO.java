/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author admin
 */
import DTO.Cinema;
import DTO.CinemaRoom;
import DTO.Movie;
import DTO.RoomCategory;

import Database.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRoomDAO {
  public CinemaRoom getCinemaRoomById(String idCinemaRoom) throws SQLException, ClassNotFoundException {
    CinemaRoom cinemaRoom = null;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM cinemaroom WHERE id_cinemaroom = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, idCinemaRoom);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idCinema = rs.getString("id_cinema");
                String idRoomCategory = rs.getString("id_roomcategory");

                RoomCategoryDAO roomCategoryDao = new RoomCategoryDAO();
                RoomCategory roomCategory = roomCategoryDao.selectById(idRoomCategory);

                CinemaDAO cinemaDao = new CinemaDAO();
                Cinema cinema = cinemaDao.selectById(idCinema);

                cinemaRoom = new CinemaRoom(
                    rs.getString("id_cinemaroom"),
                    cinema,
                    roomCategory,
                    rs.getString("roomName"),
                    rs.getBoolean("status")
                );
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

    return cinemaRoom;
}

}

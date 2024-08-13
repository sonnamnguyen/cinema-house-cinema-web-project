/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.RoomCategory;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class RoomCategoryDAO {
    public RoomCategory selectById(String idRoomCategory) throws SQLException, ClassNotFoundException {
    RoomCategory result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM roomcategory WHERE id_roomcategory=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, idRoomCategory);
            System.out.println(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String idRoomCat = rs.getString("id_roomcategory");
                String roomName = rs.getString("roomname");
                boolean status = rs.getBoolean("status");
                result = new RoomCategory(idRoomCat, roomName, status);
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

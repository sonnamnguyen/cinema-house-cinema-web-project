/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MovieCategory;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MovieCategoryDAO {
   public List<MovieCategory> selectAllCategory() throws SQLException, ClassNotFoundException {
    List<MovieCategory> ketQua = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT * FROM moviecategory";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idCategory = rs.getString("id_category");
                String categoryName = rs.getString("categoryname");
                boolean status = rs.getBoolean("status");

                MovieCategory movieCategory = new MovieCategory(idCategory, categoryName, status);
                ketQua.add(movieCategory);
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
}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import DTO.Comment;
import DTO.User;
import DTO.Movie;
import Database.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CommentDAO {
public List<Comment> selectAllComments(int offset, int limit) throws SQLException, ClassNotFoundException {
    List<Comment> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT c.id_comment, c.content, c.rating, c.datecomment, c.status, "
                    + "a.accountname, m.moviename, c.id_account, c.id_movie "
                    + "FROM comment c "
                    + "INNER JOIN account a ON c.id_account = a.id_account "
                    + "INNER JOIN movie m ON c.id_movie = m.id_movie "
                    + "ORDER BY c.datecomment DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            
            stm = con.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, limit);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                String idComment = rs.getString("id_comment");
                String content = rs.getString("content");
                int rating = rs.getInt("rating");
                Date dateComment = rs.getDate("datecomment");
                boolean commentStatus = rs.getBoolean("status");
                
                String idAccount = rs.getString("id_account");
                User user = new User(idAccount, rs.getString("accountname"));
                
                String idMovie = rs.getString("id_movie");
                Movie movie = new Movie(idMovie, rs.getString("moviename"));
                
                Comment comment = new Comment(idComment, movie, user, commentStatus, content, dateComment, rating);
                result.add(comment);
            }
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
            String sql = "SELECT COUNT(*) AS total FROM comment";
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
    public List<Comment> searchInfomations(String value, Date datecomment) throws SQLException, ClassNotFoundException {
        List<Comment> result = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT c.id_comment, c.content, c.rating, c.datecomment, c.status, "
                        + "a.accountname, m.moviename, c.id_account, c.id_movie "
                        + "FROM comment c "
                        + "INNER JOIN account a ON c.id_account = a.id_account "
                        + "INNER JOIN movie m ON c.id_movie = m.id_movie "
                        + "WHERE (a.accountname LIKE ? OR m.moviename LIKE ? OR c.content LIKE ? OR c.status LIKE ?) "
                        + "OR c.datecomment = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + value + "%");
                stm.setString(2, "%" + value + "%");
                stm.setString(3, "%" + value + "%");
                stm.setString(4, "%" + value + "%");
                stm.setDate(5, datecomment);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String idComment = rs.getString("id_comment");
                    String content = rs.getString("content");
                    int rating = rs.getInt("rating");
                    Date dateComment = rs.getDate("datecomment");
                    boolean commentStatus = rs.getBoolean("status");
                    String idAccount = rs.getString("id_account");
                    User user = new User(idAccount, rs.getString("accountname"));
                    String idMovie = rs.getString("id_movie");
                    Movie movie = new Movie(idMovie, rs.getString("moviename"));
                    Comment comment = new Comment(idComment, movie, user, commentStatus, content, dateComment, rating);
                    result.add(comment);
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

    public int delete(String commentId) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "DELETE FROM comment WHERE id_comment=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, commentId);
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

    public int update(String idComment, boolean status) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE comment SET status=? WHERE id_comment=?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, idComment);

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

    public List<Comment> getAllComments(String movieId) throws SQLException, ClassNotFoundException {
        List<Comment> comments = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection(); // Use your method to get connection
            String sql = "SELECT c.*, a.accountname, a.avatar "
                    + "FROM comment c "
                    + "JOIN account a ON c.id_account = a.id_account "
                    + "WHERE c.id_movie = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, movieId);
            rs = stm.executeQuery();

            while (rs.next()) {
                String commentId = rs.getString("id_comment");
                String userId = rs.getString("id_account");
                String content = rs.getString("content");
                boolean status = rs.getBoolean("status");
                int rating = rs.getInt("rating");
                String accountName = rs.getString("accountname");
                String avatar = rs.getString("avatar");
                Date dateComment = rs.getDate("datecomment");

                User user = new User();
                user.setIdAccount(userId);
                user.setAccountName(accountName);
                user.setAvatar(avatar);

                Comment comment = new Comment();
                comment.setIdComment(commentId);
                comment.setIdAccount(user);
                comment.setContent(content);
                comment.setStatus(status);
                comment.setRating(rating);
                comment.setDateCreate(dateComment);
                comments.add(comment);
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

        return comments;
    }

    public boolean insertComment(Comment comment) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String query = "INSERT INTO comment (id_comment, id_movie, id_account, content, status, datecomment, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(query);
                stm.setString(1, comment.getIdComment());
                stm.setString(2, comment.getIdFilm().getIdMovie());
                stm.setString(3, comment.getIdAccount().getIdAccount());
                stm.setString(4, comment.getContent());
                stm.setBoolean(5, false);
                stm.setDate(6, comment.getDateCreate());
                stm.setInt(7, comment.getRating());
                int affectedRows = stm.executeUpdate();
                return affectedRows != 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<String> selectByNameTop5(String name) throws SQLException, ClassNotFoundException {
        List<String> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
             String sql = "SELECT TOP 5 " +
                             "    a.accountname " +
                             "FROM " +
                             "    comment c " +
                             "INNER JOIN " +
                             "    account a ON c.id_account = a.id_account " +
                             "WHERE " +
                             "    a.accountname LIKE ? " +
                             "GROUP BY " +
                             "    a.accountname " ;

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

}

    

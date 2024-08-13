/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Database.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DTO.Movie;
import java.sql.Date;
/**
 *
 * @author admin
 */

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class MovieDAO {

    public List<Movie> searchMovies(String Name, Date effectivedatefrom, Date effectivedateto) throws SQLException, ClassNotFoundException {
        List<Movie> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie WHERE 1=1";
                if (Name != null && !Name.trim().isEmpty()) {
                    sql += " AND moviename LIKE ?";
                }
                if (effectivedatefrom != null) {
                    sql += " OR effectivedatefrom = ?";
                }
                if (effectivedateto != null) {
                    sql += " OR effectivedateto = ?";
                }

                stm = con.prepareStatement(sql);
                if (Name != null && !Name.isEmpty()) {
                    stm.setString(1, "%" + Name + "%");
                }
                if (effectivedatefrom != null) {
                    stm.setDate(2, effectivedatefrom);
                }
                if (effectivedateto != null) {
                    stm.setDate(3, effectivedateto);
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String category = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("Duration");

                    Movie movie = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, category, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
                    ketQua.add(movie);
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

    public List<Movie> selectAllMovies(int offset, int limit) throws SQLException, ClassNotFoundException {
        List<Movie> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie ORDER BY id_movie OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setInt(1, offset);
                stm.setInt(2, limit);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String category = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("Duration");

                    Movie movie = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, category, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
                    ketQua.add(movie);
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

        return ketQua;
    }

    public int getTotalRecords() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(*) AS total FROM movie";
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

    public List<Movie> selectAll(boolean isActive) throws SQLException, ClassNotFoundException {
        List<Movie> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie where status = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, isActive);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String category = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("Duration");

                    Movie movie = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, category, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
                    ketQua.add(movie);
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

    public List<Movie> selectByCategory(String category) throws SQLException, ClassNotFoundException {
        List<Movie> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie WHERE category=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, category);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String categoryName = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("duration");
                    Movie result = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, categoryName, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
                    ketQua.add(result);
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

    public List<Movie> selectByName(String name) throws SQLException, ClassNotFoundException {
        List<Movie> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie WHERE 1 = 1";
                if (name != null && !name.trim().isEmpty()) {
                    sql += " AND moviename LIKE ?";
                }

                stm = con.prepareStatement(sql);
                if (name != null && !name.trim().isEmpty()) {
                    stm.setString(1, "%" + name + "%");
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String category = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("duration");

                    Movie result = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, category, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
                    ketQua.add(result);
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

    public int insert(Movie t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "INSERT INTO movie (id_movie, moviename, director, actor, language, trailer, agelimit, coverimage, category, nationalorigin, effectivedatefrom, effectivedateto, status, summary, Duration) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, t.getIdMovie());
                stm.setString(2, t.getMovieName());
                stm.setString(3, t.getDirector());
                stm.setString(4, t.getActor());
                stm.setString(5, t.getLanguage());
                stm.setString(6, t.getTrailer());
                stm.setString(7, t.getAgeLimit());
                stm.setString(8, t.getCoverImage());
                stm.setString(9, t.getCategory());
                stm.setString(10, t.getNationalOrigin());
                stm.setDate(11, t.getEffectiveDateFrom());
                stm.setDate(12, t.getEffectiveDateTo());
                stm.setBoolean(13, t.isStatus());
                stm.setString(14, t.getSummary());
                stm.setInt(15, t.getDuration());
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

    public Movie selectById(Movie t) throws SQLException, ClassNotFoundException {
        Movie result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie WHERE id_movie=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, t.getIdMovie());
                System.out.println(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String category = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("Duration");
                    result = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, category, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
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

    public int checkByName(String name) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM movie WHERE moviename = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();  // Use executeQuery() instead of executeUpdate()

                if (rs.next()) {  // Check if a result is returned
                    result = 1;  // If a movie with the given name exists, set result to 1
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

    public int delete(Movie t) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();

            if (con != null) {
                String sql = "DELETE FROM moviescreeningsession WHERE id_movie = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, t.getIdMovie());
                result = stm.executeUpdate();

                String sql2 = "DELETE FROM movie WHERE id_movie = ?";
                stm = con.prepareStatement(sql2);
                stm.setString(1, t.getIdMovie());
                result = stm.executeUpdate();

                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + result + " dòng bị thay đổi!");
            }
            return result;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public int update(String movieName, String director, Date effectivedatefrom, Date effectivedateto, boolean status, int duration, String idMovie) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "UPDATE movie SET moviename=?, director=?, effectivedatefrom=?, effectivedateto=?, status=?, duration=? WHERE id_movie=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, movieName);
                stm.setString(2, director);
                stm.setDate(3, effectivedatefrom);
                stm.setDate(4, effectivedateto);
                stm.setBoolean(5, status);
                stm.setInt(6, duration);
                stm.setString(7, idMovie);

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

    public List<String> selectByNameTop5(String name) throws SQLException, ClassNotFoundException {
        List<String> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 5 moviename FROM movie WHERE moviename LIKE ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, name + "%");

                rs = stm.executeQuery();
                while (rs.next()) {

                    String movieName = rs.getString("moviename");

                    ketQua.add(movieName);
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

    public Movie getMovieBySessionDetails(String sessionTime, String roomName, String sessionDate) throws SQLException, ClassNotFoundException {
        Movie movie = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT m.* FROM movie m "
                        + "JOIN moviescreeningsession s ON m.id_movie = s.id_movie "
                        + "JOIN cinemaroom c ON s.id_cinemaroom = c.id_cinemaroom "
                        + "WHERE s.starttime = ? AND c.roomname = ? AND s.screeningdate = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, sessionTime);
                stm.setString(2, roomName);
                stm.setString(3, sessionDate);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String idMovie = rs.getString("id_movie");
                    String movieName = rs.getString("moviename");
                    String director = rs.getString("director");
                    String actor = rs.getString("actor");
                    String language = rs.getString("language");
                    String trailer = rs.getString("trailer");
                    String ageLimit = rs.getString("agelimit");
                    String coverImage = rs.getString("coverimage");
                    String category = rs.getString("category");
                    String nationalOrigin = rs.getString("nationalorigin");
                    Date effectiveDateFrom = rs.getDate("effectivedatefrom");
                    Date effectiveDateTo = rs.getDate("effectivedateto");
                    boolean status = rs.getBoolean("status");
                    String summary = rs.getString("summary");
                    int duration = rs.getInt("duration");

                    movie = new Movie(idMovie, movieName, summary, director, actor, language, trailer, ageLimit, coverImage, category, nationalOrigin, effectiveDateFrom, effectiveDateTo, status, duration);
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
        return movie;
    }

    public List<Pair<String, Integer>> getTop5MoviesBasedOnTickets() throws SQLException, ClassNotFoundException {
        List<Pair<String, Integer>> topMovies = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection(); // Assume DBUtil handles connection
            if (con != null) {
                String sql = "SELECT moviename, ticket_count "
                        + "FROM ( SELECT m.moviename, COUNT(t.id_ticket) "
                        + "AS ticket_count, ROW_NUMBER() OVER (ORDER BY COUNT(t.id_ticket) DESC) "
                        + "AS rownum FROM Movie m "
                        + "JOIN MovieScreeningSession ms ON m.id_movie = ms.id_movie "
                        + "JOIN Ticket t ON ms.id_moviescreeningsession = t.id_moviescreeningsession GROUP BY m.moviename) "
                        + "AS ranked WHERE rownum <= 5;";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    String movieName = rs.getString("moviename");
                    int ticketCount = rs.getInt("ticket_count");
                    topMovies.add(new Pair<>(movieName, ticketCount));
                }
            }
        } finally {
            // Close resources in reverse order of creation
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
        return topMovies;
    }

}

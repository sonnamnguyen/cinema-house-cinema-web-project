/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Cinema;
import DTO.CinemaRoom;
import DTO.Comment;
import DTO.Movie;
import DTO.MovieScreeningSession;
import DTO.User;
import Database.DBUtil;
import static Database.DBUtil.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ScreeningSessionDAO {
    
    public List<MovieScreeningSession> selectAllScreeningSessionAndMovie(String id_Movie) throws SQLException, ClassNotFoundException {
    List<MovieScreeningSession> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT ms.id_moviescreeningsession, ms.screeningdate, ms.starttime, ms.endtime, ms.status, " +
                         "m.id_movie, m.moviename, m.category, m.coverimage, m.nationalorigin, " +
                         "cr.id_cinemaroom, cr.roomname, " +
                         "c.id_cinema, c.cinemaname " +  
                         "FROM moviescreeningsession ms " +
                         "INNER JOIN movie m ON ms.id_movie = m.id_movie " +
                         "INNER JOIN cinemaroom cr ON ms.id_cinemaroom = cr.id_cinemaroom " +                    
                         "INNER JOIN cinema c ON cr.id_cinema = c.id_cinema where m.id_movie = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, id_Movie);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idMovieSession = rs.getString("id_moviescreeningsession");
                Date screeningDate = rs.getDate("screeningdate");
                Time startTime = rs.getTime("starttime");
                Time endTime = rs.getTime("endtime");
                boolean status = rs.getBoolean("status");

                String idMovie = rs.getString("id_movie");
                String movieName = rs.getString("moviename");
                String category = rs.getString("category");
                String coverimage = rs.getString("coverimage");
                String nationalorigin = rs.getString("nationalorigin");
                Movie movie =  new Movie(idMovie, movieName, coverimage, category, nationalorigin);

                String idCinemaRoom = rs.getString("id_cinemaroom");
                String roomName = rs.getString("roomname");

                String idCinema = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                Cinema cinema = new Cinema(idCinema, cinemaName);

                CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, cinema, roomName);

                MovieScreeningSession session = new MovieScreeningSession(idMovieSession, movie, screeningDate, startTime, endTime, cinemaRoom, status);
                result.add(session);
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
    
    public List<MovieScreeningSession> selectAllScreeningSessionAndMovie() throws SQLException, ClassNotFoundException {
    List<MovieScreeningSession> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT ms.id_moviescreeningsession, ms.screeningdate, ms.starttime, ms.endtime, ms.status, " +
                         "m.id_movie, m.moviename, m.category, m.coverimage, m.nationalorigin, " +
                         "cr.id_cinemaroom, cr.roomname, " +
                         "c.id_cinema, c.cinemaname " +  
                         "FROM moviescreeningsession ms " +
                         "INNER JOIN movie m ON ms.id_movie = m.id_movie " +
                         "INNER JOIN cinemaroom cr ON ms.id_cinemaroom = cr.id_cinemaroom " +                    
                         "INNER JOIN cinema c ON cr.id_cinema = c.id_cinema";
            
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idMovieSession = rs.getString("id_moviescreeningsession");
                Date screeningDate = rs.getDate("screeningdate");
                Time startTime = rs.getTime("starttime");
                Time endTime = rs.getTime("endtime");
                boolean status = rs.getBoolean("status");

                String idMovie = rs.getString("id_movie");
                String movieName = rs.getString("moviename");
                String category = rs.getString("category");
                String coverimage = rs.getString("coverimage");
                String nationalorigin = rs.getString("nationalorigin");
                Movie movie =  new Movie(idMovie, movieName, coverimage, category, nationalorigin);

                String idCinemaRoom = rs.getString("id_cinemaroom");
                String roomName = rs.getString("roomname");

                String idCinema = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                Cinema cinema = new Cinema(idCinema, cinemaName);

                CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, cinema, roomName);

                MovieScreeningSession session = new MovieScreeningSession(idMovieSession, movie, screeningDate, startTime, endTime, cinemaRoom, status);
                result.add(session);
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
public List<MovieScreeningSession> selectScreeningSessions(int offset, int limit) throws SQLException, ClassNotFoundException {
    List<MovieScreeningSession> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT ms.id_moviescreeningsession, ms.screeningdate, ms.starttime, ms.endtime, ms.status, " +
                         "m.id_movie, m.moviename, " +
                         "cr.id_cinemaroom, cr.roomname, " +
                         "c.id_cinema, c.cinemaname " +  
                         "FROM moviescreeningsession ms " +
                         "INNER JOIN movie m ON ms.id_movie = m.id_movie " +
                         "INNER JOIN cinemaroom cr ON ms.id_cinemaroom = cr.id_cinemaroom " +                    
                         "INNER JOIN cinema c ON cr.id_cinema = c.id_cinema " +
                         "ORDER BY ms.id_moviescreeningsession " +
                         "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            
            stm = con.prepareStatement(sql);
            stm.setInt(1, offset);
            stm.setInt(2, limit);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                String idMovieSession = rs.getString("id_moviescreeningsession");
                Date screeningDate = rs.getDate("screeningdate");
                Time startTime = rs.getTime("starttime");
                Time endTime = rs.getTime("endtime");
                boolean status = rs.getBoolean("status");

                String idMovie = rs.getString("id_movie");
                String movieName = rs.getString("moviename");
                Movie movie = new Movie(idMovie, movieName);

                String idCinemaRoom = rs.getString("id_cinemaroom");
                String roomName = rs.getString("roomname");

                String idCinema = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                Cinema cinema = new Cinema(idCinema, cinemaName);

                CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, cinema, roomName);

                MovieScreeningSession session = new MovieScreeningSession(idMovieSession, movie, screeningDate, startTime, endTime, cinemaRoom, status);
                result.add(session);
            }
            System.out.println("Fetched " + result.size() + " sessions from database."); // Debug print
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


public int countAllSessions() throws SQLException, ClassNotFoundException {
   int count = 0;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT COUNT(*) AS total FROM moviescreeningsession";
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

  public int delete(String idMovieSession) throws SQLException, ClassNotFoundException {
    int result = 0;
    Connection con = null;
    PreparedStatement stm = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "DELETE FROM moviescreeningsession WHERE id_moviescreeningsession = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, idMovieSession);
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

public int insert(MovieScreeningSession session1) throws SQLException, ClassNotFoundException {
    int result = 0;
    Connection con = null;
    PreparedStatement stm = null;

    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "INSERT INTO moviescreeningsession (id_moviescreeningsession, id_movie, id_cinemaroom, screeningdate, starttime, endtime, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, session1.getIdMoviesession());
            stm.setString(2, session1.getIdMovie().getIdMovie());
            stm.setString(3, session1.getIdMovieRoom().getIdCinemaRoom());
            stm.setDate(4, session1.getScreeningDate());
            stm.setTime(5, session1.getStartTime());
            stm.setTime(6, session1.getEndTime());
            stm.setBoolean(7, session1.isStatus());

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

public List<MovieScreeningSession> searchInformation(String value, Date screenDate) throws SQLException, ClassNotFoundException {
    List<MovieScreeningSession> result = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT ms.id_moviescreeningsession, ms.screeningdate, ms.starttime, ms.endtime, ms.status, " +
                         "m.id_movie, m.moviename, " +
                         "cr.id_cinemaroom, cr.roomname, " +
                         "c.id_cinema, c.cinemaname " +  
                         "FROM moviescreeningsession ms " +
                         "INNER JOIN movie m ON ms.id_movie = m.id_movie " +
                         "INNER JOIN cinemaroom cr ON ms.id_cinemaroom = cr.id_cinemaroom " +                    
                         "INNER JOIN cinema c ON cr.id_cinema = c.id_cinema " +
                         "WHERE m.moviename LIKE ? " +
                         "OR ms.screeningdate = ? ";
                       
            
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + value + "%");
            stm.setDate(2, screenDate);
            
            rs = stm.executeQuery();
            while (rs.next()) {
                String idMovieSession = rs.getString("id_moviescreeningsession");
                Date screeningDate = rs.getDate("screeningdate");
                Time sessionStartTime = rs.getTime("starttime");
                Time sessionEndTime = rs.getTime("endtime");
                boolean status = rs.getBoolean("status");

                String idMovie = rs.getString("id_movie");
                String movieName = rs.getString("moviename");
                Movie movie = new Movie(idMovie, movieName);

                String idCinemaRoom = rs.getString("id_cinemaroom");
                String roomName = rs.getString("roomname");

                String idCinema = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                Cinema cinema = new Cinema(idCinema, cinemaName);

                CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, cinema, roomName);

                MovieScreeningSession session = new MovieScreeningSession(idMovieSession, movie, screeningDate, sessionStartTime, sessionEndTime, cinemaRoom, status);
                result.add(session);
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

public MovieScreeningSession selectByMovieSessionId(String sessionId) throws SQLException, ClassNotFoundException {
    MovieScreeningSession result = null;
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "SELECT ms.id_moviescreeningsession, ms.screeningdate, ms.starttime, ms.endtime, ms.status, " +
                         "m.id_movie, m.moviename, " +
                         "cr.id_cinemaroom, cr.roomname, " +
                         "c.id_cinema, c.cinemaname " +  
                         "FROM moviescreeningsession ms " +
                         "INNER JOIN movie m ON ms.id_movie = m.id_movie " +
                         "INNER JOIN cinemaroom cr ON ms.id_cinemaroom = cr.id_cinemaroom " +                    
                         "INNER JOIN cinema c ON cr.id_cinema = c.id_cinema " +
                         "WHERE ms.id_moviescreeningsession = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, sessionId);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                String idMovieSession = rs.getString("id_moviescreeningsession");
                Date screeningDate = rs.getDate("screeningdate");
                Time startTime = rs.getTime("starttime");
                Time endTime = rs.getTime("endtime");
                boolean status = rs.getBoolean("status");

                String idMovie = rs.getString("id_movie");
                String movieName = rs.getString("moviename");
                Movie movie = new Movie(idMovie, movieName);

                String idCinemaRoom = rs.getString("id_cinemaroom");
                String roomName = rs.getString("roomname");

                String idCinema = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                Cinema cinema = new Cinema(idCinema, cinemaName);

                CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, cinema, roomName);

                result = new MovieScreeningSession(idMovieSession, movie, screeningDate, startTime, endTime, cinemaRoom, status);
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

public int updateMovieScreeningSession(MovieScreeningSession session) throws SQLException, ClassNotFoundException {
    int result = 0;
    Connection con = null;
    PreparedStatement stm = null;
    
    try {
        con = DBUtil.getConnection();
        if (con != null) {
            String sql = "UPDATE moviescreeningsession " +
                         "SET id_movie=?, id_cinemaroom=?, screeningdate=?, starttime=?, endtime=?, status=? " +
                         "WHERE id_moviescreeningsession=?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, session.getIdMovie().getIdMovie());
            stm.setString(2, session.getIdMovieRoom().getIdCinemaRoom());
            stm.setDate(3, session.getScreeningDate());
            stm.setTime(4, session.getStartTime());
            stm.setTime(5, session.getEndTime());
            stm.setBoolean(6, session.isStatus());
            stm.setString(7, session.getIdMoviesession());

            result = stm.executeUpdate();

            System.out.println("Executed SQL: " + sql);
            System.out.println("Number of rows updated: " + result);
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

public List<MovieScreeningSession> getScreeningSessions(String movieId) throws SQLException, ClassNotFoundException {
    List<MovieScreeningSession> sessions = new ArrayList<>();
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    try {
        con = DBUtil.getConnection();
        if (con != null) {
           String query = "SELECT ms.id_moviescreeningsession, ms.screeningdate, ms.starttime, ms.endtime, ms.status, " +
               "m.id_movie, m.moviename, " +
               "cr.id_cinemaroom, cr.roomname, " +
               "c.id_cinema, c.cinemaname " +  
               "FROM moviescreeningsession ms " +
               "INNER JOIN movie m ON ms.id_movie = m.id_movie " +
               "INNER JOIN cinemaroom cr ON ms.id_cinemaroom = cr.id_cinemaroom " +                    
               "INNER JOIN cinema c ON cr.id_cinema = c.id_cinema " + 
               "WHERE m.id_movie = ?";

            stm = con.prepareStatement(query);
            stm.setString(1, movieId);
            rs = stm.executeQuery();
            while (rs.next()) {
                String idMovieSchedule = rs.getString("id_moviescreeningsession");
                Date screeningDate = rs.getDate("screeningdate");
                Time startTime = rs.getTime("starttime");
                Time endTime = rs.getTime("endtime");
                boolean status = rs.getBoolean("status");

                String idMovie = rs.getString("id_movie");
                String movieName = rs.getString("moviename");
                Movie movie = new Movie(idMovie, movieName);

                String idCinemaRoom = rs.getString("id_cinemaroom");
                String roomName = rs.getString("roomname");

                String idCinema = rs.getString("id_cinema");
                String cinemaName = rs.getString("cinemaname");
                Cinema cinema = new Cinema(idCinema, cinemaName);

                CinemaRoom cinemaRoom = new CinemaRoom(idCinemaRoom, cinema, roomName);

                MovieScreeningSession session = new MovieScreeningSession(idMovieSchedule, movie, screeningDate, startTime, endTime, cinemaRoom, status);
                sessions.add(session);
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
    return sessions;
}

    public MovieScreeningSession getMovieScreeningSession
        (String sessionTime, String roomName, String sessionDate) throws SQLException, ClassNotFoundException {
        MovieScreeningSession session = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT mss.id_moviescreeningsession, mss.id_movie, mss.id_cinemaroom, mss.screeningdate, mss.starttime, mss.endtime, mss.status " +
                             "FROM moviescreeningsession mss " +
                             "JOIN cinemaroom cr ON mss.id_cinemaroom = cr.id_cinemaroom " +
                             "WHERE mss.starttime = ? AND cr.roomname = ? AND mss.screeningdate = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, sessionTime);
                stm.setString(2, roomName);
                stm.setString(3, sessionDate);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String idMovieScreeningSession = rs.getString("id_moviescreeningsession");
                    String idMovie = rs.getString("id_movie");
                    String idCinemaRoom = rs.getString("id_cinemaroom");
                    Date screeningDate = rs.getDate("screeningdate");
                    Time startTime = rs.getTime("starttime");
                    Time endTime = rs.getTime("endtime");
                    boolean status = rs.getBoolean("status");
                   Movie movie = new Movie(idMovie);
                   CinemaRoom cinemaroom =  new CinemaRoom(idCinemaRoom, roomName);
                   session = new MovieScreeningSession(idMovieScreeningSession, movie, screeningDate, startTime, endTime, cinemaroom, status);
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
        return session;
    }
}





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CinemaRoomDAO;
import DAO.MovieDAO;
import DAO.ScreeningSessionDAO;
import DTO.MovieScreeningSession;
import DTO.Movie;
import DTO.CinemaRoom;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/**
 *
 * @author admin
 */
public class CreateSessionSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
         
   
        private final String SESSION_ADMIN_PAGE = "screeningratemanagement.jsp";

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = "";
    PrintWriter out = response.getWriter();
    try {
        String errorMessage = "";
        String idMovie = request.getParameter("idMovie");
        String idCinemaRoom = request.getParameter("idCinemaRoom");
        String screeningDate = request.getParameter("screeningDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String status = request.getParameter("status");

        // Validate input parameters
        if (idMovie == null || idCinemaRoom == null || screeningDate == null || startTime == null || endTime == null) {
            errorMessage = "Missing required parameters.";
            request.setAttribute("errorMessage", errorMessage);
            url = "error.jsp";
        } else {
            Random rd = new Random();
            String idMovieSession = System.currentTimeMillis() + rd.nextInt(1000) + "";

            // Parse the date and time strings
            java.sql.Date dateScreen = null;
            java.sql.Time timeStart = null;
            java.sql.Time timeEnd = null;

            try {
                dateScreen = java.sql.Date.valueOf(screeningDate);
                timeStart = java.sql.Time.valueOf(startTime + ":00");
                timeEnd = java.sql.Time.valueOf(endTime + ":00");
            } catch (IllegalArgumentException e) {
                errorMessage = "Invalid date or time format.";
                request.setAttribute("errorMessage", errorMessage);
                url = "error.jsp";
            }

            if (url.isEmpty()) { 
                MovieDAO movieDao = new MovieDAO();
                Movie movie = movieDao.selectById(new Movie(idMovie));

                CinemaRoomDAO cinemaRoomDao = new CinemaRoomDAO();
                CinemaRoom cinemaRoom = cinemaRoomDao.getCinemaRoomById(idCinemaRoom);

                if (movie == null || cinemaRoom == null) {
                    errorMessage = "Invalid movie or cinema room.";
                    request.setAttribute("errorMessage", errorMessage);
                    url = "error.jsp";
                } else {
                    MovieScreeningSession session = new MovieScreeningSession(
                        idMovieSession,
                        movie,
                        dateScreen,
                        timeStart,
                        timeEnd,
                        cinemaRoom,
                        status != null
                    );

                    // Insert the session into the database
                    ScreeningSessionDAO screeningSessionDao = new ScreeningSessionDAO();
                    if (screeningSessionDao.insert(session) > 0) {
                        url = SESSION_ADMIN_PAGE;
                    } else {
                        errorMessage = "Failed to create the movie screening session. Please try again.";
                        request.setAttribute("errorMessage", errorMessage);
                        url = "error.jsp";
                    }
                }
            }
        }
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        request.setAttribute("ERROR_MESSAGE", "An error occurred while creating the movie screening session: " + ex.getMessage());
        url = "error.jsp";
    } finally {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
        out.close();
    }
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

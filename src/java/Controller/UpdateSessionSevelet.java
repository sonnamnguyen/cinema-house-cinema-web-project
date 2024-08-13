/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CinemaRoomDAO;
import DAO.MovieDAO;
import DAO.ScreeningSessionDAO;
import DAO.UserDAO;
import DTO.CinemaRoom;
import DTO.Movie;
import DTO.MovieScreeningSession;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class UpdateSessionSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   private final String ADMIN_SESSION_PAGES = "screeningratemanagement.jsp";
private final String UPDATE_SESSION_PAGES = "updatesession.jsp";

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = "";
    PrintWriter out = response.getWriter();
    try {
        String errorMessage = "";
        String idSession = request.getParameter("sessionID");
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

            // If no validation errors, proceed to update the session
            if (errorMessage.isEmpty()) {
                // Fetch movie and cinema room details
                MovieDAO movieDao = new MovieDAO();
                Movie movie = movieDao.selectById(new Movie(idMovie));

                CinemaRoomDAO cinemaRoomDao = new CinemaRoomDAO();
                CinemaRoom cinemaRoom = cinemaRoomDao.getCinemaRoomById(idCinemaRoom);

                // Update the session if it exists
                ScreeningSessionDAO dao = new ScreeningSessionDAO();
                MovieScreeningSession session = dao.selectByMovieSessionId(idSession);
                
                if (session != null) {
                    // Create updated session object
                    MovieScreeningSession updatedSession = new MovieScreeningSession(idSession, movie, dateScreen, timeStart, timeEnd, cinemaRoom, Boolean.parseBoolean(status));
                    
                    // Update session in database
                    dao.updateMovieScreeningSession(updatedSession);
                    
                    url = ADMIN_SESSION_PAGES;
                } else {
                    errorMessage = "Session with ID " + idSession + " not found.";
                    request.setAttribute("errorMessage", errorMessage);
                    url = "error.jsp";
                }
            }
        }
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
//        // Handle exception, for example log it or redirect to an error page
//        errorMessage = "Error occurred: " + ex.getMessage();
//        request.setAttribute("errorMessage", errorMessage);
//        url = "error.jsp";
    } finally {
        // Forward to appropriate URL based on the outcome
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

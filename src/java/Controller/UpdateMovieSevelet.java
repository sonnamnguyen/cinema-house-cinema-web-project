/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MovieDAO;
import DTO.Movie;
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
public class UpdateMovieSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_MOVIE_PAGES = "movieAdmin.jsp";
    private final String UPDATE_MOVIE_PAGES = "updatemovie.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       String url = UPDATE_MOVIE_PAGES;
        try {
            String errorMessage = "";
            String movieId = request.getParameter("movieID"); 
            String movieName = request.getParameter("MovieName");
            String effectiveDateFrom = request.getParameter("EffectiveDateFrom");
            String effectiveDateTo = request.getParameter("EffectiveDateTo");
            String durationMovie = request.getParameter("duration");
            String status = request.getParameter("Status");
            String director = request.getParameter("Director");

            // Set attributes for forwarding in case of error
            request.setAttribute("MovieName", movieName);
            request.setAttribute("Director", director);
            request.setAttribute("EffectiveDateFrom", effectiveDateFrom);
            request.setAttribute("EffectiveDateTo", effectiveDateTo);
            request.setAttribute("duration", durationMovie);
            request.setAttribute("Status", status);
            request.setAttribute("errorMessage", errorMessage);

            Date dateFrom = Date.valueOf(effectiveDateFrom);
            Date dateTo = Date.valueOf(effectiveDateTo);
            int duration = Integer.parseInt(durationMovie);
            boolean isActive = Boolean.parseBoolean(status);

            MovieDAO dao = new MovieDAO();
            int movie = dao.update(movieName, director, dateFrom, dateTo, isActive, duration, movieId);

            if (movie > 0) {
                url = ADMIN_MOVIE_PAGES;
            } else {
                errorMessage = "Cannot update movie. Something went wrong!";
                request.setAttribute("ERROR_MESSAGE", errorMessage);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("ERROR_MESSAGE", "An error occurred while updating the movie: " + ex.getMessage());
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

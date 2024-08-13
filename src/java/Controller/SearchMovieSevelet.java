/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.Movie;
import DAO.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class SearchMovieSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private final String ADMIN_MOVIE_PAGES = "searchmovies.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String url = "";
    try {
        String movieName = request.getParameter("MovieName");
        String datefrom = request.getParameter("ScreenStart");
        String dateto = request.getParameter("ScreenEnd");

        Date screenFrom = null;
        Date screenTo = null;

        if (datefrom != null && !datefrom.isEmpty()) {
            screenFrom = Date.valueOf(datefrom);
        }
        if (dateto != null && !dateto.isEmpty()) {
            screenTo = Date.valueOf(dateto);
        }

        MovieDAO dao = new MovieDAO();
        List<Movie> movies = dao.searchMovies(movieName, screenFrom, screenTo);
        if (movies != null) {
            request.setAttribute("LIST_ALL_SEARCH", movies);
            url = ADMIN_MOVIE_PAGES;
        } else {
            request.setAttribute("errorMessage", "Movie not found, please try again!");
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

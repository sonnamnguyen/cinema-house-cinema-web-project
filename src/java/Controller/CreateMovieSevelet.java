/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Movie;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author admin
 */
public class CreateMovieSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private final String MOVIE_ADMIN_PAGE = "movieAdmin.jsp";
        private final String CREATE_MOVIE_PAGE = "createmovie.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = MOVIE_ADMIN_PAGE;
        try  {
            String errorMessage = "";
           String CoverImage = request.getParameter("CoverImage");
           String MovieName = request.getParameter("MovieName");
           String Director = request.getParameter("Director");
           String Actor = request.getParameter("Actor");
           String Summary = request.getParameter("Summary");
           String EffectiveDateFrom = request.getParameter("EffectiveDateFrom");
           String EffectiveDateTo = request.getParameter("EffectiveDateTo");
            String durationMovie = request.getParameter("duration");
           String Trailer = request.getParameter("Trailer");
            String AgeLimit = request.getParameter("AgeLimit");
           String NationalOrigin = request.getParameter("NationalOrigin");
            String Status = request.getParameter("Status");
           String Category = request.getParameter("Category");
           String Language = request.getParameter("Language");
           
             request.setAttribute("CoverImage", CoverImage);
            request.setAttribute("MovieName", MovieName);
            request.setAttribute("Director", Director);
            request.setAttribute("Actor", Actor);
            request.setAttribute("Summary", Summary);
            request.setAttribute("EffectiveDateFrom", EffectiveDateFrom);
            request.setAttribute("EffectiveDateTo", EffectiveDateTo);
            request.setAttribute("duration", durationMovie);
            request.setAttribute("Trailer", Trailer);
            request.setAttribute("AgeLimit", AgeLimit);
             request.setAttribute("NationalOrigin", NationalOrigin);
            request.setAttribute("Status", Status);
            request.setAttribute("Category", Category);
            int duration = Integer.parseInt(durationMovie);
            
            MovieDAO dao = new MovieDAO();
          if(dao.checkByName(MovieName) > 0) {
              errorMessage = "Can not create a new movie because it existed!!!!";
              url = CREATE_MOVIE_PAGE;
          } else {
                Random rd = new Random();
                String idMovie = System.currentTimeMillis() + rd.nextInt(1000) + ""; 
              Movie movie = new Movie( idMovie,  MovieName,  Summary,  Director,  Actor, Language,  Trailer,  AgeLimit,  CoverImage,  Category, NationalOrigin, Date.valueOf(EffectiveDateFrom) , Date.valueOf(EffectiveDateTo), Status != null,  duration);
              if(dao.insert(movie) > 0) {
                  url = MOVIE_ADMIN_PAGE;
              }  else {
                    errorMessage = "Failed to create the movie. Please try again.";
                    url = CREATE_MOVIE_PAGE;
                }
            }

            request.setAttribute("errorMessage", errorMessage);
          }catch(SQLException|ClassNotFoundException ex) {
            ex.printStackTrace();
            //k handle chỉ quăng ra lỗi
        } finally {
            //lỗi xảy ra vẫn chạy nên để request
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

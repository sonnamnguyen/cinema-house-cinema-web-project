/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String ADMIN_PAGE = "admin.jsp";
    private final String CREATE_MOVIE_PAGE = "createmovie.jsp";
    private final String SEARCH_MOVIE_SERVELET = "SearchMovieSevelet";
    private final String DELETE_MOVIE_SERVELET = "DeleteMovieSevelet";
    private final String UPDATE_MOVIE_SERVELET = "updatemovie.jsp";
    private final String CREATE_USER_PAGE = "CreateUserServlet";
    private final String SEARCH_USER_SERVELET = "SearchUserSevelet";
    private final String UPDATE_USER_SERVELET = "updateuser.jsp";
    private final String DELETE_USER_SERVELET = "DeleteUserSevelet";
    private final String AUTHENTICATE_SEVELET = "AuthenticateSevelet";
    private final String SEARCH_COMMENT_SERVELET = "SearchCommentSevelet";
    private final String APPROVED_COMMENT_SERVELET = "ApprovedCommentSevelet";
    private final String DELETE_COMMENT_SERVELET = "DeleteCommentsSevelet";
    private final String SEARCH_INFORMATION_SERVELET = "SearchInformationSevelet";
    private final String UPDATE_SESSION_SERVELET = "updatesession.jsp";
    private final String DELETE_SESSION_SERVELET = "DeleteSessionSevelet";
    private final String SEARCH_BILL_SERVELET = "SearchBillSevelet";
    private final String ACCEPT_BILL_SERVELET = "AcceptBillSevelet";
    private final String UNACCEPT_BILL_SERVELET = "UnacceptBillSevelet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ADMIN_PAGE;
        try {
            String btAction = request.getParameter("btAction") + "";
            if (btAction.equals("CreateMovie")) {
                url = CREATE_MOVIE_PAGE;
            } else if (btAction.equals("SearchMovie")) {
                url = SEARCH_MOVIE_SERVELET;
            } else if (btAction.equals("DeleteMovies")) {
                url = DELETE_MOVIE_SERVELET;
            } else if (btAction.equals("UpdateMovies")) {
                url = UPDATE_MOVIE_SERVELET;
            } else if (btAction.equals("CreateUser")) {
                url = CREATE_USER_PAGE;
            } else if (btAction.equals("SearchInformation")) {
                url = SEARCH_USER_SERVELET;
            } else if (btAction.equals("UpdateUser")) {
                url = UPDATE_USER_SERVELET;
            } else if (btAction.equals("DeleteUser")) {
                url = DELETE_USER_SERVELET;
            } else if (btAction.equals("confirm")) {
                url = AUTHENTICATE_SEVELET;
            } else if (btAction.equals("SearchInfo")) {
                url = SEARCH_COMMENT_SERVELET;
            } else if (btAction.equals("ApprovedComment")) {
                url = APPROVED_COMMENT_SERVELET;
            } else if (btAction.equals("DeleteComment")) {
                url = DELETE_COMMENT_SERVELET;
            } else if (btAction.equals("SearchInformationScreen")) {
                url = SEARCH_INFORMATION_SERVELET;
            } else if (btAction.equals("UpdateSession")) {
                url = UPDATE_SESSION_SERVELET;
            } else if (btAction.equals("DeleteSession")) {
                url = DELETE_SESSION_SERVELET;
            } else if (btAction.equals("SearchInformationBill")) {
                url = SEARCH_BILL_SERVELET;
            } else if (btAction.equals("AcceptBill")) {
                url = ACCEPT_BILL_SERVELET;
            }else if (btAction.equals("UnacceptBill")) {
                url = UNACCEPT_BILL_SERVELET;
            }
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

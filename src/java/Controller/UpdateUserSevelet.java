/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
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
public class UpdateUserSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SUCCESS_PAGE = "successadmin.jsp";
    private final String UPDATE_PAGE = "managementmembers.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        try {
            String userId = request.getParameter("userID");
            String accountName = request.getParameter("accountName");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String gender = request.getParameter("gender");
            String yearOfBirth = request.getParameter("yearOfBirth");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String agreeMails = request.getParameter("agreeMails");
            request.setAttribute("accountName", accountName);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("gender", gender);
            request.setAttribute("yearOfBirth", yearOfBirth);
            request.setAttribute("address", address);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("email", email);
            request.setAttribute("agreeMails", agreeMails);

            String errorMessage = "";
            UserDAO khachHangDAO = new UserDAO();

            request.setAttribute("errorMessage", errorMessage);

            if (errorMessage.length() > 0) {
                url = UPDATE_PAGE;
            } else {
                User user = khachHangDAO.selectByUserId(userId);
                if (user != null) {
                    String maKhachHang = user.getIdAccount();
                    User kh = new User(maKhachHang, accountName, firstname, lastname, gender, address, phoneNumber, Date.valueOf(yearOfBirth), email, agreeMails != null);
                    khachHangDAO.updateInfo(kh);

                    url = SUCCESS_PAGE;
                }

            }
        } catch (SQLException | ClassNotFoundException ex) {
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

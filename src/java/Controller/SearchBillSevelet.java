/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import DTO.Bill;
import DAO.BillDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SearchBillSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
private final String ADMIN_BILLS_PAGES = "searchbills.jsp";

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String url = "";
    
    try {
        String information = request.getParameter("Information");
        String screenDateStr = request.getParameter("BillDate");

        java.sql.Date screenDate = null;

        if (screenDateStr != null && !screenDateStr.isEmpty()) {
            screenDate = java.sql.Date.valueOf(screenDateStr);
        }

        BillDAO dao = new BillDAO();
        List<Bill> bills = dao.searchInformation(information, screenDate);

        if (bills != null && !bills.isEmpty()) {
            request.setAttribute("LIST_ALL_BILLSS", bills);
            url = ADMIN_BILLS_PAGES;
        } else {
            request.setAttribute("errorMessage", "No bills found for the specified criteria.");
            url = ADMIN_BILLS_PAGES;
        }
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        request.setAttribute("errorMessage", "Error: " + ex.getMessage());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BillDAO;
import DAO.CommentDAO;
import DTO.Bill;
import DTO.User;
import Utils.Email;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UnacceptBillSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
private final String ADMIN_BILLS_PAGES = "ticketmanagement.jsp";

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String url = ADMIN_BILLS_PAGES;

    try {
        String billId = request.getParameter("billID");
        String status = request.getParameter("status");
        boolean approved = Boolean.parseBoolean(status);

        if (approved) {
            approved = false; // Set the status to false to unapprove the bill
            BillDAO dao = new BillDAO();
            int success = dao.update(billId, approved);
            if (success > 0) {
                // Retrieve the user information associated with the bill
                Bill bill = dao.getBillById(billId); // Get the first (and presumably only) bill
                User user = dao.getUserByBillId(billId);

                // Send email notification to the user
                if (user != null) {
                    Email.sendEmail(user.getEmail(), "Bill Unapproved", getUnapprovedEmailContent(user, bill));
                }

                request.setAttribute("MESSAGE", "Bill unapproved successfully.");
            } else {
                request.setAttribute("MESSAGE", "Failed to unapprove bill.");
            }
        }

    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
        request.setAttribute("ERROR", "An error occurred while processing the request: " + ex.getMessage());
    } finally {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
        out.close();
    }
}

private String getUnapprovedEmailContent(User user, Bill bill) {
    return "<p>Hello " + user.getAccountName() + ",</p>"
            + "<p>Your bill with ID <strong>" + bill.getIdBill() + "</strong> has been unapproved.</p>"
            + "<p>If you have any questions or need further assistance, please contact us.</p>"
            + "<p>Best regards,</p>"
            + "<p>Cinematic.vn</p>";
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

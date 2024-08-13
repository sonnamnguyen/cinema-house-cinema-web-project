
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BillDAO;
import DAO.SeatDAO;
import DTO.Bill;
import DTO.Movie;
import DTO.MovieScreeningSession;
import DTO.Seat;
import DTO.Ticket;
import DTO.User;
import Utils.Email;
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
public class AcceptBillSevelet extends HttpServlet {

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

        if (!approved) {
            approved = true; // Change this if you want to set the status to true instead
            BillDAO dao = new BillDAO();
            SeatDAO seatdao = new SeatDAO();
            int success = dao.update(billId, approved);
            if (success > 0) {
                // Retrieve the user information associated with the bill
                Bill bill = dao.getBillById(billId);
                User user = dao.getUserByBillId(billId);
                List<Seat> list = seatdao.getAllSeatsByBillId(billId);

                // Send email notification to the user
                if (user != null && bill != null) {
                    Email.sendEmail(user.getEmail(), "Bill Approved", getEmailContent(user, bill, list));
                }

                request.setAttribute("MESSAGE", "Bill approved successfully.");
            } else {
                request.setAttribute("MESSAGE", "Failed to approve bill.");
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

private String getEmailContent(User user, Bill bill, List<Seat> bills) {
    StringBuilder seatInfo = new StringBuilder();
    for (Seat bill1 : bills)
 { 
        String seat = bill1.getIdSeat();
        seatInfo.append("<li><strong>Seat:</strong> ").append(seat).append("</li>");
    }

    Ticket firstTicket = bill.getIdTicket(); // Assuming there is at least one ticket
    MovieScreeningSession movieSession = firstTicket.getIdMovieScreeningSession();
    Movie movie = movieSession.getIdMovie();

    String content = "<p>Hello " + user.getAccountName() + ",</p>"
            + "<p>Your bill with ID <strong>" + bill.getIdBill() + "</strong> has been approved.</p>"
            + "<p>Details of your booking:</p>"
            + "<ul>"
            + "<li><strong>Movie:</strong> " + movie.getMovieName() + "</li>"
            + "<li><strong>Date:</strong> " + movieSession.getScreeningDate() + "</li>"
            + "<li><strong>Room:</strong> " + movieSession.getIdMovieRoom().getRoomName() + "</li>"
            + seatInfo.toString()
            + "</ul>"
            + "<p>Thank you for your patience.</p>"
            + "<p>Best regards,</p>"
            + "<p>Cinematic.vn</p>";
    return content;
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

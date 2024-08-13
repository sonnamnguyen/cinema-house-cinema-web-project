/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.BillDAO;
import DAO.SeatDAO;
import DAO.TicketsDAO;
import DTO.Bill;
import DTO.MovieScreeningSession;
import DTO.Seat;
import DTO.Ticket;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class PaymentServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String SUCCESS_PAGE = "index.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SUCCESS_PAGE;
        try  {
         String userId = request.getParameter("userId");
            String MSSID = request.getParameter("MSSID");
            String seatIDs = request.getParameter("seatIDs");
            String totalPrice = request.getParameter("totalPrice");

            // Create User object
            User user = new User();
            user.setIdAccount(userId);

            // Insert Ticket and Bill information
    
                TicketsDAO ticketDAO = new TicketsDAO();
                BillDAO billDAO = new BillDAO();

                String[] seatIDArray = seatIDs.split(",");
                Random rd = new Random();
                String billId = System.currentTimeMillis() + rd.nextInt(1000) + ""; 
                for (String seatID : seatIDArray) {
                    // Create Ticket
                    String ticketId = System.currentTimeMillis() + rd.nextInt(1000) + ""; 
                    MovieScreeningSession idMovieScreeningSession = new MovieScreeningSession(MSSID);
                    Seat idSeat = new Seat(seatID);
                    Ticket ticket = new Ticket(ticketId, idMovieScreeningSession, idSeat, true);
                    ticketDAO.insertTicket(ticket);

                    // Create Bill
                    Bill bill = new Bill();
                    bill.setIdBill(billId);
                    bill.setIdTicket(ticket);
                    bill.setIdAccount(user);
                    bill.setDateCreated(new Date(System.currentTimeMillis()));
                    bill.setTimeCreated(new Time(System.currentTimeMillis()));
                    bill.setTotalMoney(Float.parseFloat(totalPrice));
                    bill.setStatus(false);
                    billDAO.insertBill(bill);
                }
   
           url = "success.jsp";
        }catch (SQLException | ClassNotFoundException ex) {
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

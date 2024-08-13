/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.User;
import Utils.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
public class CheckCodeServelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String AUTHENTICATE_PAGE = "authenticate.jsp";
   
      

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String url = "";
    try {
        String codeVerification = request.getParameter("Code");
        String userId = request.getParameter("userID");
        UserDAO dao = new UserDAO();
        User user = dao.selectById(new User(userId));
        
        if (user != null) {
            if (user.getVerificationCode().equals(codeVerification)) {
                Random rd = new Random();
                String password = System.currentTimeMillis() + rd.nextInt(1000) + "";
               
                user.setPassword(password);
                boolean success = dao.changePassword(user);
                
                if (success) {
                    Email.sendEmail(user.getEmail(), "Authenticate password at CINEMATIC.vn", getNoiDung(user));
                }
            } else {
                request.setAttribute("errorMessage", "Verification code is incorrect/or does not exist");
                url = AUTHENTICATE_PAGE;
            }
        } else {
            request.setAttribute("errorMessage", "Verification code is incorrect/or does not exist");
            url = AUTHENTICATE_PAGE;
        }
    } catch (SQLException | ClassNotFoundException ex) {
        ex.printStackTrace();
    } finally {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
        out.close();
    }
}
    
    public static String getNoiDung(User user) {
         String link = "http://localhost:8084/Cenematic1/UserController?btAction=authen&maKhachHang="
                + user.getIdAccount() + "&maPassword=" + user.getPassword();
    String noiDung = "<p>Cinematic.vn xin ch&agrave;o bạn <strong>" + user.getAccountName() + "</strong>,</p>\r\n"
            + "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"
            + user.getPassword() + "</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
            + "<p><a href=\"" + link + "\">" + link + "</a></p>\r\n"
            + "<p>Đ&acirc;y l&agrave; email tự động, vui l&ograve;ng kh&ocirc;ng phản hồi email n&agrave;y.</p>\r\n"
            + "<p>Tr&acirc;n trọng cảm ơn.</p>";
    return noiDung;
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

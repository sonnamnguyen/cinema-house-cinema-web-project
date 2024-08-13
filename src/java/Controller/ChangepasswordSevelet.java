/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.User;
import Utils.MaHoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class ChangepasswordSevelet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private final String CHANGEPASSWORD_PAGE = "changepassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = CHANGEPASSWORD_PAGE;
        try {
               String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String newPasswordAgain = request.getParameter("newPasswordAgain");

            String matKhauHienTai_Sha1 = MaHoa.toSHA1(currentPassword);

            String errorMessage = "";
            

            // Kiem tra mat khau cu co giong hay khong
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("user");
            User user = null;
            if (obj != null) {
                user = (User) obj;
            }
            if (user == null) {
                errorMessage = "Bạn chưa đăng nhập vào hệ thống!";
            } else {
                // Neu khach hang da dang nhap
                if (!matKhauHienTai_Sha1.equals(user.getPassword())) {
                    errorMessage = "Mật khẩu hiện tại không chính xác!";
                } else {
                    if (!newPassword.equals(newPasswordAgain)) {
                        errorMessage = "Mật khẩu nhập lại không khớp!";
                    } else {
                        String matKhauMoi_Sha1 = MaHoa.toSHA1(newPassword);
                        user.setPassword(matKhauMoi_Sha1);
                        UserDAO khd = new UserDAO();
                        if (khd.changePassword(user)) {
                            errorMessage = "Mật khẩu đã thay đổi thành công!";
                        } else {
                            errorMessage = "Quá trình thay đổi mật khẩu không thành công!";
                        }
                    }
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

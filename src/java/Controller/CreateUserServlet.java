/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.User;
import Utils.Email;
import Utils.MaHoa;
import Utils.SoNgauNhien;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class CreateUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        private final String CREATE_PAGE = "createuser.jsp";
       private final String MANAGEMENT_PAGE = "successadmin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        try  {
          String userName = request.getParameter("userName");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            String passwordAgain = request.getParameter("passwordAgain");
            String linkImage = request.getParameter("linkImage");
            String gender = request.getParameter("gender");
            String yearOfBirth = request.getParameter("yearOfBirth");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String dongYNhanMail = request.getParameter("agreeMails");
            boolean agreeMails = Boolean.valueOf(dongYNhanMail);
            request.setAttribute("userName", userName);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("linkImage", linkImage);
            request.setAttribute("gender", gender);
            request.setAttribute("yearOfBirth", yearOfBirth);
            request.setAttribute("address", address);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("email", email);
            request.setAttribute("agreeMails", agreeMails);
          
            boolean isAdmin = false;
            String errorMessage = "";
            UserDAO khachHangDAO = new UserDAO();

            if (khachHangDAO.kiemTraTenDangNhap(userName)) {
                errorMessage += "Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
            }

            if (!password.equals(passwordAgain)) {
                errorMessage += "Mẫu khẩu không khớp.<br/>";
            } else {
                password = MaHoa.toSHA1(password);
            }

            request.setAttribute("errorMessage", errorMessage);

            if (errorMessage.length() > 0) {
                url = CREATE_PAGE;
            } else {
                Random rd = new Random();
                String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + "";   
                User user = new User(maKhachHang, linkImage, userName, firstname, lastname, gender, address, phoneNumber, Date.valueOf(yearOfBirth), email, agreeMails, password, isAdmin);
                 user.setIsAdmin(isAdmin);
                if (khachHangDAO.insert(user) > 0) {

                    // Day so xac thuc
                    String soNgauNhien = SoNgauNhien.getSoNgauNhien();

                    // Quy dinh thoi gian hieu luc
                    Date todaysDate = new Date(new java.util.Date().getTime());
                    Calendar c = Calendar.getInstance();
                    c.setTime(todaysDate);
                    c.add(Calendar.DATE, 1);
                    Date thoGianHieuLucXacThuc = new Date(c.getTimeInMillis());

                    // Trang thai xac thuc = false
                    boolean trangThaiXacThuc = false;

                    user.setVerificationCode(soNgauNhien);
                    user.setEffectiveTime(thoGianHieuLucXacThuc);
                    user.setAuthentication(trangThaiXacThuc);

                    if (khachHangDAO.updateVerifyInformation(user) > 0) {
                        // Gui email cho khach hang
                        Email.sendEmail(user.getEmail(), "Xác thực tài khoản tại CINEMATIC.vn", getNoiDung(user));
                    }
                }
                url = MANAGEMENT_PAGE;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            //k handle chỉ quăng ra lỗi
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            //lỗi xảy ra vẫn chạy nên để request
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    public static String getNoiDung(User kh) {
        String link = "http://localhost:8084/Cenematic/AdminController?btAction=confirm&maKhachHang="
                + kh.getIdAccount() + "&maXacThuc=" + kh.getVerificationCode();
        String noiDung = "<p>Cinematic.vn xin ch&agrave;o bạn <strong>" + kh.getAccountName() + "</strong>,</p>\r\n"
                + "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"
                + kh.getVerificationCode() + "</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
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

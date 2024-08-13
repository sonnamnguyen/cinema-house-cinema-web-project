
package Controller;


import DAO.UserDAO;
import DTO.User;
import Utils.Email;
import Utils.SoNgauNhien;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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
public class ForgotPasswordServelet extends HttpServlet {

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
    private final String FORGOT_PAGE = "forgotpassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        try {
            String email = request.getParameter("Email");
            UserDAO dao = new UserDAO();
            User user = dao.selectByUserEmail(email);
            HttpSession session = request.getSession();
            if (user != null) {
                String soNgauNhien = SoNgauNhien.getSoNgauNhien();

                // Set expiration time for the verification code
                Date todaysDate = new Date(new java.util.Date().getTime());
                Calendar c = Calendar.getInstance();
                c.setTime(todaysDate);
                c.add(Calendar.DATE, 1);
                Date thoGianHieuLucXacThuc = new Date(c.getTimeInMillis());

                user.setVerificationCode(soNgauNhien);
                user.setEffectiveTime(thoGianHieuLucXacThuc);
                if (dao.updateVerifyPassword(user) > 0) {
                    Email.sendEmail(user.getEmail(), "Authenticate password at CINEMATIC.vn", getNoiDung(user));
                }
                session.setAttribute("user", user);

                url = AUTHENTICATE_PAGE;
            } else {
                request.setAttribute("errorMessage", "Account is incorrect/or does not exist");
                url = FORGOT_PAGE;
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
        String noiDung = "<p>Cinematic.vn xin ch&agrave;o bạn <strong>" + user.getAccountName() + "</strong>,</p>\r\n"
                + "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"
                + user.getVerificationCode() + "</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
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
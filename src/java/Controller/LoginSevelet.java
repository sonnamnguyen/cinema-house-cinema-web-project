package Controller;

import DAO.UserDAO;
import DTO.User;
import Utils.MaHoa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginSevelet extends HttpServlet {

    private final String INDEX_PAGE = "index.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String ADMIN_PAGE = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE; // Default to login page

        try {
            String tenDangNhap = request.getParameter("UserName");
            String matKhau = request.getParameter("Password");

            matKhau = MaHoa.toSHA1(matKhau); // Hash the password

            User kh = new User();
            kh.setAccountName(tenDangNhap);
            kh.setPassword(matKhau);

            UserDAO khd = new UserDAO();
            User khachHang = khd.selectByUsernameAndPassWord(kh, true);

            if (khachHang != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", khachHang);

                // Check if Remember Me checkbox is checked
                if (request.getParameter("rememberMe") != null) {
                    Cookie cUserName = new Cookie("UserName", tenDangNhap);
                    Cookie cPassword = new Cookie("Password", matKhau);
                    cUserName.setMaxAge(24 * 60 * 60); // 1 day
                    cPassword.setMaxAge(24 * 60 * 60); // 1 day
                    response.addCookie(cUserName);
                    response.addCookie(cPassword);
                } else {
                    // Clear existing cookies if Remember Me is not checked
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("UserName") || cookie.getName().equals("Password")) {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
                    }
                }

                url = ADMIN_PAGE; // Redirect to admin page after successful login
            } else {
                khachHang = khd.selectByUsernameAndPassWord(kh, false);
                if (khachHang != null && khachHang.isAuthentication()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", khachHang);

                    // Check if Remember Me checkbox is checked
                    if (request.getParameter("rememberMe") != null) {
                        Cookie cUserName = new Cookie("UserName", tenDangNhap);
                        Cookie cPassword = new Cookie("Password", matKhau);
                        cUserName.setMaxAge(24 * 60 * 60); // 1 day
                        cPassword.setMaxAge(24 * 60 * 60); // 1 day
                        response.addCookie(cUserName);
                        response.addCookie(cPassword);
                    } else {
                        // Clear existing cookies if Remember Me is not checked
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("UserName") || cookie.getName().equals("Password")) {
                                    cookie.setMaxAge(0);
                                    response.addCookie(cookie);
                                }
                            }
                        }
                    }

                    url = INDEX_PAGE; // Redirect to index page after successful login
                } else {
                    request.setAttribute("errorMessage",
                            "Tên đăng nhập hoặc mật khẩu không đúng / hoặc Tài khoản chưa xác thực!");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url); // Redirect to the appropriate page
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

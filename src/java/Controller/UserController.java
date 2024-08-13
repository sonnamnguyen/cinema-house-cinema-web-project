package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private final String LOGIN_SERVELET = "LoginSevelet";
    private final String REGISTER_SERVELET = "RegisterSevelet";
    private final String LOGOUT_PAGE = "LogoutSevelet";
    private final String CHANGEPASSWORD_SEVELET = "ChangepasswordSevelet";
    private final String CHANGEINFORMATION_SEVELET = "ChangeInformationSevelet";
    private final String AUTHENTICATE_SEVELET = "AuthenticateSevelet";
    private final String MOVIECATEGORY_SEVELET = "MovieCategorySevelet";
    private final String SEARCH_SEVELET = "SearchSevelet";
    private final String MAHOAPASS_SEVELET = "AuthenPassSevelet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        try {
            String btAction = request.getParameter("btAction") + "";
            if (btAction.equals("login")) {
                url = LOGIN_SERVELET;
            } else if (btAction.equals("logout")) {
                url = LOGOUT_PAGE;
            } else if (btAction.equals("register")) {
                url = REGISTER_SERVELET;
            } else if (btAction.equals("changePassword")) {
                url = CHANGEPASSWORD_SEVELET;
            } else if (btAction.equals("changeInformation")) {
                url = CHANGEINFORMATION_SEVELET;
            } else if (btAction.equals("confirm")) {
                url = AUTHENTICATE_SEVELET;
            } else if (btAction.equals("movieCategory")) {
                url = MOVIECATEGORY_SEVELET;
            } else if (btAction.equals("search")) {
                url = SEARCH_SEVELET;
            } else if(btAction.equals("authen")) {
                url = MAHOAPASS_SEVELET;
            }
            System.out.println("Forwarding to: " + url);

        } finally {
            System.out.println("Before forwarding request to: " + url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            System.out.println("After forwarding request to: " + url); // This line will not be executed
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

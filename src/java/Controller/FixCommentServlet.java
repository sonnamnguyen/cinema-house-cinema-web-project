package Controller;

import DAO.CommentDAO;
import DTO.Comment;
import DTO.Movie;
import DTO.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FixCommentServlet extends HttpServlet {

    private final String SUCCESS_PAGE = "UserController";
    private final String ERROR_PAGE = "movie.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SUCCESS_PAGE;

        try {
            String content = request.getParameter("content");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String movieId = request.getParameter("movieId");
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            Movie movie = new Movie();
            movie.setIdMovie(movieId);

            Comment comment = new Comment();
            comment.setIdComment(UUID.randomUUID().toString());
            comment.setIdFilm(movie);
            comment.setIdAccount(user);
            comment.setContent(content);
            comment.setDateCreate(new Date(System.currentTimeMillis())); // mới thêm ở đây
            comment.setRating(rating);

            CommentDAO commentDAO = new CommentDAO();
            boolean success = commentDAO.insertComment(comment);
            if (success) {
                url = SUCCESS_PAGE + "?btAction=movieCategory" + "&movieId=" + movieId;
            } else {
                url = ERROR_PAGE;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            url = ERROR_PAGE;
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

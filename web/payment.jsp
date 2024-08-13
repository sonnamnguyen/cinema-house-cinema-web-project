<%@page import="java.sql.SQLException"%>
<%@page import="DTO.MovieScreeningSession"%>
<%@page import="DAO.ScreeningSessionDAO"%>
<%@page import="DAO.MovieDAO"%>
<%@page import="DTO.User"%>
<%@ page import="DTO.Movie" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinematic</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/bill.css">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="container-bill row">
            <h1 class="Money">Vui Lòng Thanh Toán</h1>
            <div class="col-7 form">
                <div class="form-section">   
                    <form action="PaymentServlet" method="POST">
                        <% 
                            String sessionTime = (String) request.getAttribute("sessionTime");
                            String roomname = (String) request.getAttribute("roomName");
                            String sessionDate = (String) request.getAttribute("sessionDate");
                            String seatIDs = (String) request.getAttribute("seatIDs");
                            String totalPrice = String.format("%.2f VND", (double) request.getAttribute("totalPrice"));
                            ScreeningSessionDAO ScreenSesion = new ScreeningSessionDAO();
                            MovieScreeningSession SS = null;
                            Movie MOVIE = null;
                            try {
                                SS = ScreenSesion.getMovieScreeningSession(sessionTime, roomname, sessionDate);
                                MovieDAO movie = new MovieDAO();
                                MOVIE = movie.getMovieBySessionDetails(sessionTime, roomname, sessionDate);
                            } catch (SQLException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }

                            User user = (User) session.getAttribute("user");
                            if (user != null) {
                        %>
                         <div class="Bank">
                            <img src="img/418901605_349127071330464_8077729781659123108_n.jpg" alt="VpBank">
                        </div>
                        <% 
                            }
                        %>
                        <input type="hidden" name="userId" value="<%=user.getIdAccount() %>">
                        <input type="hidden" name="MSSID" value="<%=SS != null ? SS.getIdMoviesession() : "" %>">
                        <input type="hidden" name="seatIDs" value="<%=seatIDs%>">
                        <input type="hidden" name="totalPrice" value="<%=request.getAttribute("totalPrice")%>">
                        <div class="form-group">
                            <small>(*): Bằng việc click/chạm vào Thanh Toán, bạn đã xác nhận hiểu rõ các Quy Định Giao Dịch Trực Tuyến của rạp phim</small>
                        </div>
                        
                        <div class="form-actions">
                            <a href="index.jsp" class="cancel">Hủy Thanh Toán</a> 
                            <button type="submit" class="confirm">Xác Nhận Thanh Toán</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-5">
                <div class="summary-section">
                    <% 
                        if (MOVIE != null) {
                    %>
                    <img src="<%=MOVIE.getCoverImage()%>" alt="<%=MOVIE.getCoverImage()%>">
                    <div class="movie-details">
                        <h2><%=MOVIE.getMovieName()%></h2>
                        <p>Đạo diễn: <%=MOVIE.getDirector()%></p>
                        <p><%=MOVIE.getAgeLimit()%></p>
                        <p>Thời Gian: <%=MOVIE.getDuration()%> phút</p>
                        <p>Xuất Chiếu: <%=sessionTime%> | <%=sessionDate%></p>
                        <p>Ghế: <%=seatIDs%></p>
                        <p>Tổng: <%=totalPrice%></p>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>

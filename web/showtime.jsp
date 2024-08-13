<%-- 
    Document   : showtime
    Created on : 29-Jun-2024, 12:31:53
    Author     : ADMIN
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="DAO.MovieDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.ScreeningSessionDAO"%>
<%@page import="DTO.MovieScreeningSession"%>
<%@page import="DTO.Movie"%>
<%@page import="java.sql.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Showtime</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/showtime.css">
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="booking-container">
        </div>

        <%-- Dynamic Movie Containers --%>
        <%  MovieDAO allMovie = new MovieDAO();
            List<Movie> movies = new ArrayList<>();
            ScreeningSessionDAO sessionDAO = new ScreeningSessionDAO();
            List<MovieScreeningSession> sessions = new ArrayList<>();
            try {
                sessions = sessionDAO.selectAllScreeningSessionAndMovie();
                movies = allMovie.selectAll(true);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (Movie  x : movies) {
                
                    String movieName = x.getMovieName();
                    String posterImage = x.getCoverImage();
        %>
        <div class="movie-container">
            <div class="poster">
                <img src="<%= posterImage%>" alt="<%= movieName%>">
            </div>
            <div class="details">
                <h1><%= movieName%></h1>
                <p><%= x.getCategory()%></p>
                <p><strong><%=x.getNationalOrigin() %></strong></p>
                <%
                    for(MovieScreeningSession sSession : sessions) {
                        if(sSession.getIdMovie().getIdMovie().equals(x.getIdMovie())){
                    
                %>
                <div class="showtimes">  
                    <a href="#" class="time"><%= sSession.getStartTime()%> EN</a>
                </div>
                <%}}%>
                <a href="#" class="format">2D</a>
                <form action="UserController" method="get" style="display:inline;">
                    <input type="hidden" name="btAction" value="movieCategory"/>
                    <input type="hidden" name="movieId" value="<%= x.getIdMovie()%>"/>
                    <button type="submit" class="book-button">Book Ticket</button>
                </form>
            </div>
        </div>
        <%
            
                }
        %>
        <jsp:include page="footer.jsp" />
    </body>
</html>

<%@page import="DAO.MovieDAO"%>
<%@page import="DTO.Movie"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies Showing</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/movieisshowing.css"> <!-- Đường dẫn tới file CSS mới -->
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="title2 text-light" style="margin-top: 150px">
            <h2>Movies Showing</h2>
        </div>
        <div class="container mt-4">
            <div class="row">
                <%
                    MovieDAO dao = new MovieDAO();
                    List<Movie> listMovie = dao.selectAll(true);
                    if (listMovie != null && !listMovie.isEmpty()) {
                        request.setAttribute("LIST_ALL_MOVIE", listMovie);
                    }
                    List<Movie> list = (List<Movie>) request.getAttribute("LIST_ALL_MOVIE");
                    if (list != null && !list.isEmpty()) {
                        for (Movie movie : list) {
                %>
                <div class="col-lg-3 col-md-4 col-sm-6 mb-4 movie-card">
                    <div class="card h-100">
                        <a href="UserController?btAction=movieCategory&movieId=<%= movie.getIdMovie()%>" class="card-link">
                            <img class="card-img-top" src="<%= movie.getCoverImage()%>" alt="<%= movie.getMovieName()%>">
                            <div class="card-body">
                                <h4 class="card-title text-white"><%= movie.getMovieName()%></h4>
                            </div>
                        </a>
                        <div class="info-action mt-2">
                            <a href="<%= movie.getTrailer()%>" class="btn btn-primary btn-sm" target="_blank">Watch Trailer</a>
                            <form action="UserController" method="post" style="display:inline;">
                                <input type="hidden" name="btAction" value="movieCategory"/>
                                <input type="hidden" name="movieId" value="<%= movie.getIdMovie()%>"/>
                                <button type="submit" class="btn btn-success btn-sm">Book Ticket</button>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <h1>EMPTY LIST</h1>
                <% }%>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>

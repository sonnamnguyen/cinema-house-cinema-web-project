<%@page import="DTO.Movie"%>
<%@page import="java.util.List"%>
<%@page import="DAO.MovieDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinematic</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <!-- header -->
        <jsp:include page="header.jsp" />

        <div class="row">
            <!-- Big Trailer Section -->
            <div class="col-12 big-trailer-section">
                <div class="trailer-container">
                    <video id="trailerVideo" class="trailer-video" src="img/Y2meta.app-1917 - Official Trailer [HD]-(1080p).mp4" autoplay loop muted></video>
                    <div class="trailer-overlay">
                        <div class="trailer-details">
                            <h2 id="trailerTitle">1917</h2>
                            <p id="trailerType">War, Drama</p>
                            <p id="trailerDate">Release Date: December 25, 2019</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="title1 text-light">
            <h2>MOVIE IS SHOWING</h2>
        </div>
        <div class="container mt-4">
            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div id="carouselExampleIndicators1" class="carousel slide mb-4" data-bs-ride="carousel" data-bs-interval="3000">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleIndicators1" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators1" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators1" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                            <%
                                MovieDAO dao = new MovieDAO();
                                List<Movie> listMovie = dao.selectAll(true);
                                if (listMovie != null && !listMovie.isEmpty()) {
                                    request.setAttribute("LIST_ALL_MOVIE", listMovie);
                                }
                                List<Movie> list = (List<Movie>) request.getAttribute("LIST_ALL_MOVIE");
                            %>
                            <% if (list != null && !list.isEmpty()) { %>
                            <div class="carousel-item active">
                                <div class="row">
                                    <% for (int i = 0; i < list.size(); i++) {
                                            Movie movie = list.get(i);%>
                                    <div class="col-3">
                                        <a href="UserController?btAction=movieCategory&movieId=<%= movie.getIdMovie()%>" style="text-decoration: none; color: inherit;">
                                            <div class="card">
                                                <img src="<%= movie.getCoverImage()%>" class="d-block w-100" alt="<%= movie.getMovieName()%>">
                                                <div class="info">
                                                    <div class="name"><font color="white"><%= movie.getMovieName()%></font></div>
                                                    <div class="info-action">
                                                        <a href="<%= movie.getTrailer()%>" class="btn btn-primary btn-sm" target="_blank">Watch Trailer</a>
                                                        <form action="UserController" method="POST" style="display:inline;">
                                                            <input type="hidden" name="btAction" value="movieCategory"/>
                                                            <input type="hidden" name="movieId" value="<%= movie.getIdMovie()%>"/>
                                                            <button type="submit" class="btn btn-success btn-sm">Book Ticket</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <% if ((i + 1) % 4 == 0 && (i + 1) < list.size()) { %>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="row">
                                    <% } %>
                                    <% } %>
                                </div>
                            </div>
                            <% } else { %>
                            <h1>EMPTY LIST</h1>
                            <% } %>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators1" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators1" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="center-align">
            <a href="movieisshowing.jsp" class="see-more-btn">See More</a>
        </div>

        <div class="title text-light">
            <h2>MOVIE COMING SOON</h2>
        </div>
        <div class="container mt-4">
            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div id="carouselExampleIndicators2" class="carousel slide mb-4" data-bs-ride="carousel" data-bs-interval="3000">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleIndicators2" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators2" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators2" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                            <%
                                boolean isOnline = false;
                                MovieDAO daoOff = new MovieDAO();
                                List<Movie> listMovieOff = daoOff.selectAll(isOnline);
                                if (listMovieOff != null && !listMovieOff.isEmpty()) {
                                    request.setAttribute("LIST_ALL_MOVIEOFF", listMovieOff);
                                }
                                List<Movie> listMovieSoon = (List<Movie>) request.getAttribute("LIST_ALL_MOVIEOFF");
                            %>
                            <% if (listMovieSoon != null && !listMovieSoon.isEmpty()) { %>
                            <div class="carousel-item active">
                                <div class="row">
                                    <% for (int i = 0; i < listMovieSoon.size(); i++) {
                                            Movie movieSoon = listMovieSoon.get(i);%>
                                    <div class="col-3">
                                        <a href="UserController?btAction=movieCategory&movieId=<%= movieSoon.getIdMovie()%>" style="text-decoration: none; color: inherit;">
                                            <div class="card">
                                                <img src="<%= movieSoon.getCoverImage()%>" class="d-block w-100" alt="<%= movieSoon.getMovieName()%>">
                                                <div class="info">
                                                    <div class="name"><font color="white"><%= movieSoon.getMovieName()%></font></div>
                                                    <div class="info-action">
                                                        <a href="<%= movieSoon.getTrailer()%>" class="btn btn-primary btn-sm" target="_blank">Watch Trailer</a>
                                                        <form action="UserController" method="POST" style="display:inline;">
                                                            <input type="hidden" name="btAction" value="movieCategory"/>
                                                            <input type="hidden" name="movieId" value="<%= movieSoon.getIdMovie()%>"/>                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                    <% if ((i + 1) % 4 == 0 && (i + 1) < listMovieSoon.size()) { %>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="row">
                                    <% } %>
                                    <% } %>
                                </div>
                            </div>
                            <% } else { %>
                            <h1>EMPTY LIST</h1>
                            <% }%>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators2" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators2" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="center-align">
            <a href="moviecommingsoon.jsp" class="see-more-btn">See More</a>
        </div>

        <section class="membership-program">
            <div class="container">
                <div class="title text-light">
                    <h2>MEMBERSHIP PROGRAM</h2>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="program">
                            <a href="learnmore.jsp">
                                <img src="img/z5579798578074_2b535e1eac749a62291e0e348c4eddac.jpg" alt="Program 1">
                                <h3>VIP SEAT</h3>
                            </a>

                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="program">
                            <a href="learnmore.jsp">
                                <img src="img/Black And Mustard Modern Member Id Card (1).png" alt="Program 2">
                                <h3>PREMIUM SEAT</h3>
                            </a>

                        </div>
                    </div>
                    <p class="premium-member-text">BECOME OUR PREMIUM MEMBER</p>
                    <a href="learnmore.jsp" class="btn btn-primary mt-3 learn-more-btn">Learn More</a>

                </div>
            </div>
        </section>

        <!-- footer -->
        <%@include file="footer.jsp" %>
    </body>
</html>

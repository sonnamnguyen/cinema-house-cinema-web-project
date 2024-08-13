<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="DTO.Cinema"%>
<%@page import="DAO.CinemaDAO"%>
<%@page import="DTO.User"%>
<%@page import="DTO.Comment"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CommentDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/aboutus.css">
        <style>
            .nav-item.dropdown:hover .dropdown-menu {
                display: block;
                margin-top: 0; /* Đảm bảo menu hiển thị ngay dưới mục */
            }

        </style>

    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="header">
            <h1>CINESTAR CINEMAS</h1>
            <p>
                Cinestar is one of the most popular cinema systems in Vietnam, providing many attractive entertainment models including modern Cinema Complexes, Theaters, Kidzone children's play areas, Bowling, Billiards, Games, Gym, Restaurant, and C'beer Beer Street. With the goal of becoming an entertainment destination for every Vietnamese family, Cinestar is known as a theater complex that supports Vietnamese films, contributing to the development of Vietnamese cinema. Not only does it show international blockbusters, Cinestar also accompanies Vietnamese filmmakers bringing unique Vietnamese cinema works closer to big audience.
            </p>
        </div>

        <section class="mission">
            <h2>MISSION</h2>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="mission-card">
                            <h3>01</h3>
                            <p>Contribute to the growth of Vietnamese cinema, culture and entertainment market share</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mission-card">
                            <h3>02</h3>
                            <p>Develop excellent services with optimal prices, suitable for Vietnamese people's income.</p>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="mission-card">
                            <h3>03</h3>
                            <p>Bringing Vietnamese cinema art and culture to the international stage</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="location">

            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="vietnam">
                            <img src="img/vietnam.png" alt="vietnam">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="location-card">
                            <%
                                CinemaDAO cinema = new CinemaDAO();
                                List<Cinema> cinemas = new ArrayList<>();
                                try {
                                    cinemas = cinema.getAllByStatus(true);
                                } catch (SQLException | ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                for (Cinema myCinema : cinemas) {


                            %>
                            <div class="card">
                                <h3><%=myCinema.getCinemaName()%></h3>
                                <p>5 auditoriums with 725 seats</p>
                                <p>📍 <%=myCinema.getAddress()%></p>
                            </div>
                            <%}%>
                        </div>
                    </div>

                </div>
            </div>
        </section>


        <%@include file="footer.jsp" %>
    </body>
</html>

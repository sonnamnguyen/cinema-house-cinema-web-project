<%@ page import="DAO.MovieDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.util.Pair" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
    </head>
    <body>
        <div class="wrapper">
            <jsp:include page="leftbar.jsp" />
            <div class="main p-3">
                <div class="text-center">
                    <h2>Earnings Dashboard</h2>
                    <div class="chart-container">
                        <canvas id="mostWatchedMoviesChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>
        <script>
            <%
                MovieDAO movieDAO = new MovieDAO();
                List<Pair<String, Integer>> topMovies = movieDAO.getTop5MoviesBasedOnTickets();
                StringBuilder labels = new StringBuilder();
                StringBuilder data = new StringBuilder();
                for (Pair<String, Integer> movie : topMovies) {
                    labels.append("\"").append(movie.getKey()).append("\",");
                    data.append(movie.getValue()).append(",");
                }
                if (labels.length() > 0) {
                    labels.setLength(labels.length() - 1); // Remove last comma
                    data.setLength(data.length() - 1); // Remove last comma
                }
            %>

    const ctx = document.getElementById('mostWatchedMoviesChart').getContext('2d');
    const mostWatchedMoviesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [<%= labels.toString()%>], // Dynamic labels
            datasets: [{
                    label: 'Number of Tickets',
                    data: [<%= data.toString()%>], // Dynamic data
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Number of Tickets'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Movies'
                    }
                }
            }
        }
    });
        </script>
    </body>
</html>

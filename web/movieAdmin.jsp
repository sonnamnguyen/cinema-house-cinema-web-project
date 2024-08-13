<%-- 
    Document   : movieAdmin
    Created on : Jun 21, 2024, 12:21:27 PM
    Author     : admin
--%>

<%@page import="java.sql.SQLException"%>
<%@ page import="java.util.List" %>
<%@ page import="DTO.Movie" %>
<%@ page import="DAO.MovieDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movie List</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
    <link rel="stylesheet" href="leftbar.css">
    <link rel="stylesheet" href="movietable.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
    <script>
        $(document).ready(function() {
            $("#searchInput").autocomplete({
                source: function(request, response) {
                    $.ajax({
                        url: "SearchAutoCompleteServeletIndex",
                        dataType: "json",
                        data: {
                            term: request.term
                        },
                        success: function(data) {
                            response(data);
                        }
                    });
                },
                minLength: 1 
            });
        });
    </script>
</head>
<body>
    <div class="wrapper">
        <jsp:include page="leftbar.jsp" />
        <div class="main p-3">
            <div class="title">
                <h2>MOVIE LIST</h2>
            </div>
            <div class="search-container mb-3 p-3 border border-orange rounded">
                <!-- Form to Create a New Movie -->
                <button type="button" class="btn c-btn mb-2" onclick="window.location.href = 'createmovie.jsp'">+ Create Movie</button>

                <!-- Form to Search for Movies -->
                <form action="AdminController" method="GET">
                    <div class="left">
                        <input type="text" id="searchInput" placeholder="Movie Name" name="MovieName" class="form-control mb-2" />
                        <input type="hidden" name="btAction" value="SearchMovie" />
                        <button type="submit" class="btn btn-search c-btn mb-2">
                            <i class="lni lni-search-alt"></i> Search
                        </button>
                    </div>
                </form>

                <%
                     int currentPage = 1;
                        int recordsPerPage = 7;
                        if (request.getParameter("currentPage") != null) {
                            currentPage = Integer.parseInt(request.getParameter("currentPage"));
                        }
                        List<Movie> list = null;
                        int totalRecords = 0;
                        try {
                            MovieDAO dao = new MovieDAO();
                            list = dao.selectAllMovies((currentPage - 1) * recordsPerPage, recordsPerPage);
                            totalRecords = dao.getTotalRecords();
                            request.setAttribute("LIST_ALL_BILL", list);
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    
                %>
            </div>
            <table id="movieTable" class="table table-striped">
                <thead>
                    <tr>
                        <th class="one">Movie Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>National Origin</th>
                        <th>Duration (minutes)</th>
                        <th>Status</th>
                        <th>Update/Delete</th>
                    </tr>
                </thead>
                <tbody id="movieTableBody">
                    <% if (list != null && !list.isEmpty()) { %>
                    <% for (Movie movie : list) {%>
                    <tr>
                        <td class="one"><%= movie.getMovieName()%></td>
                        <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(movie.getEffectiveDateFrom())%></td>
                        <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(movie.getEffectiveDateTo())%></td>
                        <td><%= movie.getNationalOrigin()%></td>
                        <td><%= movie.getDuration()%></td>
                        <td><%= movie.isStatus() ? "Active" : "Offline"%></td>
                        <td>
                            <form action="AdminController" method="POST" style="display:inline;">
                                <input type="hidden" name="btAction" value="DeleteMovies" />
                                <input type="hidden" name="movieID" value="<%= movie.getIdMovie()%>" />
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>

                            <form action="AdminController" method="GET" style="display:inline;">
                                <input type="hidden" name="btAction" value="UpdateMovies" />
                                <input type="hidden" name="movieID" value="<%= movie.getIdMovie()%>" />
                                <button type="submit" class="btn btn-primary btn-sm">Update</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td colspan="7" class="text-center">EMPTY LIST</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>

           <div>
 <%
                        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
                        int maxVisiblePages = 10;
                        int startPage = Math.max(1, currentPage - maxVisiblePages / 2);
                        int endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);

                        if (endPage - startPage < maxVisiblePages) {
                            startPage = Math.max(1, endPage - maxVisiblePages + 1);
                        }
                    %>
                    <ul class="pagination">
                        <% if (currentPage > 1) {%>
                        <li class="page-item">
                            <a class="page-link" href="?currentPage=<%= currentPage - 1%>">Previous</a>
                        </li>
                        <% } %>

                        <% if (startPage > 1) { %>
                        <li class="page-item"><a class="page-link" href="?currentPage=1">1</a></li>
                            <% if (startPage > 2) { %>
                        <li class="page-item"><span class="page-link">...</span></li>
                            <% } %>
                            <% } %>

                        <% for (int i = startPage; i <= endPage; i++) {%>
                        <li class="page-item <%= (i == currentPage) ? "active" : ""%>">
                            <a class="page-link" href="?currentPage=<%= i%>"><%= i%></a>
                        </li>
                        <% } %>

                        <% if (endPage < totalPages) { %>
                        <% if (endPage < totalPages - 1) { %>
                        <li class="page-item"><span class="page-link">...</span></li>
                            <% }%>
                        <li class="page-item"><a class="page-link" href="?currentPage=<%= totalPages%>"><%= totalPages%></a></li>
                            <% } %>

                        <% if (currentPage < totalPages) {%>
                        <li class="page-item">
                            <a class="page-link" href="?currentPage=<%= currentPage + 1%>">Next</a>
                        </li>
                        <% }%>
                    </ul>
                </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    <script src="admin.js"></script>
</body>
</html>

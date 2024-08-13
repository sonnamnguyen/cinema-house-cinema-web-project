<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="DTO.MovieScreeningSession"%>
<%@page import="DAO.ScreeningSessionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
    <script>
        $(document).ready(function() {
            $("#tags").autocomplete({
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
                <h2>MEMBERS</h2>
            </div>
            <div class="search-container mb-3 p-3 border border-orange rounded">
                <button type="button" class="btn c-btn mb-2" onclick="window.location.href = 'createsession.jsp'">+ Create Screening Session</button>
                <form action="AdminController" method="GET">
                    <div class="left">
                        <input type="text" id="tags" placeholder="Please inform information you want to search" name="Information" class="form-control mb-2" />
                        <div class="mb-2">
                            <input type="date" id="startDateInput" name="ScreenDate" class="form-control" />
                        </div>
                        <input type="hidden" name="btAction" value="SearchInformationScreen" />
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
                    List<MovieScreeningSession> listSession = null;
                    int totalRecords = 0;
                    try {
                        ScreeningSessionDAO dao = new ScreeningSessionDAO();
                        listSession = dao.selectScreeningSessions((currentPage - 1) * recordsPerPage, recordsPerPage);
                        totalRecords = dao.countAllSessions();
                        request.setAttribute("LIST_ALL_SESSION", listSession);
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    List<MovieScreeningSession> list = (List<MovieScreeningSession>) request.getAttribute("LIST_ALL_SESSION");
                %>

                <table id="movieTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th class="one">STT</th>
                            <th>MovieName</th>
                            <th>CinemaName</th>
                            <th>Cinemaroom</th>
                            <th>RoomCategory</th>
                            <th>Screening Date</th>
                            <th>Screening Start</th>
                            <th>Screening End</th>
                            <th>Update/Delete</th>
                        </tr>
                    </thead>
                    <tbody id="movieTableBody">
                        <% if (list != null && !list.isEmpty()) { %>
                        <% for (int i = 0; i < list.size(); i++) {
                            MovieScreeningSession session1 = list.get(i);
                        %>
                        <tr>
                            <td class="one"><%= (currentPage - 1) * recordsPerPage + (i + 1) %></td>
                            <td><%= session1.getIdMovie().getMovieName() %></td>
                            <td><%= session1.getIdMovieRoom().getIdCinema().getCinemaName() %></td>
                            <td><%= session1.getIdMovieRoom().getRoomName() %></td>
                            <td><%= session1.getIdMovieRoom().getIdCinemaRoom()%></td>
                            <td><%= session1.getScreeningDate() %></td>
                            <td><%= session1.getStartTime() %></td>
                            <td><%= session1.getEndTime() %></td>
                            <td>
                                <button class="btn btn-warning" onclick="window.location.href = 'updatesession.jsp?id=<%= session1.getIdMoviesession() %>'">Update</button>
                                <button class="btn btn-danger" onclick="window.location.href = 'deletesession.jsp?id=<%= session1.getIdMoviesession() %>'">Delete</button>
                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="9">No sessions found.</td>
                        </tr>
                        <% } %>
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
                        <% if (currentPage > 1) { %>
                            <li class="page-item">
                                <a class="page-link" href="?currentPage=<%= currentPage - 1 %>">Previous</a>
                            </li>
                        <% } %>

                        <% if (startPage > 1) { %>
                            <li class="page-item"><a class="page-link" href="?currentPage=1">1</a></li>
                            <% if (startPage > 2) { %>
                                <li class="page-item"><span class="page-link">...</span></li>
                            <% } %>
                        <% } %>

                        <% for (int i = startPage; i <= endPage; i++) { %>
                            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                                <a class="page-link" href="?currentPage=<%= i %>"><%= i %></a>
                            </li>
                        <% } %>

                        <% if (endPage < totalPages) { %>
                            <% if (endPage < totalPages - 1) { %>
                                <li class="page-item"><span class="page-link">...</span></li>
                            <% } %>
                            <li class="page-item"><a class="page-link" href="?currentPage=<%= totalPages %>"><%= totalPages %></a></li>
                        <% } %>

                        <% if (currentPage < totalPages) { %>
                            <li class="page-item">
                                <a class="page-link" href="?currentPage=<%= currentPage + 1 %>">Next</a>
                            </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

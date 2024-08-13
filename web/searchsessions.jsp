<%-- 
    Document   : searchsessions
    Created on : Jun 26, 2024, 6:04:08 PM
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.MovieScreeningSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
    <link rel="stylesheet" href="leftbar.css">
    <link rel="stylesheet" href="movietable.css">
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
        <input type="text" id="searchInput" placeholder="Please inform information you want to search" name="Information" class="form-control mb-2" />
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
                  
                    List<MovieScreeningSession> list = (List<MovieScreeningSession>) request.getAttribute("LIST_ALL_SEARCH");
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
                            <td class="one"><%= i + 1 %></td>
                            <td><%= session1.getIdMovie().getMovieName() %></td>
                            <td><%= session1.getIdMovieRoom().getIdCinema().getCinemaName() %></td>
                            <td><%= session1.getIdMovieRoom().getRoomName() %></td>
                            <td><%= session1.getIdMovieRoom().getIdCinemaRoom()%></td>
                            <td><%= session1.getScreeningDate() %></td>
                            <td><%= session1.getStartTime() %></td>
                            <td><%= session1.getEndTime() %></td>
                            <td>
                                <form action="AdminController" method="POST" style="display:inline;">
                                        <input type="hidden" name="btAction" value="DeleteSession" />
                                    <input type="hidden" name="sessionID" value="<%= session1.getIdMoviesession() %>" />
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>

                                <form action="AdminController" method="GET" style="display:inline;">
                                    <input type="hidden" name="btAction" value="UpdateSession" />
                                    <input type="hidden" name="sessionID" value="<%= session1.getIdMoviesession() %>" />
                                    <button type="submit" class="btn btn-primary btn-sm">Update</button>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="9" class="text-center">EMPTY LIST</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    <script src="admin.js"></script>
</body>
</html>

<%-- 
    Document   : searchmovies
    Created on : Jun 23, 2024, 11:51:11 AM
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                    <form action="AdminController" method="GET">
                        <input type="hidden" name="btAction" value="CreateMovie" />
                        <button type="submit" class="btn c-btn mb-2">+ Tạo mới phim</button>
                    </form>

                    <!-- Form to Search for Movies -->
                    <form action="AdminController" method="GET">
                        <div class="left">
                            <!-- Movie Name Input -->
                            <input type="text" id="searchInput" placeholder="Tên phim" name="MovieName" class="form-control mb-2" />
                            <!-- Start Date Input -->
                            <div class="mb-2">
                                <input type="date" id="startDateInput" placeholder="Ngày khởi chiếu" name="ScreenStart" class="form-control" />
                            </div>
                            <!-- End Date Input -->
                            <div class="mb-2">
                                <input type="date" id="endDateInput" placeholder="Ngày kết thúc" name="ScreenEnd" class="form-control" />
                            </div>
                            <input type="hidden" name="btAction" value="SearchMovie" />
                            <button type="submit" class="btn btn-search c-btn mb-2">
                                <i class="lni lni-search-alt"></i> Search
                            </button>
                        </div>
                    </form>
                    <%
                    
                        
                        List<Movie> list = (List<Movie>) request.getAttribute("LIST_ALL_SEARCH");
                    %>
                </div>
                <table id="movieTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th class="one">Phim</th>
                            <th>Ngày khởi chiếu</th>
                            <th>Ngày kết thúc</th>
                            <th>Hãng phim</th>
                            <th>Thời lượng (phút)</th>
                            <th>Phiên bản</th>
                            <th>Sửa/Xóa</th>
                        </tr>
                    </thead>
                    <tbody id="movieTableBody">
                        <% if (list != null && !list.isEmpty()) { %>
                            <% for (Movie movie : list) { %>
                                <tr>
                                    <td class="one"><%= movie.getMovieName() %></td>
                                    <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(movie.getEffectiveDateFrom()) %></td>
                                    <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(movie.getEffectiveDateTo()) %></td>
                                    <td><%= movie.getNationalOrigin() %></td>
                                    <td><%= movie.getDuration() %></td>
                                    <td><%= movie.isStatus() ? "Active" : "Offline" %></td>
                                    <td>
                                        <form action="AdminController" method="POST" style="display:inline;">
                                            <input type="hidden" name="btAction" value="DeleteMovies" />
                                            <input type="hidden" name="movieID" value="<%= movie.getIdMovie() %>" />
                                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                        </form>

    <form  action="AdminController" method="GET" style="display:inline;">
       
        <input type="hidden" name="btAction" value="UpdateMovies" />
        <input type="hidden" name="movieID" value="<%= movie.getIdMovie() %>" />
        <button type="submit" class="btn btn-primary btn-sm">Update</button>
       
    </form>


                                    </td>
                                </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="7" class="text-center">EMPTY LIST</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
        <script src="admin.js"></script>
    </body>
</html>

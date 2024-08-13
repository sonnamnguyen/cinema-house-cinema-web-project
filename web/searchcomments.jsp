<%-- 
    Document   : searchcomments
    Created on : Jun 25, 2024, 3:55:34 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CommentDAO"%>
<%@page import="DTO.Comment"%>
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
                    <!-- Form to Search for Comments -->
                    <form action="AdminController" method="GET">
                        <div class="left">
                            <input type="text" id="searchInput" placeholder="Please inform information you want to search" name="Information" class="form-control mb-2" />
                             <div class="mb-2">
                                <input type="date" id="startDateInput" placeholder="Date" name="DateComment" class="form-control" />
                            </div>
                            <input type="hidden" name="btAction" value="SearchInfo" />
                            <button type="submit" class="btn btn-search c-btn mb-2">
                                <i class="lni lni-search-alt"></i> Search
                            </button>
                        </div>
                    </form>
                    
                    <%
                    
                       List<Comment> list = (List<Comment>) request.getAttribute("CommentSearch");
                    %>
                </div>
                <table id="movieTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th class="one">STT</th>
                            <th>AccountName</th>
                            <th>MovieName</th>
                            <th>Comment</th>
                            <th>Date</th>
                            <th>Rating</th>
                            <th>Status</th>
                            <th>Update/Delete</th>
                        </tr>
                    </thead>
                    <tbody id="movieTableBody">
                        <% if (list != null && !list.isEmpty()) { %>
                        <% for (int i = 0; i < list.size(); i++) { %>
                        <tr>
                            <td class="one"><%= i + 1 %></td>
                            <td><%= list.get(i).getIdAccount().getAccountName() %></td>
                            <td><%= list.get(i).getIdFilm().getMovieName() %></td>
                            <td><%= list.get(i).getContent() %></td>
                            <td><%= list.get(i).getDateCreate()%></td>
                            <td><%= list.get(i).getRating() %></td>
                            <td><%= list.get(i).isStatus() ? "Approved" : "Not Approved" %></td>
                            <td>
                                <form action="AdminController" method="POST" style="display:inline;">
                                    <input type="hidden" name="btAction" value="DeleteComment" />
                                    <input type="hidden" name="commentID" value="<%= list.get(i).getIdComment() %>" />
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                             <form action="AdminController" method="GET" style="display:inline;">
    <input type="hidden" name="btAction" value="ApprovedComment" />
    <input type="hidden" name="commentID" value="<%= list.get(i).getIdComment() %>" />
    <input type="hidden" name="status" value="<%= list.get(i).isStatus() %>" />
    <button type="submit" 
            class="btn btn-sm <%= list.get(i).isStatus() ? "btn-success" : "btn-primary" %>">
        <%= list.get(i).isStatus() ? "Approved" : "Not Approved" %>
    </button>
</form>
                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="8" class="text-center">EMPTY LIST</td>
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

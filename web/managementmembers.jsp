<%-- 
    Document   : managementmembers
    Created on : Jun 23, 2024, 8:26:08 PM
    Author     : admin
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="DAO.UserDAO"%>
<%@page import="DTO.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Management</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
        <link rel="stylesheet" href="leftbar.css">
        <link rel="stylesheet" href="movietable.css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
        <script>
            $(document).ready(function () {
                $("#searchInput").autocomplete({
                    source: function (request, response) {
                        $.ajax({
                            url: "SearchAutoCompleteAccountServlet",
                            dataType: "json",
                            data: {
                                term: request.term
                            },
                            success: function (data) {
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
                    <!-- Buttons to Create and Search Users -->
                    <button type="button" class="btn c-btn mb-2" onclick="window.location.href = 'createuser.jsp'">+ Create user</button>
                    <form action="AdminController" method="GET">
                        <div class="left">
                            <input type="text" id="searchInput" placeholder="Search" name="Information" class="form-control mb-2" />
                            <input type="hidden" name="btAction" value="SearchInformation" />
                            <button type="submit" class="btn btn-search c-btn mb-2">
                                <i class="lni lni-search-alt"></i> Search
                            </button>
                        </div>
                    </form>
                    <%-- Pagination Logic --%>
                    <%
                        int currentPage = 1;
                        int recordsPerPage = 7;
                        if (request.getParameter("currentPage") != null) {
                            currentPage = Integer.parseInt(request.getParameter("currentPage"));
                        }
                        List<User> list = null;
                        int totalRecords = 0;
                        try {
                            UserDAO dao = new UserDAO();
                            list = dao.selectAll((currentPage - 1) * recordsPerPage, recordsPerPage);
                            totalRecords = dao.countAllUsers();
                            request.setAttribute("LIST_ALL_USER", list);
                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    %>
                </div>
                <table id="userTable" class="table table-striped">
                    <thead>
                        <tr>
                            <th class="one">Account Name</th>
                            <th>Gender</th>
                            <th>Address</th>
                            <th>Phone Number</th>
                            <th>Year of Birth</th>
                            <th>Email</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="userTableBody">
                        <% if (list != null && !list.isEmpty()) { %>
                        <% for (User user : list) {%>
                        <tr>
                            <td class="one"><%= user.getAccountName()%></td>
                            <td><%= user.getGender()%></td>
                            <td><%= user.getAddress()%></td>
                            <td><%= user.getPhoneNumber()%></td>
                            <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(user.getYob())%></td>
                            <td><%= user.getEmail()%></td>
                            <td><%= user.isIsAdmin() ? "Admin" : "User"%></td>
                            <td>
                                <form action="AdminController" method="POST" style="display:inline;">
                                    <input type="hidden" name="btAction" value="DeleteUser" />
                                    <input type="hidden" name="userID" value="<%= user.getIdAccount()%>" />
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>

                                <form action="AdminController" method="GET" style="display:inline;">
                                    <input type="hidden" name="btAction" value="UpdateUser" />
                                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
                                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
                                    <script src="admin.js"></script>
                                    </body>
                                    </html>
                                    <input type="hidden" name="userID" value="<%= user.getIdAccount()%>" />
                                    <button type="submit" class="btn btn-primary btn-sm">Update</button>
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

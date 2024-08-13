<%@page import="java.sql.SQLException"%>
<%@page import="DAO.CommentDAO"%>
<%@page import="DTO.Comment"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Comment Management</title>
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.3/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.3/jquery-ui.js"></script>
    <script>
        $(document).ready(function() {
            $("#searchInput").autocomplete({
                source: function(request, response) {
                    $.ajax({
                        url: "SearchAutoCompleteServelet",
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
                <h2>Comment Management</h2>
            </div>
            <div class="search-container mb-3 p-3 border border-orange rounded">
                <!-- Form to Search for Comments -->
                <form action="AdminController" method="GET">
                    <div class="left">
                        <input type="text" id="searchInput" placeholder="Please enter information you want to search" name="Information" class="form-control mb-2" />
                        <div class="mb-2">
                            <input type="date" id="startDateInput" name="DateComment" class="form-control" />
                        </div>
                        <input type="hidden" name="btAction" value="SearchInfo" />
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
                        List<Comment> list = null;
                        int totalRecords = 0;
                        try {
                            CommentDAO dao = new CommentDAO();
                            list = dao.selectAllComments((currentPage - 1) * recordsPerPage, recordsPerPage);
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
                        <td class="one"><%= (currentPage - 1) * recordsPerPage + i + 1 %></td>
                        <td><%= list.get(i).getIdAccount().getAccountName() %></td>
                        <td><%= list.get(i).getIdFilm().getMovieName() %></td>
                        <td><%= list.get(i).getContent() %></td>
                        <td><%= list.get(i).getDateCreate() %></td>
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
                                <button type="submit" class="btn btn-sm <%= list.get(i).isStatus() ? "btn-success" : "btn-primary" %>">
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
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
</body>
</html>

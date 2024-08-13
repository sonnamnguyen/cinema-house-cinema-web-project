<%-- 
    Document   : ticketmanagement
    Created on : Jun 26, 2024, 9:22:44 PM
    Author     : admin
--%>

<%@page import="DAO.BillDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="DTO.Bill"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Management</title>
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
                            url: "SearchAutoCompleteServeletIndex",
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
                    <h2>TICKET MANAGEMENT</h2>
                </div>
                <div class="search-container mb-3 p-3 border border-orange rounded">
                    <!-- Form to Search for Bills -->
                    <form action="AdminController" method="GET">
                        <div class="left">
                            <input type="text" id="searchInput" placeholder="Search" name="billName" class="form-control mb-2" />
                            <input type="hidden" name="btAction" value="SearchBill" />
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
                        List<Bill> list = null;
                        int totalRecords = 0;
                        try {
                            BillDAO dao = new BillDAO();
                            list = dao.selectAllBill((currentPage - 1) * recordsPerPage, recordsPerPage);
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
                            <th>UserName</th>
                            <th>PhoneNumber</th>
                            <th>Email</th>
                            <th>MovieName</th>
                            <th>Bill Date</th>
                            <th>Screening Start</th>
                            <th>Total Amount</th>
                            <th>Accept Ticket</th>
                        </tr>
                    </thead>
                    <tbody id="movieTableBody">
                        <% if (list != null && !list.isEmpty()) { %>
                        <% for (int i = 0; i < list.size(); i++) {
                                Bill bill = list.get(i);
                        %>
                        <tr>
                            <td class="one"><%= (currentPage - 1) * recordsPerPage + i + 1%></td>
                            <td><%= bill.getIdAccount().getAccountName()%></td>
                            <td><%= bill.getIdAccount().getPhoneNumber()%></td>
                            <td><%= bill.getIdAccount().getEmail()%></td>
                            <td>
                                <%= bill.getIdTicket() != null
                                        && bill.getIdTicket().getIdMovieScreeningSession() != null
                                        && bill.getIdTicket().getIdMovieScreeningSession().getIdMovie() != null
                                        ? bill.getIdTicket().getIdMovieScreeningSession().getIdMovie().getMovieName()
                                        : "N/A"%>
                            </td>
                            <td><%= bill.getDateCreated()%></td>
                            <td><%= bill.getTimeCreated()%></td>
                            <td><%= bill.getTotalMoney()%></td>
                            <td>
                                <% if(!bill.isStatus()){ %>
                                <form action="AdminController" method="POST" style="display:inline;" onsubmit="this.querySelector('button').disabled = true; this.querySelector('button').style.display = 'none';">
                                        <input type="hidden" name="btAction" value="AcceptBill" />
                                        <input type="hidden" name="billID" value="<%= bill.getIdBill()%>" />
                                        <input type="hidden" name="status" value="<%= list.get(i).isStatus()%>" />
                                        <button type="submit" class="btn btn-danger btn-sm">Accept</button>
                                    </form>
                                 <% }if(bill.isStatus()){ %>
                                    <form action="AdminController" method="GET" style="display:inline;" onsubmit="this.querySelector('button').disabled = true; this.querySelector('button').style.display = 'none';">
                                        <input type="hidden" name="btAction" value="UnacceptBill" />
                                        <input type="hidden" name="billID" value="<%= bill.getIdBill()%>" />
                                        <input type="hidden" name="status" value="<%= list.get(i).isStatus()%>" />
                                        <button type="submit" class="btn btn-primary btn-sm">Not Accept</button>
                                    </form>
                                    <% } %>
                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="9" class="text-center">EMPTY LIST</td>
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
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    </body>
</html>

<%@page import="DTO.Bill"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DTO.User"%>
<%@page import="DAO.BillDAO"%>
<%@page import="java.util.ArrayList"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket History</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/history.css">
        <link rel="stylesheet" href="css/buttoncss.css">
    </head>
    <body>
        <%-- Include header --%>
        <jsp:include page="header.jsp" />

        <div class="container mt-5">
            <div class="booking-history">
                <h2>Lịch sử đặt vé</h2>
            </div>
            <table class="table table-bordered">
                <thead class="table-warning">
                    <tr>
                        <th>#</th>
                        <th>Ngày</th>
                        <th>Số giao dịch</th>
                        <th>Phòng chiếu</th>
                        <th>Tên Phim</th>
                        <th>Tổng Tiền vé (VND)</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int currentPage = 1;
                        int recordsPerPage = 7;
                         int totalRecords = 0;
                        if (request.getParameter("currentPage") != null) {
                            currentPage = Integer.parseInt(request.getParameter("currentPage"));
                        }
                        User user = (User) session.getAttribute("user");
                        if (user != null) {
                            BillDAO billDAO = new BillDAO();
                            List<Bill> userBills = new ArrayList<>();
                            try {
                                String userId = user.getIdAccount();
                                userBills = billDAO.selectAllBillForUser(userId,(currentPage - 1) * recordsPerPage, recordsPerPage);
                                totalRecords = billDAO.getTotalRecordsByUser(userId);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (userBills != null && !userBills.isEmpty()) {
                                int count = 1;
                                for (Bill bill : userBills) {
                                    if(bill.isStatus()){
                    %>
                    <tr>
                        <td><%= count%></td>
                        <td><%= bill.getDateCreated()%></td>
                        <td><%= bill.getIdBill()%></td>
                        <td><%= bill.getIdTicket().getIdMovieScreeningSession().getIdMovieRoom().getRoomName()%></td>
                        <td><%= bill.getIdTicket().getIdMovieScreeningSession().getIdMovie().getMovieName()%></td>
                        <td>₫<%= bill.getTotalMoney()%></td>
                    </tr>
                    <%
                                    count++;
                                    }
                                }
                            }
                        }
                    %>
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
            <%
                if (user == null) {
            %>

            <% }%>
        </div>

        <%-- Include footer --%>
        <jsp:include page="footer.jsp" />
    </body>
</html>

<%@page import="DTO.Ticket"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="DTO.MovieScreeningSession"%>
<%@page import="DAO.ScreeningSessionDAO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="DTO.Seat"%>
<%@page import="DTO.SeatCategory"%>
<%@page import="DTO.CinemaRoom"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.SeatDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Choose Seats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/buttoncss.css">
    <link rel="stylesheet" href="css/chooseseat.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <h3>Địa chỉ: 216 Võ Văn Ngân, Bình Thọ, Thủ Đức, Thành phố Hồ Chí Minh</h3>
    <%
        String sessionTime = (String) request.getAttribute("sessionTime");
        String roomname = (String) request.getAttribute("roomName");
        String sessionDate = (String) request.getAttribute("sessionDate");
        ScreeningSessionDAO ScreenSesion = new ScreeningSessionDAO();
        TicketsDAO ticketSeat = new TicketsDAO();
        MovieScreeningSession SS = null;

        List<Seat> seats = new ArrayList<>();
        Map<String, List<Seat>> seatsByCategory = new HashMap<>();
        try {
            SeatDAO dao = new SeatDAO();
            seats = dao.selectAllSeatsByCinemaRoomName(roomname);

            // Use HashMap to group seats by category
            for (Seat seat : seats) {
                String categoryId = seat.getIdSeatCategory().getIdCategory();
                if (!seatsByCategory.containsKey(categoryId)) {
                    seatsByCategory.put(categoryId, new ArrayList<Seat>());
                }
                seatsByCategory.get(categoryId).add(seat);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    %>
    <h5>Room Name: <%= roomname %></h5>
    <h5>Screening Start: <%= sessionTime %></h5>
    <h5>Screening Date: <%= sessionDate %></h5>
    <section class="ticket-selection">
        <h2>SELECT TICKETS</h2>
        <div class="ticket-options row">
            <%
                // Iterate through unique categories
                for (String categoryId : seatsByCategory.keySet()) {
                    SeatCategory category = seatsByCategory.get(categoryId).get(0).getIdSeatCategory();
            %>
            <div class="ticket-box col-4" data-price="<%= category.getPrice() %>">
                <h3><%= category.getCategoryName() %></h3>
                <p><%= category.getPrice() %> VND</p>
                <div class="counter">
                    <button class="decrement" onclick="updateTicketCount('<%= category.getCategoryName().toLowerCase() %>', -1)">-</button>
                    <span id="<%= category.getCategoryName().toLowerCase() %>-count" class="count">0</span>
                    <button class="increment" onclick="updateTicketCount('<%= category.getCategoryName().toLowerCase() %>', 1)">+</button>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </section>

    <section class="seat-selection">
        <h2>CHOOSE SEATS</h2>
        <div class="screen">Màn hình</div>
        <form action="PaymentSevelet" method="GET" onsubmit="prepareSeatInputs()">
            <div class="seat-grid">
                <%
                    int rows = 8; // Number of rows in the grid (adjust as needed)
                    int cols = 10; // Number of columns in the grid (adjust as needed)
                    int seatIndex = 0;

                    for (int i = 0; i < rows; i++) {
                %>
                <div class="seat-row">
                    <%
                        for (int j = 0; j < cols; j++) {
                            if (seatIndex < seats.size()) {
                                Seat seat = seats.get(seatIndex);
                                String seatId = seat.getIdSeat();
                                String seatClass = seat.getIdSeatCategory().getCategoryName().toLowerCase();
                                SS = ScreenSesion.getMovieScreeningSession(sessionTime, roomname, sessionDate);
                                String MSS = SS.getIdMoviesession();
                                boolean movieTicketSeat = ticketSeat.doesTicketExist(seatId, MSS);
                                if (!movieTicketSeat) {
                    %>
      <button type="button" name="seatId" value="<%= seatId %>" class="seat-button <%= seatClass %>" onclick="selectSeat(this)" disabled>
                        <%= seatId %>
      </button>
                    <%
                                } else {
                    %>
                    <button type="button" class="booked-seat" disabled>
                        <%= seatId %>
                    </button>
                    <%
                                }

                                seatIndex++;
                            }
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
            <div class="buy-tickets-card hidden">
                <div class="price-info">
                    <span>Temporary price calculation</span>
                    <span class="total-price">0 VND</span>
                </div>
            </div>
            <input type="hidden" name="totalPrice" id="totalPrice" value="0">
            <input type="hidden" name="sessionTime" value="<%= sessionTime %>">
            <input type="hidden" name="roomName" value="<%= roomname %>">
            <input type="hidden" name="sessionDate" value="<%= sessionDate %>">
            <div id="selectedSeatsContainer"></div> <!-- Container for dynamically added hidden inputs -->
            <button type="submit" class="btn btn-primary">Proceed to Payment</button>
        </form>
    </section>

    <div class="legend">
        <%
            for (String categoryId : seatsByCategory.keySet()) {
                SeatCategory category = seatsByCategory.get(categoryId).get(0).getIdSeatCategory();
                String categoryNameLower = category.getCategoryName().toLowerCase();
        %>
        <div class="<%= categoryNameLower %>"><span></span><%= category.getCategoryName() %></div>
        <%
            }
        %>
        <div class="booked"><span></span>Ghế đã đặt</div>
        <div class="selected"><span></span>Ghế đang chọn</div>
    </div>

    <script src="js/seat.js"></script>
        </body>
    </html>

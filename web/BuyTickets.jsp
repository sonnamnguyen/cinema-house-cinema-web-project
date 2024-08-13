<%@page import="java.sql.Time"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.LinkedHashSet" %>
<%@ page import="DTO.MovieScreeningSession" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinematic</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
                integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/seat.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <form id="sessionForm" action="ChooseSeatSevelet" method="post">
            <div class="container-seat">
                <section class="screening-sessions">
                    <h1>Please choose screening session</h1>
                    <div class="cinema-list">
                        <h3>216 Võ Văn Ngân, Bình Thọ, Thủ Đức, Thành phố Hồ Chí Minh</h3>
                    </div>
                    <h2>Show Dates and Times</h2>
                    <%
                        List<MovieScreeningSession> list = (List<MovieScreeningSession>) session.getAttribute("list");
                        String selectedSessionTime = (String) session.getAttribute("session");
                        String selectedRoomName = (String) session.getAttribute("roomname");

                        if (list != null && !list.isEmpty()) {
                            Set<String> screeningDates = new LinkedHashSet<>();
                            for (MovieScreeningSession session3 : list) {
                                screeningDates.add(session3.getScreeningDate().toString());
                            }

                            for (String date : screeningDates) {
                    %>
                    <div class="date-row">
                        <label><%= date%></label>
                        <div class="time-buttons">
                            <%
                                for (MovieScreeningSession session4 : list) {
                                    if (session4.getScreeningDate().toString().equals(date)) {
                                        Time sessionTimeValue = session4.getStartTime();

                            %>
                            <input type="radio" class="session-time" name="sessionTime" value="<%= session4.getStartTime()%>"
                                   onclick="selectSession('<%= session4.getScreeningDate()%>', '<%= session4.getStartTime()%>', '<%= session4.getIdMovieRoom().getRoomName()%>')" 
                                   <%= (selectedSessionTime != null && selectedSessionTime.equals(sessionTimeValue)) ? "checked" : ""%>>
                            <%= session4.getStartTime()%>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <div class="sessions-container">
                        <p>No sessions available</p>
                    </div>
                    <%
                        }
                    %>
                </section>
            </div>
            <div class="buy-tickets-card hidden">
                <button id="buyTicketsButton" class="see-more-btn bg-dark" type="submit">BUY TICKETS</button>
            </div>
            <input type="hidden" name="sessionId" id="sessionId" value="<%= selectedSessionTime%>">
            <input type="hidden" name="sessionDate" id="sessionDate" value="">
            <input type="hidden" name="sessionTime" id="sessionTime" value="">
            <input type="hidden" name="roomName" id="roomName" value="<%= selectedRoomName%>">
        </form>

        <script>
            function selectSession(date, time, roomName) {
                sessionStorage.setItem('sessionDate', date);
                sessionStorage.setItem('sessionTime', time);
                sessionStorage.setItem('roomName', roomName);

                document.getElementById('sessionDate').value = date;
                document.getElementById('sessionTime').value = time;
                document.getElementById('roomName').value = roomName;

                document.querySelector('.buy-tickets-card').classList.remove('hidden');
            }

            window.onload = function () {
                var savedSessionDate = sessionStorage.getItem('sessionDate');
                var savedSessionTime = sessionStorage.getItem('sessionTime');
                var savedRoomName = sessionStorage.getItem('roomName');
                if (savedSessionDate && savedSessionTime && savedRoomName) {
                    document.getElementById('sessionDate').value = savedSessionDate;
                    document.getElementById('sessionTime').value = savedSessionTime;
                    document.getElementById('roomName').value = savedRoomName;

                    const sessionTimeInputs = document.querySelectorAll('.session-time');
                    sessionTimeInputs.forEach(input => {
                        if (input.value === savedSessionDate + " " + savedSessionTime + "|" + savedRoomName) {
                            input.checked = true;
                            document.querySelector('.buy-tickets-card').classList.remove('hidden');
                        }
                    });
                }
            };

            const sessionForm = document.getElementById('sessionForm');
            const sessionTimeInputs = document.querySelectorAll('.session-time');
            const buyTicketsCard = document.querySelector('.buy-tickets-card');

            sessionTimeInputs.forEach(input => {
                input.addEventListener('click', () => {
                    buyTicketsCard.classList.remove('hidden');
                });
            });

            sessionForm.addEventListener('submit', function () {
                var selectedSessionDate = document.getElementById('sessionDate').value;
                var selectedSessionTime = document.getElementById('sessionTime').value;
                var selectedRoomName = document.getElementById('roomName').value;
                sessionStorage.setItem('sessionDate', selectedSessionDate);
                sessionStorage.setItem('sessionTime', selectedSessionTime);
                sessionStorage.setItem('roomName', selectedRoomName);
            });
        </script>
    </body>
</html>

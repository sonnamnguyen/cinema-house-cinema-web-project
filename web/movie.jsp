<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Date"%>
<%@page import="DTO.Ticket"%>
<%@page import="DAO.TicketsDAO"%>
<%@page import="DTO.Bill"%>
<%@page import="DAO.BillDAO"%>
<%@page import="DTO.MovieScreeningSession"%>
<%@page import="DAO.ScreeningSessionDAO"%>
<%@page import="DAO.MovieDAO"%>
<%@page import="DTO.User"%>
<%@page import="DTO.Comment"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CommentDAO"%>
<%@page import="DTO.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/newcss.css">
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/movie.css">
        <style>
            /* Additional CSS styles */
            .modal {
                display: none;
                position: fixed;
                z-index: 1050;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4); /* Background mờ */
            }

            .modal-dialog {
                margin: auto;
            }

            .modal-content {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            }

            .modal-header {
                border-bottom: none;
                color: #000; /* Màu chữ của header */
            }

            .modal-title {
                margin-bottom: 0;
            }

            .modal-body {
                padding: 15px;
                color: #000; /* Màu chữ của body */
            }

            .modal-footer {
                border-top: none;
                text-align: right;
            }

            .btn-close {
                background: transparent;
                border: none;
                cursor: pointer;
                color: #000;
                font-size: 1.5rem;
            }

            .btn-secondary,
            .btn-primary {
                border-radius: 5px;
            }

            .btn-secondary {
                background-color: #ccc;
                color: #000;
            }

            .btn-primary {
                background-color: #007bff;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <%
            Movie movie = (Movie) request.getAttribute("movie");
            if (movie != null) {
                String movieId = movie.getIdMovie();
        %>
        <div class="container movie">
            <img src="<%= movie.getCoverImage()%>" alt="<%= movie.getMovieName()%>" class="poster">
            <div class="content">
                <h1><%= movie.getMovieName()%></h1>
                <div class="metadata">
                    <span><strong>Duration:</strong> <%= movie.getDuration()%> minutes</span>
                    <span><strong>Language:</strong> <%= movie.getLanguage()%></span>
                    <span><strong>Director:</strong> <%= movie.getDirector()%></span>
                    <span><strong>Cast:</strong> <%= movie.getActor()%></span>
                    <span><strong>Age Limit:</strong> <%= movie.getAgeLimit()%></span>
                    <span><strong>Release Date:</strong> <%= movie.getEffectiveDateFrom()%></span>
                </div>
                <div class="synopsis">
                    <p><%= movie.getSummary()%></p>
                </div>
                <div class="button-container">
                    <% if (movie.isStatus() && session.getAttribute("user") != null) {%>
                    <form action="BuyTicketServlet" method="get">
                        <input type="hidden" name="movieId" value="<%=movieId%>"/>
                        <button type="submit" class="buy-button">Buy Tickets</button>
                    </form>
                    <% } else if (movie.isStatus() && session.getAttribute("user") == null) {%>
                    <button type="button" class="buy-button" onclick="openLoginModal()">Buy Tickets</button>
                    <% } %>
                    <a href="<%= movie.getTrailer()%>" class="trailer-button" target="_blank">Watch Trailer</a> 
                </div>
            </div>
        </div>

        <% if (session.getAttribute("user") != null) {%>
        <section class="user-feedbacks">
            <form action="FixCommentServlet" method="POST">
                <div class="comment-box">
                    <h2>Comment</h2>
                    <textarea name="content" placeholder="Enter your comment here..." required></textarea>
                    <div class="rating">
                        <span>Rating:</span>
                        <span class="stars">
                            <input type="radio" name="rating" id="star1" value="5"><label for="star1">★</label>
                            <input type="radio" name="rating" id="star2" value="4"><label for="star2">★</label>
                            <input type="radio" name="rating" id="star3" value="3"><label for="star3">★</label>
                            <input type="radio" name="rating" id="star4" value="2"><label for="star4">★</label>
                            <input type="radio" name="rating" id="star5" value="1" checked><label for="star5">★</label>
                        </span>
                    </div>
                    <input type="hidden" name="movieId" value="<%=movieId%>">
                    <input type="hidden" name="btAction" value="comment">
                    <input type="hidden" name="commentDate" id="commentDate">
                    <button type="submit" class="submit-btn">Post Comment</button>
                </div>
            </form>
            <div class="user-comment">
                <div class="feedback-container">
                    <%
                        CommentDAO commentDAO = new CommentDAO();
                        List<Comment> comments = commentDAO.getAllComments(movieId);
                        if (comments != null && !comments.isEmpty()) {
                            for (Comment comment : comments) {
                                if (comment.isStatus()) {
                                    User user = comment.getIdAccount();
                    %>
                    <div class="feedback-card">
                        <div class="feedback-header">
                            <img src="<%= user.getAvatar()%>" alt="User Photo" onerror="this.src='img/default-avatar.png'" class="avatar-comment">
                            <div class="user-info">
                                <h3><%= user.getAccountName()%></h3>
                                <div class="rating">
                                    <span class="stars">
                                        <% for (int i = 0; i < comment.getRating(); i++) { %>
                                        <font color="yellow">★</font>
                                        <% } %>
                                        <% for (int i = comment.getRating(); i < 5; i++) { %>
                                        ☆
                                        <% }%>
                                    </span>
                                    <p>"<%= comment.getContent()%>"</p>
                                    <p><font color="blue"><%= comment.getDateCreate()%></font></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                                }
                            }
                        }
                    %>
                </div>
            </div>
        </section>
        <% } %>
        <%
        } else {
        %>
        <h1>No Release</h1>
        <%
            }
        %>
        <jsp:include page="footer.jsp" />

        <!-- Modal -->
        <div id="loginAlertModal" class="modal">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" style="color: red; font-size: 20px; font-weight: 900px;">Notification</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>You need to log in to buy tickets. Click "Confirm" to proceed.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button id="confirmBtn" type="button" class="btn btn-primary">Confirm</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function openLoginModal() {
                var modal = document.getElementById('loginAlertModal');
                modal.style.display = 'block';
            }

            var closeModalBtns = document.querySelectorAll('.btn-close, .btn-secondary, .modal');
            closeModalBtns.forEach(function(btn) {
                btn.addEventListener('click', function() {
                    var modal = document.getElementById('loginAlertModal');
                    modal.style.display = 'none';
                });
            });

            document.getElementById('confirmBtn').addEventListener('click', function() {
                window.location.href = 'login.jsp';
            });

            window.onclick = function(event) {
                var modal = document.getElementById('loginAlertModal');
                if (event.target == modal) {
                    modal.style.display = 'none';
                }
            };
        </script>

    </body>
</html>

<%@page import="DTO.User"%>
<%@page import="DAO.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.MovieCategoryDAO"%>
<%@page import="DTO.MovieCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <style>
        .login {
            margin-left: 5px;
            display: inline-block;
            color: yellow;
            background-color: black;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            transition: all 0.3s ease;
            border: 2px solid yellow; /* Viền vàng nhỏ bên trong */
            border-radius: 25px; /* Bo tròn góc */
        }

        .login:hover {
            color: black; /* Thay đổi màu chữ khi di chuột */
            background-color: yellow; /* Thay đổi màu nền khi di chuột */
            border-color: #FFA500; /* Viền màu cam nhẹ khi di chuột */
        }
    </style>
    <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();

        // Check for cookies
        Cookie[] cookies = request.getCookies();
        String tenDangNhap = "";
        String matKhau = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("UserName")) {
                    tenDangNhap = cookie.getValue();
                }
                if (cookie.getName().equals("Password")) {
                    matKhau = cookie.getValue();
                }
            }
        }

        if (!tenDangNhap.isEmpty() && !matKhau.isEmpty()) {
            User kh = new User();
            kh.setAccountName(tenDangNhap);
            kh.setPassword(matKhau);

            UserDAO khd = new UserDAO();
            User khachHang = khd.selectByUsernameAndPassWord(kh, true);
            if (khachHang != null) {
                session.setAttribute("user", khachHang);
            } else {
                khachHang = khd.selectByUsernameAndPassWord(kh, false);
                if (khachHang != null && khachHang.isAuthentication()) {
                    session.setAttribute("user", khachHang);
                }
            }
        }

        MovieCategoryDAO dao = new MovieCategoryDAO();
        List<MovieCategory> movie = dao.selectAllCategory();
        if (movie != null && !movie.isEmpty()) {
            request.setAttribute("LIST_ALL_MOVIES_CINEMA", movie);
        }
        List<MovieCategory> list = (List<MovieCategory>) request.getAttribute("LIST_ALL_MOVIES_CINEMA");
    %>

    <!-- Navbar -->
    <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="img/bintang cinema.png" alt="Cinema Logo" height="80">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse menu" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link active text-light navbar-choice"  aria-current="page" href="showtime.jsp">Showtimes</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-light navbar-choice" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Category</a>
                        <ul class="dropdown-menu bg-dark">
                            <% if (list != null && !list.isEmpty()) {
                                for (MovieCategory movie1 : list) {%>
                            <li>
                                <form id="form_<%=movie1.getCategoryName()%>" action="CategorySevelet" method="GET" style="display:inline;">
                                    <input type="hidden" name="movieCategory" value="<%=movie1.getCategoryName()%>"/>
                                </form>
                                <a class="dropdown-item text-light" href="#" onclick="document.getElementById('form_<%=movie1.getCategoryName()%>').submit(); return false;">
                                    <%=movie1.getCategoryName()%>
                                </a>
                            </li>
                            <% }
                            } %>
                        </ul>
                    </li>

                    <li class="nav-item"><a class="nav-link active text-light navbar-choice" aria-current="page" href="aboutus.jsp">About Us</a></li>

                </ul>
                <form class="d-flex" role="search" action="UserController" method="GET">
                    <input type="hidden" name="btAction" value="search"/>
                    <div class="search-container">
                        <input type="text" class="form-control me-2" id="searchInput" name="Search" placeholder="Find movies" aria-label="Search">
                            <button class="btn btn-outline-light handlesearch" type="submit" aria-label="Search button">
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="16" height="16">
                                    <path d="M10 2a8 8 0 105.293 14.707l4.829 4.828a1 1 0 001.414-1.414l-4.828-4.829A8 8 0 0010 2zm0 2a6 6 0 110 12 6 6 0 010-12z"/>
                                </svg>
                            </button>
                    </div>
                </form>
                <%
                    User user = (User) session.getAttribute("user");
                    String userName = "";
                    String avatar = "img/default-avatar.png"; // Default avatar path

                    if (user != null) {
                        userName = user.getAccountName();
                        if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
                            avatar = user.getAvatar();
                        }
                    }
                    if (user == null) {
                %>
                <div class="navbar-nav">
                    <a class="login" href="login.jsp">LOGIN</a>
                </div>
                <% } else {
                    if (!user.isIsAdmin()) {
                %>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor user">
                    <li class="nav-item dropdown dropstart">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="<%= avatar%>" alt="User Avatar" class="avatar">
                                <%= userName%>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="history.jsp">Transaction History</a></li>
                            <li><a class="dropdown-item" href="#">Notifications</a></li>
                            <li><a class="dropdown-item" href="changeinformation.jsp">Change Information</a></li>
                            <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<%=url%>/UserController?btAction=logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
                <% } else {%>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor user">
                    <li class="nav-item dropdown dropstart">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="<%= avatar%>" alt="User Avatar" class="avatar">
                                <%= userName%>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="admin.jsp">Management</a></li>
                            <li><a class="dropdown-item" href="history.jsp">Transaction History</a></li>
                            <li><a class="dropdown-item" href="changeinformation.jsp">Change Information</a></li>
                            <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<%=url%>/UserController?btAction=logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
                <% }
                }%>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->

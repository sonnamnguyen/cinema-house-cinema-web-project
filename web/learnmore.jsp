<%-- 
    Document   : learnmore
    Created on : 24-Jun-2024, 11:49:46
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cinematic</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/vip.css">
    </head>
    <body>
         <jsp:include page="header.jsp" />
         <div class="container mt-5 pt-5">
            <div class="row mb-5">
                <div class="col-md-12">
                    <div class="card text-white mb-5">
                        <div class="row g-0">
                            <div class="col-md-6">
                                <img src="img/Black And Mustard Modern Member Id Card (1).png" class="img-fluid rounded-start" alt="PREMIUM">
                            </div>
                            <div class="col-md-6">
                                <div class="card-body">
                                    <h5 class="card-title">PREMIUM</h5>
                                    <ul>
                                        <li>Issued for the first time when purchasing any 2 movie tickets at CineHouse.</li>
                                        <li>Accumulate points based on the purchase value of goods and services as follows: Get a 10% discount directly on the bill value of popcorn when purchased at the counter.</li>
                                        <li>Get a 10% discount directly on the bill value of popcorn when purchased at the counter.</li>
                                        <li>Receive 1 2D movie ticket on your birthday week (from Monday to Sunday) with a maximum cumulative score of 500 points.</li>
                                        <li>Participate in member programs.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card text-white">
                        <div class="row g-0">
                            <div class="col-md-6">
                                <div class="card-body">
                                    <h5 class="card-title">VIP</h5>
                                    <ul>
                                        <li>Issued to C'Friend members when they accumulate at least 10,000 points.</li>
                                        <li>Accumulate points based on the purchase value of goods and services as follows: Get a 15% discount directly on the bill value of popcorn when purchased at the counter.</li>
                                        <li>Get 15% discount directly on the bill value of popcorn when purchased at the counter.</li>
                                        <li>Have the opportunity to receive tickets to attend the Movie Launch Ceremony and other CineHouse promotions.</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <img src="img/z5579798578074_2b535e1eac749a62291e0e348c4eddac.jpg" class="img-fluid rounded-start" alt="VIP">
                            </div>                           
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <jsp:include page="footer.jsp" />
    </body>
</html>

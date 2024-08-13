<%-- 
    Document   : createsession
    Created on : Jun 26, 2024, 9:26:42 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREATE SESSION</title>
        <link href="https://cdn.lineicons.com/4.0/lineicons.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous"/>
        <link rel="stylesheet" href="leftbar.css">
        <link rel="stylesheet" href="movietable.css">
    </head>
    <body>
        <%
            String errorMessage = request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage").toString() : "";
            String idMovie = request.getAttribute("idMovie") != null ? request.getAttribute("idMovie").toString() : "";
            String idCinemaRoom = request.getAttribute("idCinemaRoom") != null ? request.getAttribute("idCinemaRoom").toString() : "";
            String screeningDate = request.getAttribute("screeningDate") != null ? request.getAttribute("screeningDate").toString() : "";
            String startTime = request.getAttribute("startTime") != null ? request.getAttribute("startTime").toString() : "";
            String endTime = request.getAttribute("endTime") != null ? request.getAttribute("endTime").toString() : "";
        %>
        <div class="wrapper">     
            <jsp:include page="leftbar.jsp" />
            <div class="main p-3">
                <div class="container">
                    <h1>Create New Screening Session</h1>
                    <div class="alert alert-danger" role="alert" id="errorMessage">
                        <%= errorMessage %>
                    </div>
                    <form action="CreateSessionSevelet" method="GET">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="idMovie">Movie ID (*)</label>
                                <input type="text" class="form-control" id="idMovie" name="idMovie" required="required" value="<%= idMovie %>">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="idCinemaRoom">Cinema Room ID (*)</label>
                                <input type="text" class="form-control" id="idCinemaRoom" name="idCinemaRoom" required="required" value="<%= idCinemaRoom %>">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="screeningDate">Screening Date (*)</label>
                                <input type="date" class="form-control" id="screeningDate" name="screeningDate" required="required" value="<%= screeningDate %>">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="startTime">Start Time (*)</label>
                                <input type="time" class="form-control" id="startTime" name="startTime" required="required" value="<%= startTime %>">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="endTime">End Time (*)</label>
                                <input type="time" class="form-control" id="endTime" name="endTime" required="required" value="<%= endTime %>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="status">Status</label>
                            <input type="checkbox" class="form-check-input" id="status" name="status">
                        </div>
                        <button type="submit" class="btn btn-primary">Create Session</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
        <script src="admin.js"></script>
    </body>
</html>

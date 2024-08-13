<%-- 
    Document   : updatemovie
    Created on : Jun 22, 2024, 12:10:28 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        errorMessage = (errorMessage == null) ? "" : errorMessage;

        String movieName = (String) request.getAttribute("MovieName");
        movieName = (movieName == null) ? "" : movieName;

        String director = (String) request.getAttribute("Director");
        director = (director == null) ? "" : director;

        String duration = (String) request.getAttribute("duration");
        duration = (duration == null) ? "" : duration;

        String effectiveDateFrom = (String) request.getAttribute("EffectiveDateFrom");
        effectiveDateFrom = (effectiveDateFrom == null) ? "" : effectiveDateFrom;

        String effectiveDateTo = (String) request.getAttribute("EffectiveDateTo");
        effectiveDateTo = (effectiveDateTo == null) ? "" : effectiveDateTo;

        String status = (String) request.getAttribute("Status");
        status = (status == null) ? "" : status;
    %>
    <div class="wrapper">     
        <jsp:include page="leftbar.jsp" />
        <div class="main p-3">
            <div class="container">
                <h1>Update Movie</h1>
                <div class="red" id="errorMessage">
                    <%= errorMessage %>
                </div>
                <form action="UpdateMovieSevelet" method="POST">
                    <%
                        String movieId = request.getParameter("movieID");
                       
                        %>
                        <input type="hidden" name="movieID" value="<%=movieId%>" />
                    <div class="form-row">
                        <div class="form-group">
                            <label for="movieName">Movie Name (*)</label>
                            <input type="text" id="movieName" name="MovieName" required="required" value="<%= movieName %>">
                        </div>
                        <div class="form-group">
                            <label for="director">Director (*)</label>
                            <input type="text" id="director" name="Director" required="required" value="<%= director %>">
                        </div>
                        <div class="form-group">
                            <label for="duration">Duration (*)</label>
                            <input type="text" id="duration" name="duration" required="required" value="<%= duration %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="effectiveDateFrom">Effective Date From (*)</label>
                            <input type="date" id="effectiveDateFrom" name="EffectiveDateFrom" required="required" value="<%= effectiveDateFrom %>">
                        </div>
                        <div class="form-group">
                            <label for="effectiveDateTo">Effective Date To (*)</label>
                            <input type="date" id="effectiveDateTo" name="EffectiveDateTo" required="required" value="<%= effectiveDateTo %>">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="status">Status (*)</label>
                            <input type="checkbox" id="status" name="Status"  value="<%= status %>">
                        </div>
                    </div>
                    <input class="btn btn-primary form-control" type="submit" value="Update" name="submit" id="submit" />
                </form>
            </div>
        </div>
    </div>
</body>
</html>

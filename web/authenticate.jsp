<%-- 
    Document   : authenticate
    Created on : Jul 8, 2024, 1:04:43 PM
    Author     : admin
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authenticate</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
            crossorigin="anonymous">
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            String errorMessage = (String) request.getAttribute("errorMessage");
            errorMessage = (errorMessage == null) ? "" : errorMessage;
        %>
        
        <div class="container mt-5">
            <div class="alert alert-danger" role="alert" id="errorMessage" style="display: <%= errorMessage.isEmpty() ? "none" : "block" %>;">
                <%= errorMessage %>
            </div>
            <form action="CheckCodeServelet" method="POST" class="p-4 border rounded-3 bg-light">
                <h1 class="mb-4">Verification Code</h1>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="Code" name="Code" placeholder="Code" required>
                    <label for="Code">Please enter your verification code from your email</label>
                </div>
                <input type="hidden" name="userID" value="<%= user.getIdAccount()%>"/>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </body>
</html>

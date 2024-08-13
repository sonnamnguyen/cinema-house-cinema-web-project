<%-- 
    Document   : notify
    Created on : Jun 8, 2024, 7:52:20 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notify page</title>
         <style>
        body {
            background-color: #000; /* Nền đen */
            color: #fff; /* Màu chữ trắng */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .modal-content {
            background-color: #fff; /* Màu nền trắng */
            color: #000; /* Màu chữ đen */
            padding: 20px;
            border-radius: 10px;
            text-align: center;
        }
    </style>
    </head>
    <body>
       <div class="container">
		<%=request.getAttribute("error")+"" %>	
	</div>
	<script>
         setTimeout(function(){
            window.location.href = 'index.jsp';
         }, 2000);
      </script>
    </body>
</html>

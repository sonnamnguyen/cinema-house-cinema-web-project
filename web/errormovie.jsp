<%-- 
    Document   : errormovie
    Created on : Jun 16, 2024, 11:27:59 PM
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
       <div class="container">
		<%=request.getAttribute("baoLoi")+"" %>	
	</div>
	<script>
         setTimeout(function(){
            window.location.href = 'index.jsp';
         }, 10000);
      </script>
    </body>
</html>

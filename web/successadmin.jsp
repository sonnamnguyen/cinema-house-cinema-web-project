<%-- 
    Document   : successadmin
    Created on : Jun 24, 2024, 12:33:31 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
    </head>
    <body>
       <div class="container">Successful manipulation! Please return to the login page.</div>
	<script>
         setTimeout(function(){
            window.location.href = 'managementmembers.jsp';
         }, 5000);
      </script>
    </body>
</html>

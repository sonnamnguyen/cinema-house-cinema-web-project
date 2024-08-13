<%-- 
    Document   : changepassword
    Created on : May 29, 2024, 2:18:27 PM
    Author     : admin
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
	integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
	crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/information.css">
</head>
<body >
     <%
		 String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
		 %>
       
	<jsp:include page="header.jsp" />

	<%
		Object obj = session.getAttribute("user");
	User user = null;
		if (obj!=null)
			user = (User)obj;		
			if(user==null){		
	%>
	<h1>You are not logged into the system. Please return to the home page!</h1>
	<%
			}else {
				String errorMessage = request.getAttribute("errorMessage")+"";
				if(errorMessage.equals("null")){
					errorMessage = "";
				}
	%>
	<div class="container mt-5 user_infor">
		<div class="text-center"><h1>CHANGE PASSWORD</h1></div>
		<span style="color: red">
			<%=errorMessage%>
		</span>
		<form action="UserController" method="POST">
			<input type="hidden" name="btAction" value="changePassword"/>
		  <div class="mb-3">
		    <label for="currentPassword" class="form-label">Current Password</label>
		    <input type="password" class="form-control" id="currentPassword" name="currentPassword">
		  </div>
		  <div class="mb-3">
		    <label for="newPassword" class="form-label">New Password</label>
		    <input type="password" class="form-control" id="newPassword" name="newPassword">
		  </div>
		  <div class="mb-3">
		    <label for="newPasswordAgain" class="form-label">New Password Again</label>
		    <input type="password" class="form-control" id="newPasswordAgain" name="newPasswordAgain">
		  </div>
		  <button type="submit" class="btn btn-primary">Save Password</button>
		</form>
	</div>
	<%} %>
	
	<jsp:include page="footer.jsp" />
</body>
</html>

<%-- 
    Document   : changeinformation
    Created on : May 29, 2024, 2:19:10 PM
    Author     : admin
--%>

<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Information</title>
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
        <link rel="stylesheet" href="css/buttoncss.css">
        <link rel="stylesheet" href="css/information.css">
        <style>
            .red {
                color: red;
            }
        </style>
    </head>
    <body>
         
        <jsp:include page="header.jsp" />
       
        <%
            User user = (User) session.getAttribute("user");   
            if (user == null) {
        %>
        <h1>You are not logged into the system. Please return to the home page!</h1>
        <%
        } else {

        %>
        <div class="container">
            <%           
                String errorMessage = request.getAttribute("errorMessage") + "";
                errorMessage = (errorMessage.equals("null")) ? "" : errorMessage;
                                String accountName  = user.getAccountName();
String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
                String firstname = user.getFirstname();
                String lastname = user.getLastname();
             
                String gender = user.getGender();

                String yearOfBirth = user.getYob().toString();

                String address = user.getAddress();

                String phoneNumber = user.getPhoneNumber();

                String email = user.getEmail();

                boolean agreeMails = user.isReceiveEmail();
                
                String linkImage = user.getAvatar();
            %>
            <div class="container-fluid">
                <div class="text-center">
                    <h1>ACCOUNT INFORMATION</h1>
                </div>

                <div class="red" id="errorMessage">
                    <%=errorMessage%>
                </div>
                
                <form class="form" action="UserController" method="POST">
                    <input type="hidden" name="btAction" value="changeInformation"/>
                    <div class="row user_infor">
                        <div class="col-sm-6">

                            <h3>Thông tin khách hàng</h3>
                             <div class="mb-3">
                                <label for="accountName" class="form-label">AccountName</label> <input
                                    type="text" class="form-control" id="accountName" name="accountName" value="<%=accountName%>">
                            </div>
                            <div class="mb-3">
                                <label for="firstname" class="form-label">FirstName</label> <input
                                    type="text" class="form-control" id="firstname" name="firstname" value="<%=firstname%>">
                            </div>
                            <div class="mb-3">
                                <label for="lastname" class="form-label">LastName</label> <input
                                    type="text" class="form-control" id="lastname" name="lastname" value="<%=lastname%>">
                            </div>
                            <div class="mb-3">
                            <label for="linkImage" class="form-label">Avatar</label> <input
                                type="file" class="form-control" id="linkImage" name="linkImage" value="<%=linkImage%>">
                            </div>
                            
                            <div class="mb-3">
                                <label for="gender" class="form-label">Gender</label> <select
                                    class="form-control" id="gender" name="gender">
                                    <option></option>
                                    <option value="Male" <%=(gender.equals("Male")) ? "selected='selected'" : ""%> >Male</option>
                                    <option value="Female" <%=(gender.equals("Female")) ? "selected='selected'" : ""%> >Female</option>
                                    <option value="Others" <%=(gender.equals("Others")) ? "selected='selected'" : ""%> >Others</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="yearOfBirth" class="form-label">Year Of Birth</label> <input
                                    type="date" class="form-control" id="yearOfBirth" name="yearOfBirth" value="<%=yearOfBirth%>">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <h3>Address</h3>
                            <div class="mb-3">
                                <label for="address" class="form-label">Address</label> <input type="text" class="form-control"
                                                                                               id="address" name="address" required="required" value="<%=address%>">


                            </div>

                            <div class="mb-3">
                                <label for="phoneNumber" class="form-label">Phone Number</label> <input
                                    type="tel" class="form-control" id="phoneNumber" name="phoneNumber" required="required" value="<%=phoneNumber%>">


                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label> <input
                                    type="email" class="form-control" id="email" name="email" required="required"  value="<%=email%>">


                            </div>
                            <hr />					
                            <div class="mb-3">
                                <label for="agreeMails" class="form-label">Agree to receive emails</label> <input type="checkbox" class="form-check-input"
                                                                                                                  id="agreeMails" name="agreeMails" <%=(agreeMails ? "checked" : "")%> >
                            </div>
                            <input class="btn btn-primary form-control" type="submit"
                                   value="Save information" name="submit" id="submit" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <%}%>
        <jsp:include page="footer.jsp" />
    </body>

</html>
<%-- 
    Document   : createuser
    Created on : Jun 23, 2024, 9:50:09 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
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
        <style>
            .red {
                color: red;
            }
        </style>
    </head>
    <body>
        <%
            String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();

            String errorMessage = request.getAttribute("errorMessage") + "";
            errorMessage = (errorMessage.equals("null")) ? "" : errorMessage;

            String userName = request.getAttribute("userName") + "";
            userName = (userName.equals("null")) ? "" : userName;

            String firstname = request.getAttribute("firstname") + "";
            firstname = (firstname.equals("null")) ? "" : firstname;

            String lastname = request.getAttribute("lastname") + "";
            lastname = (lastname.equals("null")) ? "" : lastname;

            String linkImage = request.getAttribute("linkImage") + "";
            linkImage = (linkImage.equals("null")) ? "" : linkImage;

            String gender = request.getAttribute("gender") + "";
            gender = (gender.equals("null")) ? "" : gender;

            String yearOfBirth = request.getAttribute("yearOfBirth") + "";
            yearOfBirth = (yearOfBirth.equals("null")) ? "" : yearOfBirth;

            String address = request.getAttribute("address") + "";
            address = (address.equals("null")) ? "" : address;

            String phoneNumber = request.getAttribute("phoneNumber") + "";
            phoneNumber = (phoneNumber.equals("null")) ? "" : phoneNumber;

            String email = request.getAttribute("email") + "";
            email = (email.equals("null")) ? "" : email;

            String dongYNhanMail = request.getAttribute("dongYNhanMail") + "";
            dongYNhanMail = (dongYNhanMail.equals("null")) ? "" : dongYNhanMail;

            String e_userName = request.getAttribute("e_userName") + "";
            e_userName = (e_userName.equals("null")) ? "" : e_userName;

            String e_firstname = request.getAttribute("e_firstname") + "";
            e_firstname = (e_firstname.equals("null")) ? "" : e_firstname;

            String e_lastname = request.getAttribute("e_lastname") + "";
            e_lastname = (e_lastname.equals("null")) ? "" : e_lastname;

            String e_address = request.getAttribute("e_address") + "";
            e_address = (e_address.equals("null")) ? "" : e_address;

            String e_phoneNumber = request.getAttribute("e_phoneNumber") + "";
            e_phoneNumber = (e_phoneNumber.equals("null")) ? "" : e_phoneNumber;

            String e_email = request.getAttribute("e_email") + "";
            e_email = (e_email.equals("null")) ? "" : e_email;

        %>
  <div class="container">
            <div class="text-center">
                <h1>CREATE USER</h1>
            </div>    

            <div class="red" id="errorMessage">
                <%=errorMessage%>
            </div>
            <form class="form" action="CreateUserServlet" method="post">
                  <input type="hidden" name="btAction" value="CreateUser" />
                <div class="row">
                    <h3>Account</h3>
                    <div class="col-sm-6">
                        <div class="mb-3">
                            <label for="userName" class="form-label">UserName<span class="red">*</span></label> 
                            <input type="text" class="form-control" id="userName" name="userName" required value="<%=userName%>">
                            <div class="rq"><%=e_userName%></div>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password<span class="red">*</span></label> 
                            <input type="password" class="form-control" id="password" name="password" required onkeyup="checkPassword()">
                        </div>
                        <div class="mb-3">
                            <label for="passwordAgain" class="form-label">Password Again<span class="red">*</span> <span id="msg" class="red"></span></label> 
                            <input type="password" class="form-control" id="passwordAgain" name="passwordAgain" required onkeyup="checkPassword()">
                        </div>
                        <hr />
                        <h3>Information User</h3>
                        <div class="mb-3">
                            <label for="firstname" class="form-label">Firstname</label> 
                            <input type="text" class="form-control" id="firstname" name="firstname" required value="<%=firstname%>">
                            <div class="rq"><%=e_firstname%></div>
                        </div>
                        <div class="mb-3">
                            <label for="lastname" class="form-label">Lastname</label> 
                            <input type="text" class="form-control" id="lastname" name="lastname" required value="<%=lastname%>">
                            <div class="rq"><%=e_lastname%></div>
                        </div>
                        <div class="mb-3">
                            <label for="linkImage" class="form-label">Image</label> 
                            <input type="file" class="form-control" id="linkImage" name="linkImage" value="<%=linkImage%>">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender</label> 
                            <select class="form-control" id="gender" name="gender">
                                <option></option>
                                <option value="Male" <%=(gender.equals("Male")) ? "selected" : ""%>>Male</option>
                                <option value="Female" <%=(gender.equals("Female")) ? "selected" : ""%>>Female</option>
                                <option value="Others" <%=(gender.equals("Others")) ? "selected" : ""%>>Others</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="yearOfBirth" class="form-label">Year Of Birth</label> 
                            <input type="date" class="form-control" id="yearOfBirth" name="yearOfBirth" value="<%=yearOfBirth%>">
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label> 
                            <input type="text" class="form-control" id="address" name="address" required value="<%=address%>">
                            <div class="rq"><%=e_address%></div>
                        </div>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone Number</label> 
                            <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" required value="<%=phoneNumber%>">
                            <div class="rq"><%=e_phoneNumber%></div>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label> 
                            <input type="email" class="form-control" id="email" name="email" required value="<%=email%>">
                            <div class="rq"><%=e_email%></div>
                        </div>
                        <hr />
                        <div class="mb-3">
                            <label for="agreeMails" class="form-label">Agree to receive emails</label> 
                            <input type="checkbox" class="form-check-input" id="agreeMails" name="agreeMails">
                        </div>
                        <hr />
                        <div class="mb-3">
                            <label for="agreeTerms" class="form-label">Agree to <a href="#">the cinema's terms</a><span class="red">*</span></label> 
                            <input type="checkbox" class="form-check-input" id="agreeTerms" name="agreeTerms" required onchange="agreeBox()">
                        </div>
                        <input class="btn btn-primary form-control" type="submit" value="Register" name="submit" id="submit" style="visibility: hidden;">
                    </div>
                </div>
            </form>
        </div>

        <script>
             function checkPassword() {
                password = document.getElementById("password").value;
                passwordAgain = document.getElementById("passwordAgain").value;
                if (password != passwordAgain) {
                    document.getElementById("msg").innerHTML = "Password Incorrect!";
                    return false;
                } else {
                    document.getElementById("msg").innerHTML = "";
                    return true;
                }
            }

              function agreeBox() {
                var agreeTerms = document.getElementById("agreeTerms");

                if (agreeTerms.checked == true) {
                    document.getElementById("submit").style.visibility = "visible";
                } else {
                    document.getElementById("submit").style.visibility = "hidden";
                }


            }
        </script>



    </body>
</html>

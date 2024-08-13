<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/buttoncss.css">
    <link rel="stylesheet" href="css/information.css">
    <style>
        body {
            background-color: #000; /* Nền đen */
            color: #fff; /* Màu chữ trắng */
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding-top: 20px;
            margin: 0;
        }
        .form-container {
            background-color: #1c1c1c; /* Màu nền form */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.1); /* Đổ bóng nhẹ */
            width: 100%;
            max-width: 600px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .required {
            color: red; /* Màu đỏ cho dấu '*' */
        }
        .btn-primary {
            background-color: #ff9800;
            border: none;
        }
        .btn-primary:hover {
            background-color: #e68900;
        }
        .form-control {
            background-color: #333;
            color: #fff;
            border: 1px solid #555;
        }
        .form-control:focus {
            background-color: #444;
            color: #fff;
            border: 1px solid #777;
        }
        h1, h3 {
            color: #ff9800;
        }
        .text-center {
            text-align: center;
        }
        .form-label a {
            color: #ff9800;
            text-decoration: none;
        }
        .form-label a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            display: none;
        }
    </style>
</head>
<body>

    <%
        String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();

        String errorMessage = (String) request.getAttribute("errorMessage");
        errorMessage = (errorMessage == null) ? "" : errorMessage;

        String userNameAttr = (String) request.getAttribute("userName");
        userNameAttr = (userNameAttr == null) ? "" : userNameAttr;

        String firstname = (String) request.getAttribute("firstname");
        firstname = (firstname == null) ? "" : firstname;

        String lastname = (String) request.getAttribute("lastname");
        lastname = (lastname == null) ? "" : lastname;

        String yearOfBirth = (String) request.getAttribute("yearOfBirth");
        yearOfBirth = (yearOfBirth == null) ? "" : yearOfBirth;

        String address = (String) request.getAttribute("address");
        address = (address == null) ? "" : address;

        String phoneNumber = (String) request.getAttribute("phoneNumber");
        phoneNumber = (phoneNumber == null) ? "" : phoneNumber;

        String email = (String) request.getAttribute("email");
        email = (email == null) ? "" : email;

        String dongYNhanMail = (String) request.getAttribute("dongYNhanMail");
        dongYNhanMail = (dongYNhanMail == null) ? "" : dongYNhanMail;

        String e_userName = (String) request.getAttribute("e_userName");
        e_userName = (e_userName == null) ? "" : e_userName;

        String e_firstname = (String) request.getAttribute("e_firstname");
        e_firstname = (e_firstname == null) ? "" : e_firstname;

        String e_lastname = (String) request.getAttribute("e_lastname");
        e_lastname = (e_lastname == null) ? "" : e_lastname;

        String e_address = (String) request.getAttribute("e_address");
        e_address = (e_address == null) ? "" : e_address;

        String e_phoneNumber = (String) request.getAttribute("e_phoneNumber");
        e_phoneNumber = (e_phoneNumber == null) ? "" : e_phoneNumber;

        String e_email = (String) request.getAttribute("e_email");
        e_email = (e_email == null) ? "" : e_email;
    %>

    <div class="form-container">
        <div class="text-center regiser">
            <h1>REGISTER</h1>
        </div>    

        <div class="red" id="errorMessage">
            <%=errorMessage%>
        </div>
        <form class="form" action="UserController" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="btAction" value="register"/>
            <div class="row">
                <div class="col-sm-12">
                    <h3>Account</h3>
                    <div class="mb-3">
                        <label for="userName" class="form-label">Username<span class="required">*</span></label> 
                        <input type="text" class="form-control" id="userName" name="userName" required="required" value="<%=userNameAttr%>">
                        <div class="rq"><%=e_userName%></div>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password<span class="required">*</span></label> 
                        <input type="password" class="form-control" id="password" name="password" required="required" onkeyup="checkPassword()">
                    </div>
                    <div class="mb-3">
                        <label for="passwordAgain" class="form-label">Password Again<span class="required">*</span> <span id="msg" class="required"></span></label> 
                        <input type="password" class="form-control" id="passwordAgain" name="passwordAgain" required="required" onkeyup="checkPassword()">
                    </div>
                    <hr />
                    <h3>Information User</h3>
                    <div class="mb-3">
                        <label for="firstname" class="form-label">Firstname<span class="required">*</span></label> 
                        <input type="text" class="form-control" id="firstname" name="firstname" required="required" value="<%=firstname%>">
                        <div class="rq"><%=e_firstname%></div>
                    </div>
                    <div class="mb-3">
                        <label for="lastname" class="form-label">Lastname<span class="required">*</span></label> 
                        <input type="text" class="form-control" id="lastname" name="lastname" required="required" value="<%=lastname%>">
                        <div class="rq"><%=e_lastname%></div>
                    </div>
                    <div class="mb-3">
                        <label for="yearOfBirth" class="form-label">Year Of Birth</label> 
                        <input type="date" class="form-control" id="yearOfBirth" name="yearOfBirth" value="<%=yearOfBirth%>">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address<span class="required">*</span></label> 
                        <input type="text" class="form-control" id="address" name="address" required="required" value="<%=address%>">
                        <div class="rq"><%=e_address%></div>
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone Number<span class="required">*</span></label> 
                        <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" required="required" pattern="^0[0-9]{9}$" title="Phone number must be 10 digits starting with 0" value="<%=phoneNumber%>">
                        <div class="error-message" id="phoneError">Phone number must be 10 digits starting with 0.</div>
                        <div class="rq"><%=e_phoneNumber%></div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email<span class="required">*</span></label> 
                        <input type="email" class="form-control" id="email" name="email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Email must contain @" value="<%=email%>">
                        <div class="error-message" id="emailError">Invalid email format. Please include '@'.</div>
                        <div class="rq"><%=e_email%></div>
                    </div>
                    <hr />
                    <div class="mb-3">
                        <label for="agreeTerms" class="form-label">Agree to <a href="#">the cinema's terms </a><span class="required">*</span></label> 
                        <input type="checkbox" class="form-check-input" id="agreeTerms" name="agreeTerms" required="required" onchange="agreeBox()">
                    </div>
                    <div class="mb-3">
                        <label for="agreeMails" class="form-label">Agree to receive emails</label> 
                        <input type="checkbox" class="form-check-input" id="agreeMails" name="agreeMails">
                    </div>
                    <input class="btn btn-primary form-control" type="submit" value="Register" name="submit" id="submit" style="visibility: hidden;" />
                </div>
            </div>
        </form>
    </div>

    <script>
        function checkPassword() {
            var password = document.getElementById("password").value;
            var passwordAgain = document.getElementById("passwordAgain").value;
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
            if (agreeTerms.checked) {
                document.getElementById("submit").style.visibility = "visible";
            } else {
                document.getElementById("submit").style.visibility = "hidden";
            }
        }

        function validateForm() {
            var email = document.getElementById("email").value;
            var phoneNumber = document.getElementById("phoneNumber").value;
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            var phonePattern = /^0[0-9]{9}$/;

            var emailError = document.getElementById("emailError");
            var phoneError = document.getElementById("phoneError");

            var valid = true;

            if (!emailPattern.test(email)) {
                emailError.style.display = "block";
                valid = false;
            } else {
                emailError.style.display = "none";
            }

            if (!phonePattern.test(phoneNumber)) {
                phoneError.style.display = "block";
                valid = false;
            } else {
                phoneError.style.display = "none";
            }

            return valid;
        }

        document.getElementById("email").addEventListener("input", function () {
            validateForm();
        });

        document.getElementById("phoneNumber").addEventListener("input", function () {
            validateForm();
        });
    </script>
</body>
</html>

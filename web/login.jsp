<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log in</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
    <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet">
</head>
<body>
    <main class="form-signin">
        <form class="text-center" action="UserController" method="POST">
            <input type="hidden" name="btAction" value="login"/>

            <img class="mb-4" src="<%=request.getContextPath()%>/img/logo/logo.png" alt="" width="72">
            <h1 class="h3 mb-3 fw-normal">LOGIN</h1>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage == null) {
                    errorMessage = "";
                }
            %>
            <div class="text-center"><span class="red"><%=errorMessage%></span></div>
            <div class="form-floating">
                <input type="text" class="form-control" id="UserName" placeholder="UserName" name="UserName">
                <label for="UserName">UserName</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="Password" placeholder="Password" name="Password">
                <label for="Password">Password</label>
            </div>

            <div class="checkbox mb-3">
                <label><input type="checkbox" name="rememberMe" value="true"> Remember this account</label>
            </div>

            <div class="checkbox mb-3">
                <a href="forgotpassword.jsp" class="text-body forgot-password-link">Forgot password?</a>
            </div>

            <button class="w-100 btn btn-lg btn-primary" type="submit">Login</button>
            <a href="register.jsp" class="d-block mt-3">Register a new account</a>
            <p class="mt-5 mb-3 text-muted">&copy; 2024-2025</p>
        </form>
    </main>
</body>
</html>

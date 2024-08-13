<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Forgot Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #000; /* Nền đen */
            color: #fff; /* Màu chữ trắng */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .container {
            background-color: #1c1c1c; /* Màu nền form */
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Đổ bóng nhẹ */
            width: 100%;
            max-width: 400px;
            text-align: center;
        }
        .form-floating {
            margin-bottom: 20px;
            position: relative;
        }
        .form-control {
            background-color: #444; /* Màu nền input */
            color: #ffcc00; /* Màu chữ input */
            border: 1px solid #555; /* Màu viền input */
            border-radius: 5px;
            padding: 10px;
            text-align: center; /* Chữ ở giữa */
        }
        .form-control::placeholder {
            color: #bbb; /* Màu chữ placeholder */
        }
        .form-control:focus {
            border-color: #ffcc00; /* Màu viền khi focus */
            box-shadow: 0 0 5px rgba(255, 204, 0, 0.5); /* Đổ bóng khi focus */
            outline: none;
            background-color: #333; /* Đổi nền thành đen nhạt khi focus */
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #ffcc00; /* Màu chữ cho label */
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #ffcc00;
        }
        .btn-submit {
            background-color: #ff5722; /* Màu cam cho nút */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }
        .btn-submit:hover {
            background-color: #ff7043; /* Màu cam nhạt khi hover */
        }
        .red {
            color: red;
        }
    </style>
</head>
<body>
    <%
        String errorMessage = request.getAttribute("errorMessage") + "";
        errorMessage = (errorMessage.equals("null")) ? "" : errorMessage;
    %>
    <div class="container">
        <div class="red" id="errorMessage">
            <%=errorMessage%>
        </div>
        <form action="ForgotPasswordServelet" method="POST">
            <h1>Forgot Password</h1>
            <div class="form-floating">
                <input type="email" class="form-control" id="Email" name="Email" placeholder="Please enter your email" required>
                <label for="Email">Please enter your email</label>
            </div>
            <button type="submit" class="btn-submit">Submit</button>
        </form>
    </div>
</body>
</html>

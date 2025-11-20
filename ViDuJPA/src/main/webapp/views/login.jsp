<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>

<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #74ABE2, #5563DE);
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0;
    }

    form {
        background-color: #fff;
        border-radius: 15px;
        box-shadow: 0 4px 25px rgba(0, 0, 0, 0.2);
        padding: 40px 50px;
        width: 350px;
        animation: fadeIn 0.6s ease-in-out;
    }

    @keyframes fadeIn {
        from {opacity: 0; transform: translateY(-20px);}
        to {opacity: 1; transform: translateY(0);}
    }

    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 25px;
    }

    label {
        font-weight: bold;
        color: #333;
        display: block;
        margin-top: 10px;
        margin-bottom: 5px;
    }

    input[type=text], input[type=password] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-sizing: border-box;
        font-size: 14px;
        transition: all 0.3s ease;
    }

    input[type=text]:focus, input[type=password]:focus {
        border-color: #5563DE;
        box-shadow: 0 0 5px rgba(85, 99, 222, 0.5);
        outline: none;
    }

    button[type=submit], .cancelbtn {
        background-color: #5563DE;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        padding: 10px;
        width: 100%;
        font-size: 15px;
        margin-top: 15px;
        transition: background 0.3s ease;
    }

    .cancelbtn {
        background-color: #aaa;
    }

    button:hover {
        background-color: #3c4bc5;
    }

    .cancelbtn:hover {
        background-color: #888;
    }

    .login-link {
        text-align: center;
        margin-top: 15px;
        font-size: 14px;
    }

    .login-link a {
        color: #5563DE;
        text-decoration: none;
        font-weight: bold;
    }

    .login-link a:hover {
        text-decoration: underline;
    }

    .remember-section {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-top: 10px;
        font-size: 14px;
    }

    .remember-section a {
        color: #5563DE;
        text-decoration: none;
    }

    .remember-section a:hover {
        text-decoration: underline;
    }

    .alert {
        text-align: center;
        color: #fff;
        background-color: #e74c3c;
        border-radius: 8px;
        padding: 10px;
        margin-bottom: 15px;
    }

</style>
</head>
<body>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <h2>🔐 Login</h2>

        <c:if test="${alert != null}">
            <div class="alert">${alert}</div>
        </c:if>

        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" 
               name="uname" 
               value="${rememberedUser}"
               required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <div class="remember-section">
            <label>
                <input type="checkbox" 
                       name="remember"
                       <c:if test="${rememberedUser != null}">checked</c:if>
                > Remember me
            </label>
            <span class="psw">Forgot <a href="${pageContext.request.contextPath}/forget">password?</a></span>
        </div>

        <button type="submit">Login</button>
        <button type="button" class="cancelbtn">Cancel</button>

        <div class="login-link">
            Don't have an account?
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </div>
    </form>

</body>
</html>

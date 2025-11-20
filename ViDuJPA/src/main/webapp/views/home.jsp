<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>

<style>
    body {
        margin: 0;
        padding: 0;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #74ABE2, #5563DE);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #fff;
        animation: fadeIn 0.7s ease-in-out;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-10px); }
        to { opacity: 1; transform: translateY(0); }
    }

    .container {
        text-align: center;
        background: rgba(255, 255, 255, 0.15);
        padding: 40px 60px;
        border-radius: 20px;
        backdrop-filter: blur(10px);
        box-shadow: 0 8px 30px rgba(0,0,0,0.2);
    }

    h1 {
        font-size: 32px;
        margin-bottom: 10px;
    }

    p {
        font-size: 18px;
        margin-top: 5px;
        opacity: 0.9;
    }

    a.btn {
        display: inline-block;
        margin-top: 25px;
        padding: 12px 30px;
        background: #fff;
        color: #5563DE;
        border-radius: 8px;
        font-weight: bold;
        text-decoration: none;
        font-size: 16px;
        transition: 0.3s ease;
    }

    a.btn:hover {
        background: #e2e2e2;
    }

</style>
</head>
<body>

    <div class="container">
        <h1>🎉 Welcome to Home Page!</h1>
        <p>You have successfully logged in.</p>
        
        <a href="${pageContext.request.contextPath}/logout" class="btn">
            Logout
        </a>
    </div>

</body>
</html>

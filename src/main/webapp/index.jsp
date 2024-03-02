<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url("https://assets.ey.com/content/dam/ey-sites/ey-com/en_be/topics/financial-services/ey-art-instant-payments-417305058.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 300px;
            max-width: 80%;
            text-align: center;
        }

        .container h1 {
            margin-bottom: 20px;
            color: #333;
        }

        .container form {
            text-align: left;
            margin-bottom: 20px;
        }

        .container label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .container input {
            width: 93%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .container .input-group {
            margin-bottom: 20px; /* Increased spacing */
        }

        .container input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 8px;
            cursor: pointer;
            transition: background-color 0.5s;
            width: 100%;
        }

        .container input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .container a {
            text-decoration: none;
            color: #007bff;
        }

        .container a:hover {
            text-decoration: underline;
        }

        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="adminLogin" method="post">
            <div class="input-group">
                <label for="adminUsername">Admin Username</label>
                <input type="text" id="adminUsername" name="adminUsername" required>
            </div>
            
            <div class="input-group">
                <label for="adminPassword">Admin Password</label>
                <input type="password" id="adminPassword" name="adminPassword" required>
            </div>
            
            <input type="submit" value="Admin Login">
        </form>
        
        <form action="userLogin" method="post">
            <div class="input-group">
                <label for="userUsername">User Username</label>
                <input type="text" id="userUsername" name="userUsername" required>
            </div>
            
            <div class="input-group">
                <label for="userPassword">User Password</label>
                <input type="password" id="userPassword" name="userPassword" required>
            </div>
            
            <input type="submit" value="User Login">
        </form>
        
        <div class="signup-link">
            Don't have an account? <a href="signup.action">Sign Up</a>
        </div>
    </div>
</body>
</html>

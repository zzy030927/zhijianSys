<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录界面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);

        }
        input[type="text"], input[type="password"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .register-link {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>用户登录界面</h2>
    <form action="DengluServlet" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" placeholder="111" required>
        <label for="password">密 码:</label>
        <input type="password" id="password" name="password" placeholder="111" required>
        <input type="submit" value="登录">
        <input type="reset" value="重置">
    </form>
    <form action="SignIn.jsp">
        <input type="submit" value="新用户注册">
    </form>
</div>
</body>
</html>



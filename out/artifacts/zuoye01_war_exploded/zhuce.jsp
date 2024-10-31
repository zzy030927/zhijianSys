
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+ "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册界面</title>
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
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
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
    <h2 style="text-align:center;">用户注册界面</h2>
    <form action="ZhuceServlet" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <input type="radio"name="sex"value="男"checked>男
            <input type="radio"name="sex"value="女">女<br><br>
        </div>
        <div class="form-group">
            <label for="home">家 乡 :</label>
            <select id="home" name="home">
                <option value="上海">上海</option>
                <option value="北京" selected>北京</option>
                <option value="天津">天津</option>
                <!-- Add more options as needed -->
            </select>
        </div>
        <div class="form-group">
            <label for="info">个人备注信息:</label>
            <textarea id="info" name="info" rows="4"></textarea>
        </div>
        <div class="form-group">
            <input type="reset"value="重置">
            <input type="submit"value="注册">

        </div>
    </form>
</div>
</body>
</html>

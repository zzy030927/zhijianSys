<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
        input[type="submit"] {
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
    ${folderPath} <br>
    <h2>数据读取成功</h2>
        <form action="SearchallServlet">
            <input type="submit" value="查看所有用户">
        </form>
        <form action="AllDefectAnalyzeServlet">
            <input type="submit" value="查看所有缺陷分析记录">
        </form>
        <form action="SearchDefectAnalyzeServlet">
            <input type="submit" value="按时间查询缺陷分析记录">
        </form>
        <form action="RateChangeUploadServlet">
            <input type="submit" value="合格率有变化时上传">
        </form>
        <form action="UploadDataUpdateServlet">
            <input type="submit" value="数据有更新时上传">
        </form>

</div>

</body>
</html>


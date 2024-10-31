
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <title>所有用户页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 100%;
            margin: 1px auto;
            background-color: #fff;
            padding: 1px;
            border-radius: 1px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1px;
        }
        table th, table td {
            padding: 1px;
            border: 1px solid #ccc;
            text-align: center;
        }
        table th {
            background-color: #007bff;
            color: #fff;
        }
        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>


<div class="container">
    <h2>查询所有用户</h2>


    <table>
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>密码</th>
            <th>家乡</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        <c:forEach var="U" items="${userAll}"  >
            <form action="UpdateServlet" method="post">
                <tr>
                    <td><input type="text" value="${U.id}" name="id" ></td>
                    <td><input type="text" value="${U.name}" name="name"></td>
                    <td><input type="text" value="${U.sex}" name="sex"></td>
                    <td><input type="text" value="${U.pwd}" name="pwd"></td>
                    <td><input type="text" value="${U.home}" name="home"></td>
                    <td><input type="text" value="${U.info}" name="info"></td>
                    <td><a href="DeleteServlet?id=${U.id}">删除</a> </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>

</body>
</html>
<%@ page import="java.util.List" %>
<%@ page import="zhijian.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>查看所有用户</title>
  <style>

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
  </style>
  <link href="./bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  ${xiaoping} <br>
  <h2>查看所有用户</h2>
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

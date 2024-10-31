<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>按时间查询质检分析数据</title>
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
  <h2>按时间查询质检分析数据</h2>

  <form action="UpSearchDefectAnalyzeServlet" method="post">
    <label for="start-time">开始时间：</label>
    <input type="datetime-local" id="start-time" name="start-time">
    <label for="end-time">结束时间：</label>
    <input type="datetime-local" id="end-time" name="end-time">
    <input type="submit" value="查询">
  </form>

  <table>
    <tr>
      <th>产品名称</th>
      <th>操作员</th>
      <th>检测参数</th>
      <th>缺陷总数</th>
      <th>合格</th>
      <th>不合格</th>
      <th>背景缺陷数量</th>
      <th>纹理缺陷数量</th>
      <th>检验时间</th>
      <th>操作</th>
    </tr>
    <c:forEach var="U" items="${defectAnalyzes}"  >
      <form method="post">

        <tr>
          <td ><input type="text" value="${U.productName}" name="productName" ></td>
          <td ><input type="text" value="${U.operator}" name="operator" ></td>
          <td ><input type="text" value="${U.checkParameters}" name="checkParameters" ></td>
          <td ><input type="text" value="${U.defectTotal}" name="defectTotal"></td>
          <td ><input type="text" value="${U.qualifiedNumber}" name="qualifiedNumber"></td>
          <td ><input type="text" value="${U.notQualifiedNumber}" name="notQualifiedNumber"></td>
          <td ><input type="text" value="${U.defectBackground}" name="defectBackground"></td>
          <td ><input type="text" value="${U.defectTexture}" name="defectTexture"></td>
          <td ><input type="text" value="${U.checkTime}" name="checkTime"></td>
          <td><a href="DownloadServlet?id=${U.id}"> 下 载 </a> </td>
        </tr>
      </form>
    </c:forEach>
  </table>
</div>
</body>
</html>

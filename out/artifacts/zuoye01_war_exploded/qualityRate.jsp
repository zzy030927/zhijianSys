<%@ page import="java.util.List" %>
<%@ page import="org.apache.http.util.TextUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>合格率上传</title>
  <link href="./bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2>合格率上传</h2>
  <ul class="list-group">
    <%
      String responseContent = (String) request.getAttribute("rates");
      if (!TextUtils.isEmpty(responseContent)) {
          out.println("<li class='list-group-item'>" + responseContent + "</li>");
      } else {
        out.println("<li class='list-group-item'>没有合格率数据</li>");
      }
    %>
  </ul>
</div>
</body>
</html>

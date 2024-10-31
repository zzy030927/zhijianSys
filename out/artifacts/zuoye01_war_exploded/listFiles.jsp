<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>文件目录</title>
</head>
<body>
<h2>文件目录：</h2>
<%
  String folderPath = request.getParameter("folderPath");
  File folder = new File(folderPath);
  if(folder.exists() && folder.isDirectory()) {
    folder.listFiles();
  } else {
    out.println("<p>文件夹不存在或不是一个有效的文件夹。</p>");
  }

  // 递归遍历文件夹并显示目录及文件
  void listFiles(File folder) {
  File[] files = folder.listFiles();
  for(File file : files) {
  if(file.isDirectory()) {
%>
<p><strong><%= file.getName() %></strong></p>
<ul>
  <%
    listFiles(file); // 递归调用自身以遍历子目录
  %>
</ul>
<%
  } else {
%>
<li><%= file.getName() %> - <%= file.getPath() %></li>
<%
  }
  }
  }
%>
</body>
</html>

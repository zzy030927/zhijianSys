<%@ page import="org.apache.http.util.TextUtils" %>
<%@ page import="javax.servlet.jsp.JspWriter" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<%@ page import="zhijian.util.DBconn" %>
<%@ page import="zhijian.dao.daoImp.FolderPathDaoImpl" %>
<%@ page import="zhijian.dao.FolderPathDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>选择预览文件夹</title>
  <style>
    .folder-hierarchy li {
      margin-left: 20px;
    }
  </style>
</head>
<body>
<h1>选择预览文件夹</h1>
<form action="selectFolder.jsp" method="post">
  <label for="folderPath">填写一个文件夹路径:</label>
  <input type="text" id="folderPath" name="folderPath" required>
  <button type="submit">点击预览</button>
</form>

<%
  // 检查是否提交了表单
  if ("POST".equals(request.getMethod())) {
    String folderPath = request.getParameter("folderPath");

    if(!TextUtils.isEmpty(folderPath)){
      DBconn.init();
      FolderPathDao folderPathDao=new FolderPathDaoImpl();
      folderPathDao.add(folderPath);
      DBconn.closeConn();
    }

    if(TextUtils.isEmpty(folderPath)){
      return;
    }
    File folder = new File(folderPath);
    // 检查文件夹是否存在
    if (folder.exists() && folder.isDirectory()) {
      out.println("<h2> 文件夹名字与路径:</h2>");
      out.println("<p>" + folder.getName()+"-----"+folder.getPath() + "</p>");
      // 递归调用，获取文件夹及其子文件夹的层次关系
      displayFolderHierarchy(out,folder, "");
    } else {
      out.println("<h2>文件名与路径:</h2>");
      out.println("<p>" + folder.getName()+"-----"+folder.getPath()+"</p>");
    }
  }
%>
<%!

  // 递归方法，显示文件夹的层次关系
  private void displayFolderHierarchy(JspWriter out, File folder, String indent) throws IOException {
    File[] files = folder.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          // 递归调用
          out.println("<h2> 文件夹名字与路径:</h2>");
          out.println("<p>" + file.getName()+"-----"+file.getPath() + "</p>");

          displayFolderHierarchy(out, file, indent + "  ");
        }else {
          out.println("<h2>文件名与路径:</h2>");
          out.println("<p>" + file.getName()+"-----"+file.getPath()+"</p>");
        }

      }
    }
  }
%>
</body>
</html>

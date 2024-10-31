<%@ page import="org.apache.http.util.TextUtils" %>
<%@ page import="zhijian.util.ExcelReader" %>
<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>手动读取数据</title>
    <script>
        function loadData() {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "ReadDataServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById("dataDisplay").innerHTML = xhr.responseText;
                }
            };
            xhr.send();
        }

        function autoRefresh() {
            setInterval(loadData, 1500); // 每30秒刷新一次数据
        }
    </script>
</head>
<body onload="autoRefresh()">
<h1>手动读取数据</h1>
<form action="handReadExcelData.jsp" method="post">
    <label for="folderPath">填写一个文件夹路径:</label>
    <input type="text" id="folderPath" name="folderPath" required>
    <button type="submit">手动读取数据</button>
</form>
<%
    // 检查是否提交了表单
    if ("POST".equals(request.getMethod())) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(!TextUtils.isEmpty(request.getParameter("folderPath"))){
                        ExcelReader excelReader = new ExcelReader();
                        excelReader.readAndStoreExcel(new File(request.getParameter("folderPath")));
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
%>
<div id="dataDisplay">
    <!-- 数据展示区域 -->
</div>
</body>
</html>

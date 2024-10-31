<%@ page import="static zhijian.util.Utils.scanFiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>读取数据</title>
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
<h1>正在进行自动读取数据</h1>

<div id="dataDisplay">
    <!-- 数据展示区域 -->
</div>
</body>
</html>

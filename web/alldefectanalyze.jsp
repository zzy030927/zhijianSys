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
    <h2>查询所有缺陷分析数据</h2>


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
                </tr>
            </form>
        </c:forEach>
    </table>
</div>

</body>
</html>

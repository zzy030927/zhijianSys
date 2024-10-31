<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>产品合格率分析</title>
    <link href="./bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="container mt-5">
    <h2>产品合格率分析</h2>
    <div class="form-group">
        <label for="productName">选择产品名称:</label>
        <select class="form-control" id="productName">
            <option value="一级目录002">一级目录002</option>
            <option value="二级目录测试文件名字001">二级目录测试文件名字001</option>
            <option value="三级目录测试文件名字001">三级目录测试文件名字001</option>
            <option value="三级目录测试文件名字002">三级目录测试文件名字002</option>
            <option value="四级目录测试文件名字001">四级目录测试文件名字001</option>
            <option value="一级目录002副本">一级目录002副本</option>
        </select>
    </div>
    <button id="analyzeBtn" class="btn btn-primary">分析数据</button>
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

    <div class="mt-5">
        <h3>单一产品柱状图</h3>
        <canvas id="singleBarChart"></canvas>
    </div>
    <div class="mt-5">
        <h3>单一产品饼状图</h3>
        <canvas id="singlePieChart"></canvas>
    </div>
    <div class="mt-5">
        <h3>全部产品柱状图</h3>
        <canvas id="totalBarChart"></canvas>
    </div>
    <div class="mt-5">
        <h3>全部产品饼状图</h3>
        <canvas id="totalPieChart"></canvas>
    </div>
</div>
<script>
    document.getElementById('analyzeBtn').addEventListener('click', function() {
        const productName = document.getElementById('productName').value;
        fetch('analyzeData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ productName: productName })

        })
            .then(response => response.json())
            .then(data => {
                drawBarChart('singleBarChart', data.singleProduct);
                drawPieChart('singlePieChart', data.singleProduct);
                drawBarChart('totalBarChart', data.totalProduct);
                drawPieChart('totalPieChart', data.totalProduct);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });

    function drawBarChart(canvasId, data) {
        const ctx = document.getElementById(canvasId).getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['合格数', '不合格数', '背景缺陷', '纹理缺陷'],
                datasets: [{
                    label: '数量',
                    data: [data.qualifiedNumber, data.notQualifiedNumber, data.defectBackground, data.defectTexture],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    function drawPieChart(canvasId, data) {
        const ctx = document.getElementById(canvasId).getContext('2d');
        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ['合格', '不合格'],
                datasets: [{
                    data: [data.qualifiedNumber, data.notQualifiedNumber],
                    backgroundColor: ['rgba(54, 162, 235, 0.2)', 'rgba(255, 99, 132, 0.2)'],
                    borderColor: ['rgba(54, 162, 235, 1)', 'rgba(255, 99, 132, 1)'],
                    borderWidth: 1
                }]
            },
            options: {
                radius: 250
            }
        });
    }
</script>
</body>
</html>

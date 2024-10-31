<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>菜单界面</title>
  <link href="./bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      display: flex;
      height: 100vh;
      margin: 0;
    }
    .sidebar {
      width: 250px;
      background-color: #343a40;
      padding: 0;
      padding-left: 10px;
      display: flex;
      flex-direction: column;
      line-height: 2;
    }
    .sidebar .list-group-item {
      color: white;
      background-color: #343a40;
      border: none;
      font-size: 18px;
    }
    .sidebar .list-group-item:hover, .sidebar .list-group-item:focus {
      background-color: #495057;
    }
    .sidebar .list-group-item-action {
      padding: 5px;
      cursor: pointer;
    }
    .sidebar .submenu .list-group-item {
      padding: 0 20px;
      font-size: 0.875rem; /* 2个字号小 */
      color: #adb5bd; /* 二级菜单字体颜色 */
    }
    .content {
      flex-grow: 1;
      padding: 20px;
      display: flex;
      justify-content: flex-start;
      flex-wrap: wrap;
    }
    iframe {
      width: 100%;
      height: 100%;
      border: none;
    }
  </style>
</head>
<body>
<div class="sidebar">
  <form id="menuForm" method="post" action="/menu" target="contentFrame">
  <div class="list-group">
    <a href="#dataSourceSubmenu" class="list-group-item list-group-item-action" data-toggle="collapse" aria-expanded="false" aria-controls="dataSourceSubmenu">
      原始数据
    </a>
    <div class="collapse submenu" id="dataSourceSubmenu">
      <button type="submit" name="action" value="fileReadPreview" class="list-group-item list-group-item-action">文件选取预览</button><br>
      <button type="submit" name="action" value="folderPreview" class="list-group-item list-group-item-action">文件夹配置预览</button><br>
      <button type="submit" name="action" value="dataAutoReadDisplay" class="list-group-item list-group-item-action">自动读取数据</button><br>
      <button type="submit" name="action" value="dataHandReadDisplay" class="list-group-item list-group-item-action">手动读取数据</button><br>
    </div>
    <a href="#saveDataSourceSubmenu" class="list-group-item list-group-item-action" data-toggle="collapse" aria-expanded="false" aria-controls="dataSourceSubmenu">
      数据存储
    </a>
    <div class="collapse submenu" id="saveDataSourceSubmenu">
      <button type="submit" name="action" value="dataFindDisplay" class="list-group-item list-group-item-action">数据查询展示</button>
    </div>
    <button type="submit" name="action" value="dataAnalysis" class="list-group-item list-group-item-action">数据分析</button>
    <a href="#dataUploadSubmenu" class="list-group-item list-group-item-action" data-toggle="collapse" aria-expanded="false" aria-controls="dataUploadSubmenu">
      数据上传
    </a>
    <div class="collapse submenu" id="dataUploadSubmenu">
      <button type="submit" name="action" value="dataUpload" class="list-group-item list-group-item-action">数据主动上传中台</button>
    </div>
    <button type="submit" name="action" value="viewUsers" class="list-group-item list-group-item-action">用户查询</button>
  </div>
  </form>
</div>
<br>
<div class="content">
  <p style="display: inline; margin-left: 8px">管理员：张振炎</p>
  <iframe name="contentFrame" id="contentFrame"></iframe>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="./bootstrap.min.js"></script>
<script>
  function loadPage(page) {
    document.getElementById('contentFrame').src = page;
  }
</script>
</body>
</html>

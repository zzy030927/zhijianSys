<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Excel 文件上传与预览</title>
  <link href="./bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2>上传Excel文件</h2>
  <form id="uploadForm" action="uploadExcel" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="file">选择Excel文件:</label>
      <input type="file" class="form-control" id="file" name="file" accept=".xlsx,.xls">
    </div>
    <button type="submit" class="btn btn-primary">上传</button>
  </form>
  <div id="preview" class="mt-5"></div>
</div>
<script>
  document.getElementById('uploadForm').onsubmit = function(event) {
    event.preventDefault();
    var formData = new FormData(this);
    fetch('uploadExcel', {
      method: 'POST',
      body: formData
    })
            .then(response => response.json())
            .then(data => {
              if(data.success) {
                let previewDiv = document.getElementById('preview');
                previewDiv.innerHTML = '<h3>Excel 文件预览</h3><table class="table table-bordered"><thead><tr>';
                data.headers.forEach(header => {
                  previewDiv.innerHTML += '<th>' + header + '</th>';
                });
                previewDiv.innerHTML += '</tr></thead><tbody>';
                data.rows.forEach(row => {
                  previewDiv.innerHTML += '<tr>';
                  row.forEach(cell => {
                    previewDiv.innerHTML += '<td>' + cell + '</td>';
                  });
                  previewDiv.innerHTML += '</tr>';
                });
                previewDiv.innerHTML += '</tbody></table>';
              } else {
                alert('文件上传失败');
              }
            })
            .catch(error => {
              console.error('Error:', error);
              alert('文件上传失败');
            });
  };
</script>
</body>
</html>

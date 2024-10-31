<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>预览Excel文件</title>

</head>
<body>
<h2>选择预览Excel文件</h2>
<form action="FilePreViewServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="file" accept=".xls,.xlsx" required>
    <button type="submit">上传并预览</button>
</form>
</body>
</html>

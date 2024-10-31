<!DOCTYPE html>
<html>
<head>
    <title>Upload Excel File</title>
</head>
<body>
<h2>Upload Excel File</h2>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    Select file: <input type="file" name="file" accept=".xls,.xlsx">
    <input type="submit" value="Upload">
</form>
</body>
</html>

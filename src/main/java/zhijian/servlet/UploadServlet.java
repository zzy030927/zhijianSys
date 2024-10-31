package zhijian.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从请求中获取文件部分
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();

        // 创建输入流
        InputStream inputStream = filePart.getInputStream();

        Connection conn = null;
        String message = null;

        try {
            // 连接数据库
            String dbURL = "jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf-8";
            String dbUser = "root";
            String dbPass = "123456";

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true", "root", "123456");

            // 构建SQL语句
            String sql = "INSERT INTO excel_files (file_name, file_data) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, fileName);

            if (inputStream != null) {
                statement.setBlob(2, inputStream);
            }

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (Exception ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            request.setAttribute("xiaoping", message);
            getServletContext().getRequestDispatcher("/download.jsp").forward(request, response);
        }
    }
}


package zhijian.servlet;



import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fileId = request.getParameter("id"); // 获取文件ID
        Connection conn = null;

        try {
            // Connects to the database
            String dbURL = "jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf-8";
            String dbUser = "root";
            String dbPass = "123456";

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true", "root", "123456");

            String sql = "SELECT productName, fileData FROM defectanalyze WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(fileId));

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String fileName = result.getString("productName");
                InputStream inputStream = result.getBinaryStream("fileData");

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition", "attachment; productName=\"" + fileName + "\"");

                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}



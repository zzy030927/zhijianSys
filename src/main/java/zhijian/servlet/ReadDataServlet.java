package zhijian.servlet;
import zhijian.entity.DefectAnalyze;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DataServlet")
public class ReadDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf-8";
        String username = "12";
        String password = "123456";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:/pingzi?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true", "root", "123456");
            stmt = conn.createStatement();
            String query = "SELECT * FROM defectanalyze";
            rs = stmt.executeQuery(query);

            result.append("<table border='1'>");
            result.append("<tr><th>ID</th><th>文件名</th><th>检测员</th><th>检测时间</th><th>检测参数</th><th>检测数量</th></tr>");
//            for (DefectAnalyze da :all) {
//                result.append("<tr>");
//                result.append("<td>").append(da.getId()).append("</td>");
//                result.append("<td>").append(da.getProductName()).append("</td>");
//                result.append("<td>").append(da.getOperator()).append("</td>");
//                result.append("<td>").append(da.getCheckTime()).append("</td>");
//                result.append("<td>").append(da.getCheckParameters()).append("</td>");
//                result.append("<td>").append(da.getDefectTotal()).append("</td>");
//                result.append("</tr>");
//            }
            //productName,operator,checkParameters,defectTotal,qualifiedNumber,notQualifiedNumber,defectBackground,defectTexture,checkTime
            while (rs.next()) {
                result.append("<tr>");
                result.append("<td>").append(rs.getInt("id")).append("</td>");
                result.append("<td>").append(rs.getString("productName")).append("</td>");
                result.append("<td>").append(rs.getString("operator")).append("</td>");
                result.append("<td>").append(rs.getString("checkTime")).append("</td>");
                result.append("<td>").append(rs.getString("checkParameters")).append("</td>");
                result.append("<td>").append(rs.getString("defectTotal")).append("</td>");
                result.append("</tr>");
            }
            result.append("</table>");
        } catch (Exception e) {
            result.append("Error: ").append(e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ignore) {}
            if (stmt != null) try { stmt.close(); } catch (Exception ignore) {}
            if (conn != null) try { conn.close(); } catch (Exception ignore) {}
        }

        out.print(result.toString());
    }
}


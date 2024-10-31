<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
  String url = "jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf-8";
  String username = "root";
  String password = "123456";
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  StringBuilder result = new StringBuilder();

  try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true", "root", "123456");
    stmt = conn.createStatement();
    String query = "SELECT * FROM defectanalyze";
    rs = stmt.executeQuery(query);

    result.append("<table border='1'>");
    result.append("<tr><th>ID</th><th>Name</th><th>Value</th></tr>");

    while (rs.next()) {
      result.append("<tr>");
      result.append("<td>").append(rs.getInt("id")).append("</td>");
      result.append("<td>").append(rs.getString("productName")).append("</td>");
      result.append("<td>").append(rs.getString("operator")).append("</td>");
      result.append("</tr>");
    }
    result.append("</table>");
  } catch (Exception e) {
    result.append("Error: ").append(e.getMessage());
  } finally {
    if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
    if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
    if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
  }

  out.print(result.toString());
%>

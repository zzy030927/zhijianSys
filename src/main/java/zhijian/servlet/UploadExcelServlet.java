package zhijian.servlet;

        import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
        import org.apache.commons.fileupload.servlet.ServletFileUpload;
        import org.apache.commons.fileupload.disk.DiskFileItemFactory;
        import org.apache.commons.fileupload.FileItem;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.io.InputStream;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.stream.Collectors;
        import com.google.gson.Gson;

        import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

/**
 *
 * 名称：UploadExcelServlet
 * 描述：选择excel读取上传
 *
 * @version 1.0
 * @author: Ma xiaoping
 * @datetime: 2024-05-30 17:49
 */

@WebServlet("/uploadExcel")
public class UploadExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().write(gson.toJson(new Response(false, "Form must has enctype=multipart/form-data.")));
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> formItems = upload.parseRequest(request);
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    InputStream inputStream = item.getInputStream();
                    List<String> headers = new ArrayList<>();
                    List<List<String>> rows = new ArrayList<>();
                    Workbook workbook = new XSSFWorkbook(inputStream);
                    Sheet sheet = workbook.getSheetAt(0);
                    for (Row row : sheet) {
                        List<String> rowData = new ArrayList<>();
                        for (Cell cell : row) {
                            if (cell.getCellType() == NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                                headers.add(cell.getLocalDateTimeCellValue().toString());
                                rowData.add(cell.getLocalDateTimeCellValue().toString());
                            } else {
                                cell.setCellType(CellType.STRING);
                                headers.add(cell.getStringCellValue());
                                rowData.add(cell.getStringCellValue());
                            }
                        }
                        rows.add(rowData);
                    }

                    workbook.close();
                    inputStream.close();

                    response.getWriter().write(gson.toJson(new Response(true, headers, rows)));
                    return;
                }
            }
        } catch (Exception ex) {
            response.getWriter().write(gson.toJson(new Response(false, "Error:====== " + ex.getMessage())));
        }
    }

    private static class Response {
        private boolean success;
        private List<String> headers;
        private List<List<String>> rows;
        private String message;

        public Response(boolean success, List<String> headers, List<List<String>> rows) {
            this.success = success;
            this.headers = headers;
            this.rows = rows;
        }

        public Response(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }
}


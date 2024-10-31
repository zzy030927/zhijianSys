package zhijian.servlet;


        import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.Part;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Iterator;

@WebServlet("/previewExcel")
public class PreviewExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        if (filePart != null) {
            try (InputStream inputStream = filePart.getInputStream()) {
                Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                StringBuilder sb = new StringBuilder();
                sb.append("<table border='1'>");

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    sb.append("<tr>");
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        sb.append("<td>").append(getCellValue(cell)).append("</td>");
                    }
                    sb.append("</tr>");
                }
                sb.append("</table>");

                workbook.close();
                request.setAttribute("previewData", sb.toString());
                request.getRequestDispatcher("excelPreview.jsp").forward(request, response);
            }
        }
    }

    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}



package zhijian.servlet;


import zhijian.dao.DefectAnalyzeDao;
import zhijian.dao.daoImp.DefectAnalyzeDaoImpl;
import zhijian.entity.DefectAnalyze;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UploadDataServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String url = request.getParameter("folderPath");


//        String responseContent = Utils.uploadJsonUpload(url);
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        request.setAttribute("responseContent", "{\n" +
                "  \"success\": true,\n" +
                "  \"statusCode\": 200,\n" +
                "  \"message\": \"\",\n" +
                "  \"data\": null\n" +
                "}");
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        request.setAttribute("defectAnalyzes", defectAnalyzes);
        request.getRequestDispatcher("/dataUpload.jsp").forward(request, response);
    }
}



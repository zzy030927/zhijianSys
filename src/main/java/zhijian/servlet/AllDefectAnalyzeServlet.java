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


public class AllDefectAnalyzeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        request.setAttribute("defectAnalyzes", defectAnalyzes);
        request.getRequestDispatcher("/alldefectanalyze.jsp").forward(request, response);


    }
}


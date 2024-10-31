package zhijian.servlet;


import zhijian.dao.DefectAnalyzeDao;
import zhijian.dao.daoImp.DefectAnalyzeDaoImpl;
import zhijian.entity.DefectAnalyze;
import zhijian.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class UpSearchDefectAnalyzeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String star_time = request.getParameter("start-time"); //得到jsp页面传过来的参数
        String end_time = request.getParameter("end-time");
        String star = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),star_time);
        String end = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),end_time);
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> findDefectAnalyzes = dad.getFindDefectAnalyze(star, end);
        request.setAttribute("defectAnalyzes", findDefectAnalyzes);
        request.getRequestDispatcher("/queryByTime.jsp").forward(request, response);
    }

}


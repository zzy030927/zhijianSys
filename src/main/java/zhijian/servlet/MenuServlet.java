package zhijian.servlet;

import org.apache.http.util.TextUtils;
import zhijian.dao.*;
import zhijian.dao.daoImp.DefectAnalyzeDaoImpl;
import zhijian.dao.daoImp.FolderPathDaoImpl;
import zhijian.dao.daoImp.UserDaoImpl;
import zhijian.entity.DefectAnalyze;
import zhijian.entity.User;
import zhijian.util.ExcelReader;
import zhijian.util.Utils;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private ScheduledExecutorService scheduler;
    private String folderPath = "/Users/ASUS/Desktop/430017.csv"; // 文件夹路径


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        scheduler = Executors.newSingleThreadScheduledExecutor();

    }

    @Override
    public void destroy() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        super.destroy();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "viewUsers":
                viewUsers(request, response);
                break;
            case "fileReadPreview":
                fileReadPreview(request, response);
                break;
            case "folderPreview":
                folderPreview(request, response);
                break;
            case "dataAutoReadDisplay":
                dataAutoReadDisplay(request, response);
                break;
            case "dataHandReadDisplay":
                dataHandReadDisplay(request, response);
                break;
            case "dataFindDisplay":
                dataFindDisplay(request, response);
                break;
            case "dataAnalysis":
                dataAnalysis(request, response);
                break;
            case "dataUpload":
                dataUpload(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }
    }



    private void viewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserDao ud = new UserDaoImpl();
        List<User> userAll = ud.getUserAll();
        request.setAttribute("userAll", userAll);
        request.getRequestDispatcher("/viewUsers.jsp").forward(request, response);

    }
    private void fileReadPreview(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/uploadExcel.jsp").forward(request, response);
    }

    private void folderPreview(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/selectFolder.jsp").forward(request, response);
    }

    private void dataAutoReadDisplay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {

        request.setCharacterEncoding("utf-8");
        scheduler.scheduleAtFixedRate(() -> scanFiles(), 0, 300, TimeUnit.SECONDS);
        request.getRequestDispatcher("/readExcelData.jsp").forward(request, response);
    }
    private void dataHandReadDisplay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/handReadExcelData.jsp").forward(request, response);
    }

    private void dataFindDisplay(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        request.setCharacterEncoding("utf-8");
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        request.setAttribute("defectAnalyzes", defectAnalyzes);
        request.getRequestDispatcher("/queryByTime.jsp").forward(request, response);
    }
    private void dataAnalysis(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
        request.setCharacterEncoding("utf-8");
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        request.setAttribute("defectAnalyzes", defectAnalyzes);
        request.getRequestDispatcher("/analyze.jsp").forward(request, response);
    }


    private void viewDefects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        request.setAttribute("defectAnalyzes", defectAnalyzes);
        request.getRequestDispatcher("/viewDefects.jsp").forward(request, response);
    }

    private void queryByTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("defectAnalyzes", null);
        request.getRequestDispatcher("/queryByTime.jsp").forward(request, response);
    }

    private void qualityRate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String responseContent=null;
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
//      responseContent = Utils.uploadPassRateJsonUpload();
        request.setAttribute("responseContent", responseContent);
        request.getRequestDispatcher("/qualityRate.jsp").forward(request, response);
    }

    private void dataUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        request.setAttribute("defectAnalyzes", defectAnalyzes);
        request.getRequestDispatcher("/dataUpload.jsp").forward(request, response);
    }
    private void inquire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String star_time = request.getParameter("start-time");
        String end_time = request.getParameter("end-time");
        String star = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),star_time);
        String end = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),end_time);
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> findDefectAnalyzes = dad.getFindDefectAnalyze(star, end);
        request.setAttribute("defectAnalyzes", findDefectAnalyzes);
        request.getRequestDispatcher("/queryByTime.jsp").forward(request, response);
    }
    private void readFileData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/analyze.jsp").forward(request, response);
    }

    public void scanFiles() {
        try {
            FolderPathDao folderPathDao=new FolderPathDaoImpl();
            String lastRecordPath = folderPathDao.getLastRecordPath();
            if(TextUtils.isEmpty(lastRecordPath)){
                lastRecordPath="D:/沧州交通学院/javaWeb/二印项目/二印质检数据";
            }
            ExcelReader excelReader = new ExcelReader();
            excelReader.readAndStoreExcel(new File(lastRecordPath));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}



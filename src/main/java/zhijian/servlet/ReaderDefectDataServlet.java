package zhijian.servlet;


import zhijian.util.ExcelReader;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ReaderDefectDataServlet extends HttpServlet {
    private ScheduledExecutorService scheduler;
    private String folderPath = "/Users/maxiaoping/Documents/中印科院项目对接/二印质检数据"; // 文件夹路径

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(() -> scanFiles(), 0, 300, TimeUnit.SECONDS);
    }

    @Override
    public void destroy() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        super.destroy();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        scanFiles();
        request.setAttribute("folderPath", "缺陷数据读取路径为："+folderPath);
//        request.getRequestDispatcher("/readersuccess.jsp").forward(request, response);
        request.getRequestDispatcher("/fetchData.jsp").forward(request, response);
    }


    public void scanFiles() {
        try {
//            ExcelReader excelReader = new ExcelReader();
//            excelReader.readAndStoreExcel(new File(folderPath));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


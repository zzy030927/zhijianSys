package zhijian.servlet;
import com.google.gson.Gson;
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
import java.util.Enumeration;
import java.util.List;
public class DefectAnalyzeInterfaceServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String star=null;
        String end=null;
        // 设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 获取POST请求body中的数据
        Enumeration<String> parameterNames = request.getParameterNames();
        // 遍历参数名，并获取对应的值
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            System.out.println("Key: " + paramName + ", Value: " + paramValue);
            if(paramName.equals("start-time")){
                star = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),paramValue);
            }
            if(paramName.equals("end-time")) {
                 end = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"),paramValue);
            }
        }

        // 返回响应
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> findDefectAnalyzes = dad.getFindDefectAnalyze(star, end);
        response.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(findDefectAnalyzes);
        response.getWriter().write(json);
    }


}


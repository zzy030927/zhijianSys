package zhijian.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.TextUtils;
import org.json.JSONObject;
import zhijian.dao.DefectAnalyzeDao;
import zhijian.dao.daoImp.DefectAnalyzeDaoImpl;
import zhijian.dao.FolderPathDao;
import zhijian.dao.daoImp.FolderPathDaoImpl;
import zhijian.entity.DefectAnalyze;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 名称：Utils
 * 描述：工具类
 *
 * @version 1.0
 * @author: Ma xiaoping
 * @datetime: 2024-05-11 14:20
 */
public class Utils {

    public static String timeFormat(DateTimeFormatter inputFormatter,String inputDateTime){

        // 定义输入和输出的日期时间格式
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 将输入的时间字符串解析为LocalDateTime对象
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);

        // 格式化LocalDateTime对象为输出的日期字符串
        String outputDate = dateTime.format(outputFormatter);

        // 输出结果
        System.out.println("转换后的日期为：" + outputDate);
        return outputDate;
    }
    public static String uploadJsonUpload(String url) throws IOException {
        HttpPost httpPost;
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        Gson gson = new Gson();
        String json = gson.toJson(defectAnalyzes);

        // 创建HttpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpPost请求
        if(TextUtils.isEmpty(url)){
             httpPost = new HttpPost("http://192.168.1.181:3000/api/v1/qc/update");
        }else {
             httpPost = new HttpPost(url);
        }


        // 设置请求体内容
        StringEntity entity = new StringEntity(json, "UTF-8");
        httpPost.setEntity(entity);

        // 设置请求头部
        httpPost.setHeader("Content-type", "application/json");

        // 发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            // 获取响应状态码
            System.out.println("Response Status: " + response.getStatusLine().getStatusCode());

            // 获取响应实体
            HttpEntity responseEntity = response.getEntity();

            // 读取响应内容
            String responseContent = EntityUtils.toString(responseEntity);
            System.out.println("Response Content: " + responseContent);
            return responseContent;
        } finally {
            // 关闭响应
            response.close();
        }
    }
    public static String uploadPassRateJsonUpload() throws IOException {
        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();
        int Pass_number=0;
        int total_num=0;
        int rate=0;
        for (DefectAnalyze defectAnalyze:defectAnalyzes) {
            Pass_number+=Integer.valueOf(defectAnalyze.getQualifiedNumber());
            total_num+=Integer.valueOf(defectAnalyze.getDefectTotal());
        }
        rate=Pass_number/total_num;
        Gson gson = new Gson();
        String json = gson.toJson(defectAnalyzes);
        JSONObject jso=new JSONObject(json);
        jso.put("rate",rate+"");

        // 创建HttpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpPost请求
        HttpPost httpPost = new HttpPost("http://192.168.1.181:3000/api/v1/qc/update");

        // 设置请求体内容
        StringEntity entity = new StringEntity(jso.toString(), "UTF-8");
        httpPost.setEntity(entity);

        // 设置请求头部
        httpPost.setHeader("Content-type", "application/json");

        // 发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            // 获取响应状态码
            System.out.println("Response Status: " + response.getStatusLine().getStatusCode());

            // 获取响应实体
            HttpEntity responseEntity = response.getEntity();

            // 读取响应内容
            String responseContent = EntityUtils.toString(responseEntity);
            System.out.println("Response Content: " + responseContent);
            return responseContent;
        } finally {
            // 关闭响应
            response.close();
        }

    }


        // 获取指定文件夹下的所有子目录及文件的相对路径
        public static List<String> exploreFolder(String folderPath) {
            List<String> result = new ArrayList<>();
            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                exploreFolderRecursive(folder, folderPath, result);
            }
            return result;
        }

        // 递归遍历文件夹
        private static void exploreFolderRecursive(File folder, String basePath, List<String> result) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        result.add(file.getPath().replace(basePath, ""));
                        exploreFolderRecursive(file, basePath, result);
                    } else {
                        result.add(file.getPath().replace(basePath, ""));
                    }
                }
            }
        }
    public static void scanFiles(HttpServletRequest request, HttpServletResponse response) {
        try {
            FolderPathDao folderPathDao=new FolderPathDaoImpl();
            String lastRecordPath = folderPathDao.getLastRecordPath();
            if(TextUtils.isEmpty(lastRecordPath)){
                lastRecordPath="/Users/maxiaoping/Documents/中印科院项目对接/二印质检数据";
            }
            ExcelReader excelReader = new ExcelReader();
            excelReader.readAndStoreExcel(new File(lastRecordPath));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

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

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@WebServlet("/analyzeData")
public class AnalyzeDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        JsonObject requestBody = gson.fromJson(request.getReader(), JsonObject.class);
        String productName = requestBody.get("productName").getAsString();

        DefectAnalyzeDao dad = new DefectAnalyzeDaoImpl();
        List<DefectAnalyze> defectAnalyzes = dad.getAll();

        String json = gson.toJson(defectAnalyzes);
        Type listType = new TypeToken<ArrayList<Map<String, String>>>() {}.getType();
        List<Map<String, String>> dataList = gson.fromJson(json, listType);

        // 单一产品
        int singleQualifiedNumber = 0;
        int singleNotQualifiedNumber = 0;
        int singleDefectBackground = 0;
        int singleDefectTexture = 0;

        // 全部产品
        int totalQualifiedNumber = 0;
        int totalNotQualifiedNumber = 0;
        int totalDefectBackground = 0;
        int totalDefectTexture = 0;

        for (Map<String, String> data : dataList) {
            int qualifiedNumber = Integer.parseInt(data.get("qualifiedNumber"));
            int notQualifiedNumber = Integer.parseInt(data.get("notQualifiedNumber"));
            int defectBackground = Integer.parseInt(data.get("defectBackground"));
            int defectTexture = Integer.parseInt(data.get("defectTexture"));

            // 计算单一产品数据
            if (data.get("productName").equals(productName)) {
                singleQualifiedNumber += qualifiedNumber;
                singleNotQualifiedNumber += notQualifiedNumber;
                singleDefectBackground += defectBackground;
                singleDefectTexture += defectTexture;
            }

            // 计算全部产品数据
            totalQualifiedNumber += qualifiedNumber;
            totalNotQualifiedNumber += notQualifiedNumber;
            totalDefectBackground += defectBackground;
            totalDefectTexture += defectTexture;
        }

        JsonObject result = new JsonObject();

        JsonObject singleProduct = new JsonObject();
        singleProduct.addProperty("qualifiedNumber", singleQualifiedNumber);
        singleProduct.addProperty("notQualifiedNumber", singleNotQualifiedNumber);
        singleProduct.addProperty("defectBackground", singleDefectBackground);
        singleProduct.addProperty("defectTexture", singleDefectTexture);

        JsonObject totalProduct = new JsonObject();
        totalProduct.addProperty("qualifiedNumber", totalQualifiedNumber);
        totalProduct.addProperty("notQualifiedNumber", totalNotQualifiedNumber);
        totalProduct.addProperty("defectBackground", totalDefectBackground);
        totalProduct.addProperty("defectTexture", totalDefectTexture);

        result.add("singleProduct", singleProduct);
        result.add("totalProduct", totalProduct);

        response.getWriter().write(result.toString());
    }
}

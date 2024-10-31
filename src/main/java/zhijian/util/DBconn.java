package zhijian.util;

import org.apache.http.util.TextUtils;
import zhijian.dao.DefectAnalyzeDao;
import zhijian.dao.daoImp.DefectAnalyzeDaoImpl;
import zhijian.dao.DefectDataDao;
import zhijian.dao.daoImp.DefectDataDaoImpl;
import zhijian.entity.DefectAnalyze;
import zhijian.entity.DefectData;
import zhijian.entity.DefectFileName;
import zhijian.entity.User;

import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DBconn {
    static String url = "jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf-8";
    static String username = "root";
    static String password = "123456";
    static Connection conn = null;
    static ResultSet rs = null;
    static PreparedStatement ps = null;

    public static void init() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, username, password);

            Class.forName("com.mysql.cj.jdbc.Driver");  //加载驱动程序
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pingzi?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true", "root", "123456");  //连接数据库



        } catch (Exception e) {
            System.out.println("init [SQL驱动程序初始化失败！]");
            e.printStackTrace();
        }
    }

    public static int addUpdDel(String sql) {
        int i = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
        }

        return i;
    }

    public static ResultSet selectSql(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            e.printStackTrace();
        }
        return rs;
    }

    public static void closeConn() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql数据库关闭异常");
            e.printStackTrace();
        }
    }
    public static boolean addFolderPath(String sql,String path) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, path);//对sql语句中的第1个参数赋值
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row > 0) {
                System.out.print("成功添加了 " + row + " 条数据！！！");
            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
            return false;
        }

    }
    public static boolean addData(String sql,User user) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());//对sql语句中的第1个参数赋值
            ps.setString(2, user.getPwd());//对sql语句中的第2个参数赋值
            ps.setString(3, user.getSex());//对sql语句中的第3个参数赋值
            ps.setString(4, user.getHome());//对sql语句中的第4个参数赋值
            ps.setString(5, user.getInfo());//对sql语句中的第4个参数赋值
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row > 0) {
                System.out.print("成功添加了 " + row + " 条数据！！！");
            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
            return false;
        }

    }
    public static boolean addDefectAnalyzeData(String sql, DefectAnalyze defectAnalyze,InputStream fis) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, defectAnalyze.getProductName());//对sql语句中的第1个参数赋值
            ps.setString(2, defectAnalyze.getOperator());//对sql语句中的第2个参数赋值
            ps.setString(3, defectAnalyze.getCheckParameters());//对sql语句中的第3个参数赋值
            ps.setString(4, defectAnalyze.getDefectTotal());//对sql语句中的第4个参数赋值
            ps.setString(5, defectAnalyze.getQualifiedNumber());//对sql语句中的第5个参数赋值
            ps.setString(6, defectAnalyze.getNotQualifiedNumber());//对sql语句中的第6个参数赋值
            ps.setString(7, defectAnalyze.getDefectBackground());//对sql语句中的第7个参数赋值
            ps.setString(8, defectAnalyze.getDefectTexture());//对sql语句中的第8个参数赋值
            ps.setString(9, defectAnalyze.getCheckTime());//对sql语句中的第9个参数赋值
            if (fis != null) {
                ps.setBlob(10, fis);
            }
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row > 0) {
                System.out.print("成功添加了 " + row + " 条数据！！！");
            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
            return false;
        }

    }
    public static boolean addDefectData(String sql,DefectAnalyze defectAnalyze1,List<DefectData> defectDataList ,List<String> strings) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if(defectAnalyze1!=null){
                System.out.print("已经有数据记录了 ");
                if(defectDataList==null || defectDataList.size()==0){
                    if(strings.size()>0){
                        for(int k=0;k<strings.size();k++){
                            ps.setString(k+1, strings.get(k));
                        }
                        ps.setInt(strings.size()+1, defectAnalyze1.getId());
                        int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
                        if (row > 0) {
                            System.out.print("成功添加了 " + row + " 条数据！！！");
                        }
                    }
                }

            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
            return false;
        }

    }
    public static boolean addDefectFileName(String sql, DefectFileName defectFileName) {
        try {
            File file=null;
            DBconn.init();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, defectFileName.getFileName());//对sql语句中的第1个参数赋值
            int row = ps.executeUpdate();//执行更新操作，返回所影响的行数
            if (row > 0) {
                System.out.print("成功添加了 " + row + " 条数据！！！");
            }
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
            return false;
        }

    }

    public static boolean addDefectAnalyzeData(List<List<String>> lists, InputStream fis) {
        try {
            DefectAnalyze defectAnalyze1=null;
            List<DefectData> defectDataList = null;
            DefectAnalyze defectAnalyze =new DefectAnalyze();
            DefectAnalyzeDao defectAnalyzeDao =new DefectAnalyzeDaoImpl();
            for(int i=0;i<lists.size();i++){
                List<String> strings = lists.get(i);
                if(i<5){
                    for(int j=0;j<strings.size();j++){
                        String s = strings.get(j);
                        if(!TextUtils.isEmpty(s)){
                            if(s.equals("产品名称")){
                                defectAnalyze.setProductName(strings.get(j+1));
                            }
                            if(s.equals("操作员")){
                                defectAnalyze.setOperator(strings.get(j+1));
                            }
                            if(s.equals("检测参数")){
                                defectAnalyze.setCheckParameters(strings.get(j+1));
                            }
                            if(s.equals("缺陷总数")){
                                defectAnalyze.setDefectTotal(strings.get(j+1));
                            }
                            if(s.equals("合格")){
                                defectAnalyze.setQualifiedNumber(strings.get(j+1));
                            }
                            if(s.equals("不合格")){
                                defectAnalyze.setNotQualifiedNumber(strings.get(j+1));
                            }
                            if(s.equals("背景缺陷数量")){
                                defectAnalyze.setDefectBackground(strings.get(j+1));
                            }
                            if(s.equals("纹理缺陷数量")){
                                defectAnalyze.setDefectTexture(strings.get(j+1));
                            }
                            if(s.equals("检验时间")){
                                String time0=strings.get(j+1);
                                String time1 = Utils.timeFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),time0);
                                defectAnalyze.setCheckTime(time1);
                            }
                        }
                    }
                    if(i==4){
                        if(defectAnalyzeDao.isDefectAnalyze(defectAnalyze.getProductName(), defectAnalyze.getCheckTime())){
                            System.out.print("已经有了这条记录 " );
                        }else {

                            defectAnalyzeDao.add(defectAnalyze,fis);
                        }

                    }
                }else if(i==5){
                    defectAnalyze1 = defectAnalyzeDao.selectDefectAnalyzeSql(defectAnalyze.getProductName(), defectAnalyze.getCheckTime());
                    DefectDataDao defectDataDao=new DefectDataDaoImpl();
                    defectDataList = defectDataDao.findDefectDataId(defectAnalyze1.getId());
                }else if(i>5) {
                    DefectDataDao defectDataDao=new DefectDataDaoImpl();
                    defectDataDao.add(defectAnalyze1,defectDataList,strings);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
            return false;
        }
    }

}


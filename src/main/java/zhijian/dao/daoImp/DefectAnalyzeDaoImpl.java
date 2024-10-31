package zhijian.dao.daoImp;


import zhijian.dao.DefectAnalyzeDao;
import zhijian.entity.DefectAnalyze;
import zhijian.util.DBconn;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DefectAnalyzeDaoImpl implements DefectAnalyzeDao {

    public boolean add(DefectAnalyze da, InputStream fis) {
        DBconn.init();
        String sql="insert into defectanalyze(productName,operator,checkParameters,defectTotal,qualifiedNumber,notQualifiedNumber,defectBackground,defectTexture,checkTime,fileData) values(?,?,?,?,?,?,?,?,?,?)";
        return DBconn.addDefectAnalyzeData(sql,da,fis);
    }

    public  boolean isDefectAnalyze(String productName, String checkTime) {
        boolean flag = false;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from defectanalyze where productName='" + productName + "' and checkTime='" + checkTime + "'");
            while (rs.next()) {
                if (rs.getString("productName").equals(productName) && rs.getString("checkTime").equals(checkTime)) {
                    flag = true;
                }
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public  DefectAnalyze selectDefectAnalyzeSql(String productName,String checkTime) {
        DefectAnalyze defectAnalyze=null;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from defectanalyze where productName='" + productName + "' and checkTime='" + checkTime + "'");
            while (rs.next()) {
                defectAnalyze = new DefectAnalyze();
                defectAnalyze.setId(rs.getInt("id"));
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            e.printStackTrace();
        }

        return defectAnalyze;
    }

    public List<DefectAnalyze> getAll() {
        List<DefectAnalyze> list = new ArrayList<DefectAnalyze>();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from defectanalyze");
            while (rs.next()) {
                DefectAnalyze defectAnalyze = new DefectAnalyze();
                defectAnalyze.setId(rs.getInt("id"));
                defectAnalyze.setCheckParameters(rs.getString("checkParameters"));
                defectAnalyze.setCheckTime(rs.getString("checkTime"));
                defectAnalyze.setDefectBackground(rs.getString("defectBackground"));
                defectAnalyze.setOperator(rs.getString("operator"));
                defectAnalyze.setQualifiedNumber(rs.getString("qualifiedNumber"));
                defectAnalyze.setProductName(rs.getString("productName"));
                defectAnalyze.setNotQualifiedNumber(rs.getString("notQualifiedNumber"));
                defectAnalyze.setDefectTotal(rs.getString("defectTotal"));
                defectAnalyze.setDefectTexture(rs.getString("defectTexture"));
                list.add(defectAnalyze);
            }
            DBconn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<DefectAnalyze> getFindDefectAnalyze(String start_time,String end_time) {
        DBconn.init();
        String sql = "SELECT * FROM defectanalyze WHERE checkTime BETWEEN '" + start_time + "' AND '" + end_time + "'";
        List<DefectAnalyze> lists = new ArrayList<DefectAnalyze>();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql(sql);
            while (rs.next()) {
                DefectAnalyze defectAnalyze = new DefectAnalyze();
                defectAnalyze.setId(rs.getInt("id"));
                defectAnalyze.setCheckParameters(rs.getString("checkParameters"));
                defectAnalyze.setCheckTime(rs.getString("checkTime"));
                defectAnalyze.setDefectBackground(rs.getString("defectBackground"));
                defectAnalyze.setOperator(rs.getString("operator"));
                defectAnalyze.setQualifiedNumber(rs.getString("qualifiedNumber"));
                defectAnalyze.setProductName(rs.getString("productName"));
                defectAnalyze.setNotQualifiedNumber(rs.getString("notQualifiedNumber"));
                defectAnalyze.setDefectTotal(rs.getString("defectTotal"));
                defectAnalyze.setDefectTexture(rs.getString("defectTexture"));
                lists.add(defectAnalyze);
            }
            DBconn.closeConn();
            return lists;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean delete(int id) {
        boolean flag = false;
        DBconn.init();
        String sql = "delete  from defect_analyze where id=" + id;
        int i = DBconn.addUpdDel(sql);
        if (i > 0) {
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }

}


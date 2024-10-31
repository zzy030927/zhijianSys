package zhijian.dao.daoImp;


import zhijian.dao.DefectDataDao;
import zhijian.entity.DefectAnalyze;
import zhijian.entity.DefectData;
import zhijian.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DefectDataDaoImpl implements DefectDataDao {
    public boolean add(DefectAnalyze defectAnalyze1, List<DefectData> defectDataList , List<String> strings) {
        DBconn.init();
        String sql="insert into defectdata(defectId,operator,productName,defectType,coordinate,area,state,defectAnalyze_id) values(?,?,?,?,?,?,?,?)";
        return DBconn.addDefectData(sql,defectAnalyze1,defectDataList,strings);
    }

    public  List<DefectData> findDefectDataId(int defectanalyzeId) {
        List<DefectData> list = null;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from defectdata where defectAnalyze_id='" + defectanalyzeId + "'");
            list=new ArrayList<>();
            while (rs.next()) {
                DefectData defectData = new DefectData();
                defectData.setId(rs.getInt("id"));
                defectData.setDefectId(rs.getString("defectId"));
                defectData.setOperator(rs.getString("operator"));
                defectData.setProductName(rs.getString("productName"));
                defectData.setDefectType(rs.getString("defectType"));
                defectData.setCoordinate(rs.getString("coordinate"));
                defectData.setArea(rs.getString("area"));
                defectData.setState(rs.getString("state"));
                list.add(defectData);
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            e.printStackTrace();
        }

        return list;
    }

    public List<DefectData> getAll() {
        List<DefectData> list = new ArrayList<DefectData>();
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from defect_data");
            while (rs.next()) {
                DefectData defectData = new DefectData();
                defectData.setId(rs.getInt("id"));
                defectData.setProductName(rs.getString("productName"));
                defectData.setOperator(rs.getString("operator"));
                defectData.setDefectId(rs.getString("defectId"));
                defectData.setDefectType(rs.getString("defectType"));
                defectData.setArea(rs.getString("area"));
                defectData.setState(rs.getString("state"));
                defectData.setCoordinate(rs.getString("coordinate"));
                list.add(defectData);
            }
            DBconn.closeConn();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        boolean flag = false;
        DBconn.init();
        String sql = "delete  from user where id=" + id;
        int i = DBconn.addUpdDel(sql);
        if (i > 0) {
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }

}


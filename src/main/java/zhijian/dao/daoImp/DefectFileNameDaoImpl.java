package zhijian.dao.daoImp;


import zhijian.dao.DefectFileNameDao;
import zhijian.entity.DefectFileName;
import zhijian.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DefectFileNameDaoImpl implements DefectFileNameDao {

    @Override
    public boolean add(DefectFileName dd) {
        String sql="insert into defectfilename(fileName,fileData) values(?,?)";
        if(findFileName(dd.getFileName())){
            return true;
        }else {
            return DBconn.addDefectFileName(sql,dd);

        }

    }

    @Override
    public List<DefectFileName> getAll() {
        return null;
    }


    @Override
    public boolean findFileName(String fileName) {
        boolean flag = false;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from defectfilename where fileName='" + fileName+ "'");
            while (rs.next()) {
                if (rs.getString("fileName").equals(fileName) ) {
                    flag = true;
                }
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return flag;
    }

}


package zhijian.dao.daoImp;


import zhijian.dao.FolderPathDao;
import zhijian.util.DBconn;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FolderPathDaoImpl implements FolderPathDao {
    @Override
    public boolean add(String path) {
        DBconn.init();
        String sql="insert into folderpath(path) values(?)";
        return DBconn.addFolderPath(sql,path);
    }

    @Override
    public String getLastRecordPath() {
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("SELECT * FROM defectanalyze ORDER BY id DESC LIMIT 1");
            while (rs.next()) {
                return rs.getString("path");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBconn.closeConn();
        }
        return null;
    }

}


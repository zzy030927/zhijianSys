package zhijian.dao;


import zhijian.entity.DefectAnalyze;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public interface DefectAnalyzeDao {

    public boolean add(DefectAnalyze da, InputStream fis);

    public List<DefectAnalyze> getAll();

    public List<DefectAnalyze> getFindDefectAnalyze(String start_time,String end_time);

    public boolean delete(int id);
    public  boolean isDefectAnalyze(String productName, String checkTime);
    public  DefectAnalyze selectDefectAnalyzeSql(String productName,String checkTime);

}



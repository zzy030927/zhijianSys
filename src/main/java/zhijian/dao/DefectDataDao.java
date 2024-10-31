package zhijian.dao;


import zhijian.entity.DefectAnalyze;
import zhijian.entity.DefectData;

import java.util.List;

public interface DefectDataDao {

    public boolean add(DefectAnalyze defectAnalyze1, List<DefectData> defectDataList , List<String> strings);//添加

    public List<DefectData> getAll();//返回信息集合

    public boolean delete(int id);//根据id删除

    public  List<DefectData> findDefectDataId(int defectanalyzeId);//缺陷分析表id
}



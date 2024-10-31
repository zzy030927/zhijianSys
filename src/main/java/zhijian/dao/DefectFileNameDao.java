package zhijian.dao;


import zhijian.entity.DefectData;
import zhijian.entity.DefectFileName;

import java.io.FileInputStream;
import java.util.List;

public interface DefectFileNameDao {

    public boolean add(DefectFileName dd);//添加

    public List<DefectFileName> getAll();//返回信息集合
    public boolean findFileName(String fileName); //查询


}



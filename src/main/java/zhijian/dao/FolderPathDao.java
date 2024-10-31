package zhijian.dao;


import zhijian.entity.User;

import java.util.List;

public interface FolderPathDao {
    public boolean add(String path);
    public String getLastRecordPath();
}



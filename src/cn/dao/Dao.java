package cn.dao;

import java.util.List;

public interface Dao {

    //查询数据库里所有文件名
    public List<String> findAll();
    //往数据库添加数据如果重名返回false
    public void addFile(String name);
    //查询是否有重复文件存在返回true不存在返回false
    public Boolean existFile(String name);
}

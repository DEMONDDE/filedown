package cn.service;

import java.util.List;

public interface Service {

    //查询数据库里所有文件名
    public List<String> findAll();

    //往数据库添加数据如果重名返回true
    public Boolean addFile(String name);
    //test
    public void cc();

}

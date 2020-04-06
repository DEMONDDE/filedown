package cn.service.Impl;

import cn.dao.Dao;
import cn.dao.Impl.DaoImpl;
import cn.service.Service;

import java.util.List;

public class ServiceImpl implements Service {

    private Dao dao = new DaoImpl();
    @Override
    public List<String> findAll() {
        return dao.findAll();
    }

    @Override
    public Boolean addFile(String name) {
        if(dao.existFile(name)){
            return true;
        }
        dao.addFile(name);
        return false;
    }
}

package cn.dao.Impl;

import cn.dao.Dao;
import cn.util.JDBCUtils;

import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class DaoImpl implements Dao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<String> findAll() {
        String sql = "select name from filename";
        List<String> list = null;
        list = jdbcTemplate.queryForList(sql, String.class);
        return list;
    }

    @Override
    public void addFile(String name) {

        String sql = "INSERT INTO filename (NAME) VALUE(?)";
        jdbcTemplate.update(sql,name);
    }

    @Override
    public Boolean existFile(String name) {
        String sql = "select name from filename where name = ?";
        //由于结果为空会保存所以用try catch解决
        try {
            String file = jdbcTemplate.queryForObject(sql, String.class, name);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}

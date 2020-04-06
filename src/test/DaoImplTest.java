package test;

import cn.dao.Impl.DaoImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 测试类对DaoImpl进行测试
 */
class DaoImplTest {

    private DaoImpl dao = new DaoImpl();

    @org.junit.jupiter.api.Test
    void findAll() {
        List<String> list = dao.findAll();
        System.out.println(list);
    }

    @Test
    void addFile() {
        dao.addFile("aaa");
    }

    @org.junit.jupiter.api.Test
    void existFile(){
        //对数据不存在查询
        Boolean b = dao.existFile("123");
        Assert.assertFalse(b);//断言检测是否为false
        //对数据存在的进行查询
        Boolean c = dao.existFile("text");
        Assert.assertTrue(c);//断言检测是否为true
    }


}
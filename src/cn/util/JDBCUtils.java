package cn.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接池
 */
public class JDBCUtils {
    private static DataSource ds;

    static {
        try {
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static DataSource getDataSource(){
        return  ds;
    }

    public static void close(Statement s, Connection c){
        close(s, c, null);
    }


    public static void close(Statement s, Connection c, ResultSet r){
        if(s != null){
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(c != null){
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(r != null){
            try {
                r.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

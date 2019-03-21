package Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类用于获取连接对象
 */
public class JDBCUtil {
    // 静态对象用于在初始化时加载
    private static DataSource ds;

    /**
     * 读取配置文件并加载
     */
    static {
        Properties pro = new Properties();
        InputStream resourceAsStream = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return connection
     * @throws SQLException
     */
    public static Connection getconnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获取数据库连接池对象
     * @return ds
     */
    public static DataSource getdatasource(){
        return ds;
    }
}

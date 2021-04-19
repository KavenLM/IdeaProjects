package cn.itcast.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
   jdbc工具类
 */
public class JDBCUtils {

    private static DataSource ds;

    /*
     *初始化资源
     */
    static{
        //1.加载配置文件
        Properties pro = new Properties();
        InputStream rs = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(rs);
            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取Connection对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }
}

package tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {

    private static DruidDataSource dds;

    static {
        dds = new DruidDataSource();
        dds.setUrl("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        dds.setUsername("root");
        dds.setPassword("root");

        dds.setInitialSize(3);
        dds.setMaxActive(5);


    }

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        Connection conn = dds.getConnection();
        System.out.println("连接:" + conn);
        //获取连接对象

        return conn;
    }
}

package cn.tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        dds.setUsername("root");
        dds.setPassword("root");

        dds.setInitialSize(3);
        dds.setMaxActive(5);

        Connection conn = dds.getConnection();
        System.out.println("连接:" + conn);
    }
}

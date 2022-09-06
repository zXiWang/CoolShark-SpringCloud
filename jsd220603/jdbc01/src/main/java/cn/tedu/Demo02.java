package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //参考Demo01  删除jdbct1表
        Class.forName("com.mysql.cj.jdbc.Driver");
        //1. 获取数据库连接  异常抛出
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false",
                "root", "root");
        System.out.println("连接对象:" + conn);
        //2. 创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //3. 执行SQL语句 execute=执行
        s.execute("drop table jdbct1");
        //4. 关闭资源
        conn.close();
        System.out.println("执行完成!");
    }
}


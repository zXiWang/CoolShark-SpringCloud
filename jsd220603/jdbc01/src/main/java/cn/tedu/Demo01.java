package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false",
                "root", "root");
        System.out.println("连接对象:" + conn);
        //2. 创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //3. 执行SQL语句 execute=执行
        s.execute("create table jdbct1(name varchar(20),age int)");
        //4. 关闭资源
        conn.close();
        System.out.println("执行完成!");
    }
}

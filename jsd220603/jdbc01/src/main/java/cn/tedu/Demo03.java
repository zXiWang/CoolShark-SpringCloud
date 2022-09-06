package cn.tedu;

import java.sql.*;

public class Demo03 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //获取连接对象
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false",
                "root", "root");
        //创建执行SQL语句对象
        Statement s = conn.createStatement();
        //执行插入数据的SQL语句
//        s.executeUpdate("insert into emp(name) values('Tom')");
        //执行修改数据的SQL语句
        // s.executeUpdate("update emp set name='Jerry' where name='Tom'");
        //执行删除数据的SQL语句
//        s.executeUpdate("delete from emp where name='Jerry'");
        //执行查询数据的SQL语句
        ResultSet rs = s.executeQuery("select * from emp");
        //遍历结果集对象 rs.next()询问是否有下一条数据有返回true否则返回false 同时游标往下移动
        while (rs.next()) {
            String name = rs.getString("name");
            double sal = rs.getDouble("sal");
            System.out.println(name + ":" + sal);
        }
        conn.close();
        System.out.println("执行完成!");
    }
}

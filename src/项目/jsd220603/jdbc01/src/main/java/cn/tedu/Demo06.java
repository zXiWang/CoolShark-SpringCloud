package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo06 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.nextLine();
        System.out.println("请输入密码");
        String password = scan.nextLine();
        System.out.println("请输入昵称");
        String nickname = scan.nextLine();

        try(Connection conn = DBUtils.getConn()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id from user where username='" + username + "'");
            if(rs.next()){
                System.out.println("用户已存在!");
                return;
            }
            stmt.executeUpdate("insert into user(id,username,password,nickname) values(null,'"+username+"','"+password+"','"+nickname+"')");
        }catch (SQLException e) {
            System.out.println("注册失败!");
            return;
        }
        System.out.println("注册成功!");
    }
}

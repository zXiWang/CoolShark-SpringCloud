package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.nextLine();
        System.out.println("请输入密码");
        String password = scan.nextLine();
        System.out.println("请输入昵称");
        String nickname = scan.nextLine();

        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("select id from user where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("用户已存在!");
                return;
            }
            ps = conn.prepareStatement("insert into user(id,username,password,nickname) values(null,?,?,?)");
            ps.setString(1, username);
            ps.setString(2,password);
            ps.setString(3,nickname);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("注册失败!");
            return;
        }
        System.out.println("注册成功!");
    }
}

package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo08 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.nextLine();
        System.out.println("请输入密码");
        String password = scan.nextLine();

        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("select password from user where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (password.equals(rs.getString(1))) {
                    System.out.println("登陆成功!");
                } else {
                    System.out.println("用户名或密码错误!");
                }
                return;

            } else {
                System.out.println("用户名不存在!");
            }
        } catch (SQLException e) {
            System.out.println("登录失败!");
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("用户名或密码错误!");
    }
}

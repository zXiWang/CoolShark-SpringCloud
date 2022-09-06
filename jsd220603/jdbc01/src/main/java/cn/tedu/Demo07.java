package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo07 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.nextLine();
        System.out.println("请输入密码");
        String password = scan.nextLine();


        try (Connection conn = DBUtils.getConn()) {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select id from user where username='" + username + "' and password='" + password + "'");
            PreparedStatement ps = conn.prepareStatement("select count(*) from user where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("登陆成功!");
                return;
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

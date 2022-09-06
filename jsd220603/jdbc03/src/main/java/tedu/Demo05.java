package tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        try (Connection conn = DBUtils.getConn()
        ) {
            ResultSet rs = conn.createStatement().executeQuery(
                    "select * from car");
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String type = rs.getString("type");
                String price = rs.getString("price");
                System.out.println("id\t" + "title\t\t" + "type\t" + "price");
                System.out.println(id+"\t" + title+"\t" + type+"\t" + price);

            }
        } catch (SQLException e) {
            System.out.println("无法查询!");
            return;
        }
        System.out.println("查询完成!");
    }
}

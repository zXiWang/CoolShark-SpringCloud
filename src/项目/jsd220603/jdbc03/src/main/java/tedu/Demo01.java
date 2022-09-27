package tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        try (Connection conn = DBUtils.getConn()) {
            Statement s = conn.createStatement();
            s.executeUpdate("CREATE TABLE " +
                    "car(id INTEGER PRIMARY KEY,title varchar(255),type varchar(255),price integer)");
            ResultSet rs = s.executeQuery("show create table car");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("表已经存在");
            return;
        }

        System.out.println("表创建完成");
    }
}

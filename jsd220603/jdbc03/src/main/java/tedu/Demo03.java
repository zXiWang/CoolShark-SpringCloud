package tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo03 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try (Connection conn = DBUtils.getConn()) {
            Statement s = conn.createStatement();
            s.executeUpdate("delete from car where id=(select id from(select max(id) from car) a)");
        } catch (SQLException e) {
            System.out.println("表中无数据");
            return;
        }

        System.out.println("删除成功");
    }
}

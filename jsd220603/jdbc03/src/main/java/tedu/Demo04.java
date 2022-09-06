package tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo04 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try (Connection conn = DBUtils.getConn()) {
            Statement s = conn.createStatement();
            s.executeUpdate("update car set" +
                    " title='奔驰S500',type='轿车',price=9.9 " +
                    "where id=3");
        } catch (SQLException e) {
            System.out.println("未找到该车辆");
            return;
        }
        System.out.println("修改成功");
    }
}

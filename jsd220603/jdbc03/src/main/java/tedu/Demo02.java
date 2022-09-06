package tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try (Connection conn = DBUtils.getConn()) {
            Statement s = conn.createStatement();
            s.executeUpdate("insert into car " +
                    "value (1,'牛逼牛逼车','跑车',55566666)," +
                    "(2,'垃圾垃圾车','自行车',5)," +
                    "(3,'中规中矩车','轿车',555555)");
        } catch (SQLException e) {
            System.out.println("数据重复!");
            return;
        }
        System.out.println("添加成功");
    }
}


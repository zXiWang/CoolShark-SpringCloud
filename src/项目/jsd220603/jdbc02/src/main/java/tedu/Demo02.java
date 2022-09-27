package tedu;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) {
        try (Connection conn = DBUtils.getConn()){
            Statement s = conn.createStatement();
            s.executeUpdate("insert into hero values(null,'孙悟空',500),(null,'猪八戒',300)");
            System.out.println("插入数据完成");
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}

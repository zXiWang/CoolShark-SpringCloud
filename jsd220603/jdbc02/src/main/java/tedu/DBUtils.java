package tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");        //获取连接对象
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false",
                "root","root");
        return conn;
    }
}

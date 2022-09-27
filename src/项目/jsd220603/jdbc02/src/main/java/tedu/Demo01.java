package tedu;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        try (Connection conn = DBUtils.getConn()){
            Statement s = conn.createStatement();
            s.execute("create table hero(id int primary key auto_increment,name varchar(20),age int)");
            System.out.println("创建完成!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

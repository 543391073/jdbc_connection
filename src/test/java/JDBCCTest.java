import org.junit.Test;

import java.sql.*;

/**
 * Created by chengh on 2018/12/1.
 * 使用 JDBC连接数据库
 */
public class JDBCCTest {
    @Test
    public void test(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/xz_1";
        String userName = "root";
        String password = "123456";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //加载驱动程序
            Class.forName(driver);

            //连接数据库
            connection = DriverManager.getConnection(url, userName, password);

            statement = connection.createStatement();

            //结果集
            resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()){
                System.out.println(resultSet.getInt("id"));
                System.out.println(resultSet.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动异常");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库链接异常");
            e.printStackTrace();
        }finally {

        }
    }
}

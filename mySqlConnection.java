package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class mySqlConnection {
    public static Connection getConnection() throws Exception {

        Connection conn=null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "oose2";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
        try {
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

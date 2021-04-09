package kz.sitedev.Forum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection init()
            throws SQLException, ClassNotFoundException
    {
        String dbDriver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/forum";
        String username = "root";
        String password = "root";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(
                url,
                username,
                password);
        return con;
    }
}

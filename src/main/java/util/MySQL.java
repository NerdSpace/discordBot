package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    public static Connection con;

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + STATIC.DB_IP + "/" + STATIC.DB_NAME + "?autoReconnect=true", STATIC.DB_USER, STATIC.DB_PWD);
                System.out.println("MySQL Verbunden");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println("MySQL Verbindung getrennt");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static void createTable() {
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS denyedUsers (USERID VARCHAR(100) UNIQUE, allowed TinyInt(1))").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

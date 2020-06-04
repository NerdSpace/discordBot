package handlers;

import util.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL_Handler {

    public static int getUser(String USERID) {
        try {
            PreparedStatement st = MySQL.con.prepareStatement("SELECT allowed FROM denyedUsers WHERE USERID = ?");
            st.setString(1, USERID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("allowed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public static void createEntry(String USERID) {
        try {
            PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO denyedUsers (USERID, allowed) VALUES (?,?)");
            st.setString(1, USERID);
            st.setInt(2, 1);
            st.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void updateStatus(String USERID, Integer allowed) {
        try {
            PreparedStatement st = MySQL.con.prepareStatement("UPDATE denyedUsers set allowed = ? WHERE USERID = ?");
            st.setString(2, USERID);
            st.setInt(1, allowed);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package IT2B_GALLERA_MIDTERM.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:hotel.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS reservations ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "guest_name TEXT NOT NULL, "
                + "room_type TEXT NOT NULL, "
                + "nightly_rate REAL NOT NULL, "
                + "payment TEXT NOT NULL)";
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }
}
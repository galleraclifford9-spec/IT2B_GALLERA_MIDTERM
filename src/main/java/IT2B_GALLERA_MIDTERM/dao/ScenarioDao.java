package IT2B_GALLERA_MIDTERM.dao;

import IT2B_GALLERA_MIDTERM.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public boolean addReservation(Reservation r) {
        String sql = "INSERT INTO reservations (guest_name, room_type, nightly_rate, payment) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getGuestName());
            ps.setString(2, r.getRoomType());
            ps.setDouble(3, r.getNightlyRate());
            ps.setString(4, r.getPayment());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            return false;
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT id, guest_name, room_type, nightly_rate, payment FROM reservations ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Reservation(
                        rs.getInt("id"),
                        rs.getString("guest_name"),
                        rs.getString("room_type"),
                        rs.getDouble("nightly_rate"),
                        rs.getString("payment")));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return list;
    }
}
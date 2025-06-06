package mg.p16.java.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.Reservation;

public class ReservationDAO {

    public void addReservation(Reservation reservation) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO Reservation (id_vol, au_nom, CIN, date_reservation, statut, prix_total) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, reservation.getIdVol());
        ps.setString(2, reservation.getAuNom());
        ps.setInt(3, reservation.getCIN());
        ps.setTimestamp(4, new Timestamp(reservation.getDateReservation().getTime()));
        ps.setString(5, reservation.getStatut());
        ps.setDouble(6, reservation.getPrixTotal());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            reservation.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        conn.close();
    }

    public Reservation getReservationById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Reservation WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Reservation reservation = null;
        if(rs.next()){
            reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setIdVol(rs.getInt("id_vol"));
            reservation.setAuNom(rs.getString("au_nom"));
            reservation.setCIN(rs.getInt("CIN"));
            reservation.setDateReservation(rs.getTimestamp("date_reservation"));
            reservation.setStatut(rs.getString("statut"));
            reservation.setPrixTotal(rs.getDouble("prix_total"));
        }
        rs.close();
        ps.close();
        conn.close();
        return reservation;
    }

    public List<Reservation> getAllReservations() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Reservation";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setIdVol(rs.getInt("id_vol"));
            reservation.setAuNom(rs.getString("au_nom"));
            reservation.setCIN(rs.getInt("CIN"));
            reservation.setDateReservation(rs.getTimestamp("date_reservation"));
            reservation.setStatut(rs.getString("statut"));
            reservation.setPrixTotal(rs.getDouble("prix_total"));
            reservations.add(reservation);
        }
        rs.close();
        st.close();
        conn.close();
        return reservations;
    }
    
    public void updateReservation(Reservation reservation) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "UPDATE Reservation SET statut = ?, prix_total = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, reservation.getStatut());
        ps.setDouble(2, reservation.getPrixTotal());
        ps.setInt(3, reservation.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}

package mg.p16.java.dao;

import java.sql.*;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.ReservationPersonne;

public class ReservationPersonneDAO {

    public void addReservationPersonne(ReservationPersonne rp) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO Reservation_personne (id_reservation, id_categorie_peronne, total) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, rp.getIdReservation());
        ps.setInt(2, rp.getIdCategoriePersonne());
        ps.setInt(3, rp.getTotal());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            rp.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        conn.close();
    }
}

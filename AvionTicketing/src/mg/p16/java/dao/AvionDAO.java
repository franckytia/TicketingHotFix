package mg.p16.java.dao;
import java.sql.*;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.Avion;

public class AvionDAO {

    public void addAvion(Avion avion) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO Avion (modele, capacite, date_fabrication) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, avion.getModele());
        ps.setInt(2, avion.getCapacite());
        ps.setDate(3, new java.sql.Date(avion.getDateFabrication().getTime()));
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            avion.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        conn.close();
    }

    public Avion getAvionById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Avion WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Avion avion = null;
        if(rs.next()){
            avion = new Avion();
            avion.setId(rs.getInt("id"));
            avion.setModele(rs.getString("modele"));
            avion.setCapacite(rs.getInt("capacite"));
            avion.setDateFabrication(rs.getDate("date_fabrication"));
        }
        rs.close();
        ps.close();
        conn.close();
        return avion;
    }
}

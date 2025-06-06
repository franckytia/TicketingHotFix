package mg.p16.java.dao;
import java.sql.*;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.TypeSiege;

public class TypeSiegeDAO {

    public void addTypeSiege(TypeSiege type) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO TypeSiege (nom, coefficient_prix) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, type.getNom());
        ps.setDouble(2, type.getCoefficientPrix());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            type.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
    }

    public TypeSiege getTypeSiegeById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM TypeSiege WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        TypeSiege type = null;
        if(rs.next()){
            type = new TypeSiege();
            type.setId(rs.getInt("id"));
            type.setNom(rs.getString("nom"));
            type.setCoefficientPrix(rs.getDouble("coefficient_prix"));
        }
        rs.close();
        ps.close();
        return type;
    }
}

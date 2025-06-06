package mg.p16.java.dao;
import java.sql.*;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.Ville;
import java.util.List;
import java.util.List;
import java.util.*;
public class VilleDAO {

    public void addVille(Ville ville) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO Ville (nom) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, ville.getNom());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            ville.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
    }

    public Ville getVilleById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Ville WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Ville ville = null;
        if(rs.next()){
            ville = new Ville();
            ville.setId(rs.getInt("id"));
            ville.setNom(rs.getString("nom"));
        }
        rs.close();
        ps.close();
        conn.close();
        return ville;
    }
    public List<Ville> getAllVilles() throws Exception {
        List<Ville> villes = new ArrayList<>();
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Ville ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Ville ville = new Ville();
            ville.setId(rs.getInt("id"));
            ville.setNom(rs.getString("nom"));
            villes.add(ville);
        }
        rs.close();
        ps.close();
        conn.close();
        return villes;  
    }
}

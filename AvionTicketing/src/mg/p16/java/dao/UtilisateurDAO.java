package mg.p16.java.dao;
import java.sql.*;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.Utilisateur;

public class UtilisateurDAO {

    public void addUtilisateur(Utilisateur Utilisateur) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO \"Utilisateur\" (nom, prenom, email, password, role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, Utilisateur.getNom());
        ps.setString(2, Utilisateur.getPrenom());
        ps.setString(3, Utilisateur.getEmail());
        ps.setString(4, Utilisateur.getPassword());
        ps.setString(5, Utilisateur.getRole());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            Utilisateur.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
    }

    public Utilisateur getUtilisateurById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM \"Utilisateur\" WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Utilisateur Utilisateur = null;
        if(rs.next()){
            Utilisateur = new Utilisateur();
            Utilisateur.setId(rs.getInt("id"));
            Utilisateur.setNom(rs.getString("nom"));
            Utilisateur.setPrenom(rs.getString("prenom"));
            Utilisateur.setEmail(rs.getString("email"));
            Utilisateur.setPassword(rs.getString("password"));
            Utilisateur.setRole(rs.getString("role"));
        }
        rs.close();
        ps.close();
        return Utilisateur;
    }

    public Utilisateur authenticate(String email, String password) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM utilisateur WHERE email = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        Utilisateur utilisateur = null;
        if(rs.next()){
            utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setPassword(rs.getString("password"));
            utilisateur.setRole(rs.getString("role"));
        }
        rs.close();
        ps.close();
        return utilisateur;
    }
}

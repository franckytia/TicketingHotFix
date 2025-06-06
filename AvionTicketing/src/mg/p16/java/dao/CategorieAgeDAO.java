package mg.p16.java.dao;
import java.sql.*;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieAgeDAO {
    // Récupérer toutes les catégories d'âge
    public List<CategorieAge> getAllCategories() throws SQLException {
        List<CategorieAge> categories = new ArrayList<>();
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM categorie_age";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            CategorieAge categorie = new CategorieAge(
                rs.getInt("id"),
                rs.getInt("age_max"),
                rs.getInt("age_min"),
                rs.getString("description"),
                rs.getDouble("coefficient")
            );
            categories.add(categorie);
        }
        
        rs.close();
        ps.close();
        return categories;
    }

    // Récupérer une catégorie d'âge par ID
    public CategorieAge getCategorieById(int id) throws SQLException {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM categorie_age WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        CategorieAge categorie = null;
        if (rs.next()) {
            categorie = new CategorieAge(
                rs.getInt("id"),
                rs.getInt("age_max"),
                rs.getInt("age_min"),
                rs.getString("description"),
                rs.getDouble("coefficient")
            );
        }
        
        rs.close();
        ps.close();
        return categorie;
    }

    // Ajouter une nouvelle catégorie d'âge
    public void addCategorie(CategorieAge categorie) throws SQLException {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO categorie_age (age_max, age_min, description, coefficient) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, categorie.getAgeMax());
        ps.setInt(2, categorie.getAgeMin());
        ps.setString(3, categorie.getDescription());
        ps.setDouble(4, categorie.getCoefficient());
        ps.executeUpdate();
        ps.close();
    }

    // Mettre à jour une catégorie d'âge
    public void updateCategorie(CategorieAge categorie) throws SQLException {
        Connection conn = Connexion.getConnection();
        String sql = "UPDATE categorie_age SET age_max = ?, age_min = ?, description = ?, coefficient = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, categorie.getAgeMax());
        ps.setInt(2, categorie.getAgeMin());
        ps.setString(3, categorie.getDescription());
        ps.setDouble(4, categorie.getCoefficient());
        ps.setInt(5, categorie.getId());
        ps.executeUpdate();
        ps.close();
    }

    // Supprimer une catégorie d'âge
    public void deleteCategorie(int id) throws SQLException {    
        Connection conn = Connexion.getConnection();
        String sql = "DELETE FROM categorie_age WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }
}

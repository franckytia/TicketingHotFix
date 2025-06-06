package mg.p16.java.dao;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.Promotion;

public class PromotionDAO {

    public void addPromotion(Promotion promotion) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO promotion (id_vol, id_type_siege, pourcentage_reduction, nombre_places_promo) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, promotion.getIdVol());
        ps.setInt(2, promotion.getIdTypeSiege());
        ps.setDouble(3, promotion.getPourcentageReduction());
        ps.setInt(4, promotion.getNombrePlacesPromo());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            promotion.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        conn.close();
    }


    public Promotion getPromotionById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Promotion WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Promotion promotion = null;
        if(rs.next()){
            promotion = new Promotion();
            promotion.setId(rs.getInt("id"));
            promotion.setIdVol(rs.getInt("id_vol"));
            promotion.setIdTypeSiege(rs.getInt("id_type_siege"));
            promotion.setPourcentageReduction(rs.getDouble("pourcentage_reduction"));
            promotion.setNombrePlacesPromo(rs.getInt("nombre_places_promo"));
        }
        rs.close();
        ps.close();
        return promotion;
    }

    public void deleteById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "DELETE FROM Promotion WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();

    }
    public List<Promotion> findAll() throws Exception {
        Connection conn = Connexion.getConnection();
        List<Promotion> list = new ArrayList<>();
        String sql = "SELECT * FROM Promotion";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Promotion promotion = null;
        while(rs.next()){
            promotion = new Promotion();
            promotion.setId(rs.getInt("id"));
            promotion.setIdVol(rs.getInt("id_vol"));
            promotion.setIdTypeSiege(rs.getInt("id_type_siege"));
            promotion.setPourcentageReduction(rs.getDouble("pourcentage_reduction"));
            promotion.setNombrePlacesPromo(rs.getInt("nombre_places_promo"));
            list.add(promotion);
        }
        rs.close();
        ps.close();
        conn.close();
        return list;
    }
}

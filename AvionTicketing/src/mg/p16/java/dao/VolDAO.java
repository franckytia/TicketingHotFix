package mg.p16.java.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mg.p16.java.connexion.Connexion;
import mg.p16.java.model.Vol;

public class VolDAO {

   public void addVol(Vol vol) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "INSERT INTO Vol (numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_base) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, vol.getNumeroVol());
        ps.setInt(2, vol.getIdAvion());
        ps.setInt(3, vol.getIdVilleDepart());
        ps.setInt(4, vol.getIdVilleArrivee());
        ps.setTimestamp(5, new Timestamp(vol.getDateDepart().getTime()));
        ps.setTimestamp(6, new Timestamp(vol.getDateArrivee().getTime()));
        ps.setDouble(7, vol.getPrixBase());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()) {
            vol.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        conn.close();
    }

    public Vol getVolById(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Vol WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Vol vol = null;
        if(rs.next()){
            vol = new Vol();
            vol.setId(rs.getInt("id"));
            vol.setNumeroVol(rs.getString("numero_vol"));
            vol.setIdAvion(rs.getInt("id_avion"));
            vol.setIdVilleDepart(rs.getInt("id_ville_depart"));
            vol.setIdVilleArrivee(rs.getInt("id_ville_arrivee"));
            vol.setDateDepart(rs.getTimestamp("date_depart"));
            vol.setDateArrivee(rs.getTimestamp("date_arrivee"));
            vol.setPrixBase(rs.getDouble("prix_base"));
        }
        rs.close();
        ps.close();
        conn.close();

        return vol;
    }

    public List<Vol> getAllVols() throws Exception {
        List<Vol> vols = new ArrayList<>();
        Connection conn = Connexion.getConnection();
        String sql = "SELECT * FROM Vol";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Vol vol = new Vol();
            vol.setId(rs.getInt("id"));
            vol.setNumeroVol(rs.getString("numero_vol"));
            vol.setIdAvion(rs.getInt("id_avion"));
            vol.setIdVilleDepart(rs.getInt("id_ville_depart"));
            vol.setIdVilleArrivee(rs.getInt("id_ville_arrivee"));
            vol.setDateDepart(rs.getTimestamp("date_depart"));
            vol.setDateArrivee(rs.getTimestamp("date_arrivee"));
            vol.setPrixBase(rs.getDouble("prix_base"));
            vols.add(vol);
        }
        rs.close();
        st.close();
        conn.close();

        return vols;
    }

    public void updateVol(Vol vol) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "UPDATE Vol SET numero_vol = ?, id_avion = ?, id_ville_depart = ?, id_ville_arrivee = ?, date_depart = ?, date_arrivee = ?, prix_base = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, vol.getNumeroVol());
        ps.setInt(2, vol.getIdAvion());
        ps.setInt(3, vol.getIdVilleDepart());
        ps.setInt(4, vol.getIdVilleArrivee());
        ps.setTimestamp(5, new Timestamp(vol.getDateDepart().getTime()));
        ps.setTimestamp(6, new Timestamp(vol.getDateArrivee().getTime()));
        ps.setDouble(7, vol.getPrixBase());
        ps.setInt(8, vol.getId());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteVol(int id) throws Exception {
        Connection conn = Connexion.getConnection();
        String sql = "DELETE FROM Vol WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();

    }
    public List<Vol> searchVols(Integer idVilleDepart, Integer idVilleArrivee, Date dateDepart, Date dateArrive) throws Exception {
        List<Vol> vols = new ArrayList<>();
        Connection conn = Connexion.getConnection();
        
        if (conn == null) {
            throw new Exception("Impossible d'obtenir une connexion à la base de données.");
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM Vol WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (idVilleDepart != null) {
            sql.append("AND id_ville_depart = ? ");
            params.add(idVilleDepart);
        }
        if (idVilleArrivee != null) {
            sql.append("AND id_ville_arrivee = ? ");
            params.add(idVilleArrivee);
        }
        if (dateDepart != null && dateArrive != null) {
            sql.append("AND date_depart BETWEEN ? AND ? ");
            params.add(new Timestamp(dateDepart.getTime()));
            params.add(new Timestamp(dateArrive.getTime()));
        }
        if (dateDepart != null) {
            sql.append("AND date_depart >= ? ");
            params.add(new Timestamp(dateDepart.getTime()));
        }
        if (dateArrive != null) {
            sql.append("AND date_arrivee <= ? ");
            params.add(new Timestamp(dateArrive.getTime()));
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("sql = " + sql.toString());
        System.out.println("Parameters: " + params);
        try {
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            for (Object param : params) {
                ps.setObject(index++, param);
            }


            rs = ps.executeQuery();
            while (rs.next()) {
                Vol vol = new Vol();
                vol.setId(rs.getInt("id"));
                vol.setNumeroVol(rs.getString("numero_vol"));
                vol.setIdAvion(rs.getInt("id_avion"));
                vol.setIdVilleDepart(rs.getInt("id_ville_depart"));
                vol.setIdVilleArrivee(rs.getInt("id_ville_arrivee"));
                vol.setDateDepart(rs.getTimestamp("date_depart"));
                vol.setDateArrivee(rs.getTimestamp("date_arrivee"));
                vol.setPrixBase(rs.getDouble("prix_base"));
                vols.add(vol);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return vols;
    }

    public List<Vol> researchVols(Integer idVilleDepart, Integer idVilleArrivee, Date dateDepart, Date dateArrive) throws Exception {
        List<Vol> vols = new ArrayList<>();
        Connection conn = Connexion.getConnection();
        
        if (conn == null) {
            throw new Exception("Impossible d'obtenir une connexion à la base de données.");
        }
        
        // Construction de la requête SQL avec jointure pour récupérer les noms des villes
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT v.id, v.numero_vol, v.id_avion, v.date_depart, v.date_arrivee, v.prix_base, ");
        sql.append("vd.nom AS nom_ville_depart, va.nom AS nom_ville_arrivee ");
        sql.append("FROM vol v ");
        sql.append("JOIN ville vd ON v.id_ville_depart = vd.id ");
        sql.append("JOIN ville va ON v.id_ville_arrivee = va.id ");
        sql.append("WHERE 1=1 ");
        
        List<Object> params = new ArrayList<>();
        
        if (idVilleDepart != null) {
            sql.append("AND v.id_ville_depart = ? ");
            params.add(idVilleDepart);
        }
        if (idVilleArrivee != null) {
            sql.append("AND v.id_ville_arrivee = ? ");
            params.add(idVilleArrivee);
        }
        // Si les deux dates sont fournies, on recherche entre ces deux bornes
        if (dateDepart != null && dateArrive != null) {
            sql.append("AND v.date_depart BETWEEN ? AND ? ");
            params.add(new Timestamp(dateDepart.getTime()));
            params.add(new Timestamp(dateArrive.getTime()));
        }
        // Sinon, on peut ajouter des conditions séparées
        if (dateDepart != null) {
            sql.append("AND v.date_depart >= ? ");
            params.add(new Timestamp(dateDepart.getTime()));
        }
        if (dateArrive != null) {
            sql.append("AND v.date_arrivee <= ? ");
            params.add(new Timestamp(dateArrive.getTime()));
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("sql = " + sql.toString());
        System.out.println("Parameters: " + params);
        
        try {
            ps = conn.prepareStatement(sql.toString());
            int index = 1;
            for (Object param : params) {
                ps.setObject(index++, param);
            }
            
            rs = ps.executeQuery();
            while (rs.next()) {
                Vol vol = new Vol();
                vol.setId(rs.getInt("id"));
                vol.setNumeroVol(rs.getString("numero_vol"));
                vol.setIdAvion(rs.getInt("id_avion"));
                vol.setVilleDepart(rs.getString("nom_ville_depart"));
                vol.setVilleArrivee(rs.getString("nom_ville_arrivee")); 
                vol.setDateDepart(rs.getTimestamp("date_depart"));
                vol.setDateArrivee(rs.getTimestamp("date_arrivee"));
                vol.setPrixBase(rs.getDouble("prix_base"));
                vols.add(vol);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return vols;
    }

}

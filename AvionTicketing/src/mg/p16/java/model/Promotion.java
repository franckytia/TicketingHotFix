package mg.p16.java.model;
public class Promotion {
    private int id;
    private int idVol;
    private int idTypeSiege;
    private double pourcentageReduction;
    private int nombrePlacesPromo;

    public Promotion() {}

    public Promotion(int idVol, int idTypeSiege, double pourcentageReduction, int nombrePlacesPromo) {
        this.idVol = idVol;
        this.idTypeSiege = idTypeSiege;
        this.pourcentageReduction = pourcentageReduction;
        this.nombrePlacesPromo = nombrePlacesPromo;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdVol() { return idVol; }
    public void setIdVol(int idVol) { this.idVol = idVol; }

    public int getIdTypeSiege() { return idTypeSiege; }
    public void setIdTypeSiege(int idTypeSiege) { this.idTypeSiege = idTypeSiege; }

    public double getPourcentageReduction() { return pourcentageReduction; }
    public void setPourcentageReduction(double pourcentageReduction) { this.pourcentageReduction = pourcentageReduction; }

    public int getNombrePlacesPromo() { return nombrePlacesPromo; }
    public void setNombrePlacesPromo(int nombrePlacesPromo) { this.nombrePlacesPromo = nombrePlacesPromo; }
}

package mg.p16.java.model;
import java.util.Date;

public class Avion {
    private int id;
    private String modele;
    private int capacite;
    private Date dateFabrication;

    public Avion() {}

    public Avion(String modele, int capacite, Date dateFabrication) {
        this.modele = modele;
        this.capacite = capacite;
        this.dateFabrication = dateFabrication;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public Date getDateFabrication() { return dateFabrication; }
    public void setDateFabrication(Date dateFabrication) { this.dateFabrication = dateFabrication; }
}
// I see it , I look it, I want it , I got It
package mg.p16.java.model;
public class Ville {
    private int id;
    private String nom;

    public Ville() {}

    public Ville(String nom) {
        this.nom = nom;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
}

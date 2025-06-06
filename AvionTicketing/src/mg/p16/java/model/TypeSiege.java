package mg.p16.java.model;
public class TypeSiege {
    private int id;
    private String nom;
    private double coefficientPrix;

    public TypeSiege() {}

    public TypeSiege(String nom, double coefficientPrix) {
        this.nom = nom;
        this.coefficientPrix = coefficientPrix;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getCoefficientPrix() { return coefficientPrix; }
    public void setCoefficientPrix(double coefficientPrix) { this.coefficientPrix = coefficientPrix; }
}

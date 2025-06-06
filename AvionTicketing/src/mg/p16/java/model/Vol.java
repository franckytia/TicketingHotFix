package mg.p16.java.model;
import java.util.Date;

public class Vol {
    private int id;
    private String numeroVol;
    private int idAvion;
    private int idVilleDepart;
    private int idVilleArrivee;
    private Date dateDepart;
    private Date dateArrivee;
    private double prixBase;
    private String villeDepart;
    private String villeArrivee;

    public Vol() {}

    public Vol(String numeroVol, int idAvion, int idVilleDepart, int idVilleArrivee, Date dateDepart, Date dateArrivee, double prixBase) {
        this.numeroVol = numeroVol;
        this.idAvion = idAvion;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrivee = idVilleArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.prixBase = prixBase;
    }
    public Vol(int id, String numeroVol, int idAvion, int idVilleDepart, int idVilleArrivee,
               Date dateDepart, Date dateArrivee, double prixBase,
               String villeDepart, String villeArrivee) {
        this.id = id;
        this.numeroVol = numeroVol;
        this.idAvion = idAvion;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrivee = idVilleArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.prixBase = prixBase;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }
    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroVol() { return numeroVol; }
    public void setNumeroVol(String numeroVol) { this.numeroVol = numeroVol; }

    public int getIdAvion() { return idAvion; }
    public void setIdAvion(int idAvion) { this.idAvion = idAvion; }

    public int getIdVilleDepart() { return idVilleDepart; }
    public void setIdVilleDepart(int idVilleDepart) { this.idVilleDepart = idVilleDepart; }

    public int getIdVilleArrivee() { return idVilleArrivee; }
    public void setIdVilleArrivee(int idVilleArrivee) { this.idVilleArrivee = idVilleArrivee; }

    public Date getDateDepart() { return dateDepart; }
    public void setDateDepart(Date dateDepart) { this.dateDepart = dateDepart; }

    public Date getDateArrivee() { return dateArrivee; }
    public void setDateArrivee(Date dateArrivee) { this.dateArrivee = dateArrivee; }

    public double getPrixBase() { return prixBase; }
    public void setPrixBase(double prixBase) { this.prixBase = prixBase; }
    
    public String getVilleDepart() { return villeDepart; }
    public void setVilleDepart(String villeDepart) { this.villeDepart = villeDepart; }

    public String getVilleArrivee() { return villeArrivee; }
    public void setVilleArrivee(String villeArrivee) { this.villeArrivee = villeArrivee; }
}

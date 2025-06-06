package mg.p16.java.model;

import java.util.Date;

public class Reservation {
    private int id;
    private int idVol;
    private String auNom;
    private int CIN;
    private Date dateReservation;
    private String statut; // 'Confirmé', 'Annulé', 'En attente'
    private double prixTotal;  // Initialisé à 0.0 lors de l'insertion

    public Reservation() {}

    public Reservation(int idVol, String auNom, int CIN, Date dateReservation, double prixTotal, String statut) {
        this.idVol = idVol;
        this.auNom = auNom;
        this.CIN = CIN;
        this.dateReservation = dateReservation;
        this.prixTotal = prixTotal;
        this.statut = statut;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdVol() { return idVol; }
    public void setIdVol(int idVol) { this.idVol = idVol; }

    public String getAuNom() { return auNom; }
    public void setAuNom(String auNom) { this.auNom = auNom; }

    public int getCIN() { return CIN; }
    public void setCIN(int CIN) { this.CIN = CIN; }

    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }
}

package mg.p16.java.model;

public class ReservationDetail {
    private int id;
    private int reservationId;
    private int nbAdulte;
    private int nbEnfant;
    // Vous pouvez ajouter d'autres champs, par exemple : id_type_siege_adulte, id_type_siege_enfant, etc.

    public ReservationDetail() {}

    public ReservationDetail(int reservationId, int nbAdulte, int nbEnfant) {
        this.reservationId = reservationId;
        this.nbAdulte = nbAdulte;
        this.nbEnfant = nbEnfant;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    
    public int getNbAdulte() { return nbAdulte; }
    public void setNbAdulte(int nbAdulte) { this.nbAdulte = nbAdulte; }
    
    public int getNbEnfant() { return nbEnfant; }
    public void setNbEnfant(int nbEnfant) { this.nbEnfant = nbEnfant; }
}

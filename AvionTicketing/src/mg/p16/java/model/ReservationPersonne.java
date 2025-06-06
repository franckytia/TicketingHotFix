package mg.p16.java.model;

public class ReservationPersonne {
    private int id;
    private int idReservation;
    private int idCategoriePersonne;  // correspond à id_categorie_peronne
    private int total;

    public ReservationPersonne() {}

    public ReservationPersonne(int idReservation, int idCategoriePersonne, int total) {
        this.idReservation = idReservation;
        this.idCategoriePersonne = idCategoriePersonne;
        this.total = total;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdReservation() { return idReservation; }
    public void setIdReservation(int idReservation) { this.idReservation = idReservation; }

    public int getIdCategoriePersonne() { return idCategoriePersonne; }
    public void setIdCategoriePersonne(int idCategoriePersonne) { this.idCategoriePersonne = idCategoriePersonne; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}

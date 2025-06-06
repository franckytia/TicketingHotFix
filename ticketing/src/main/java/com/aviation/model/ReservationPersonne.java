package com.aviation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Reservation_personne")
public class ReservationPersonne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_reservation", nullable = false)
    private Integer idReservation;

    @Column(name = "id_categorie_peronne", nullable = false)
    private Integer idCategoriePersonne;

    @Column(nullable = false)
    private Integer total;

    @Transient
    private String categorieDescription;

    // Constructeurs
    public ReservationPersonne() { }

    public ReservationPersonne(Integer idReservation, Integer idCategoriePersonne, Integer total) {
        this.idReservation = idReservation;
        this.idCategoriePersonne = idCategoriePersonne;
        this.total = total;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
    public Integer getIdCategoriePersonne() {
        return idCategoriePersonne;
    }
    public void setIdCategoriePersonne(Integer idCategoriePersonne) {
        this.idCategoriePersonne = idCategoriePersonne;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCategorieDescription() {
        return categorieDescription;
    }

    public void setCategorieDescription(String categorieDescription) {
        this.categorieDescription = categorieDescription;
    }
}

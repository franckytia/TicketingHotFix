package com.aviation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ReservationDetail")
public class ReservationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Référence à la réservation correspondante
    @Column(name = "id_reservation", nullable = false)
    private Integer idReservation;

    @Column(name = "nom_passager", nullable = false, length = 100)
    private String nomPassager;

    @Column(name = "taille")
    private Double taille;

    @Column(name = "passport", nullable = false, length = 50)
    private String passport;

    // Référence vers le type de siège choisi
    @Column(name = "id_type_siege", nullable = false)
    private Integer idTypeSiege;

    @Column(name = "remarque")
    private String remarque;

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
    public String getNomPassager() {
        return nomPassager;
    }
    public void setNomPassager(String nomPassager) {
        this.nomPassager = nomPassager;
    }
    public Double getTaille() {
        return taille;
    }
    public void setTaille(Double taille) {
        this.taille = taille;
    }
    public String getPassport() {
        return passport;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    public Integer getIdTypeSiege() {
        return idTypeSiege;
    }
    public void setIdTypeSiege(Integer idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }
    public String getRemarque() {
        return remarque;
    }
    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }
}

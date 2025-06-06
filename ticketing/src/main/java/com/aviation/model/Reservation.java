package com.aviation.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_vol", nullable = false)
    private Integer idVol;

    @Column(name = "au_nom", nullable = false, length = 200)
    private String auNom;

    @Column(name = "CIN", nullable = false)
    private Integer cin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_reservation", nullable = false)
    private Date dateReservation;

    @Column(name = "statut", nullable = false, length = 20)
    private String statut;

    @Column(name = "prix_total")
    private Double prixTotal;

    // Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVol() {
        return idVol;
    }

    public void setIdVol(Integer idVol) {
        this.idVol = idVol;
    }

    public String getAuNom() {
        return auNom;
    }

    public void setAuNom(String auNom) {
        this.auNom = auNom;
    }

    public Integer getCin() {
        return cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }
}

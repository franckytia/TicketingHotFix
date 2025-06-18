package com.aviation.dto;

public class ReservationDetailDTO {
    private Integer idReservation;
    private Integer idCategorie;
    private String nomPassager;
    private Double taille;
    private String passport;
    private Integer idTypeSiege;
    private String remarque;
    
    // Getters et Setters
    public Integer getIdReservation() { return idReservation; }
    public void setIdReservation(Integer idReservation) { this.idReservation = idReservation; }
    public Integer getIdCategorie() { return idCategorie; }
    public void setIdCategorie(Integer idCategorie) { this.idCategorie = idCategorie; }
    public String getNomPassager() { return nomPassager; }
    public void setNomPassager(String nomPassager) { this.nomPassager = nomPassager; }
    public Double getTaille() { return taille; }
    public void setTaille(Double taille) { this.taille = taille; }
    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }
    public Integer getIdTypeSiege() { return idTypeSiege; }
    public void setIdTypeSiege(Integer idTypeSiege) { this.idTypeSiege = idTypeSiege; }
    public String getRemarque() { return remarque; }
    public void setRemarque(String remarque) { this.remarque = remarque; }
 
}

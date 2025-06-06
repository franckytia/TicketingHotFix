package com.aviation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "typesiege")
public class TypeSiege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String nom;

    @Column(name = "coefficient_prix", nullable = false)
    private Double coefficientPrix;

    // Constructeurs
    public TypeSiege() { }

    public TypeSiege(String nom, Double coefficientPrix) {
        this.nom = nom;
        this.coefficientPrix = coefficientPrix;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Double getCoefficientPrix() {
        return coefficientPrix;
    }
    public void setCoefficientPrix(Double coefficientPrix) {
        this.coefficientPrix = coefficientPrix;
    }
}

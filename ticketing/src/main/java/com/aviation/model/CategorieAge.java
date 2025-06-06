package com.aviation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorie_age")
public class CategorieAge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Lien avec la colonne "id"
    private Integer id;

    @Column(name = "description") // Lien avec la colonne "description"
    private String description;  // Exemple : "Enfant", "Adulte", "Senior"
    
    @Column(name = "age_min") // Lien avec la colonne "age_min"
    private Integer ageMin;  // Exemple : 1

    @Column(name = "age_max") // Lien avec la colonne "age_max"
    private Integer ageMax;  // Exemple : 12
    
    @Column(name = "coefficient") // Lien avec la colonne "coefficient"
    private Double coefficient;  // Prix du billet pour cette catégorie

    // Constructeurs
    public CategorieAge() {}

    public CategorieAge(String description, Integer ageMin, Integer ageMax, Double coefficient) {
        this.description = description;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.coefficient = coefficient;
    }

    // Getters et Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getAgeMin() { return ageMin; }
    public void setAgeMin(Integer ageMin) { this.ageMin = ageMin; }

    public Integer getAgeMax() { return ageMax; }
    public void setAgeMax(Integer ageMax) { this.ageMax = ageMax; }

    public Double getCoefficient() { return coefficient; } // Correction du getter
    public void setCoefficient(Double coefficient) { this.coefficient = coefficient; } // Correction du setter
}

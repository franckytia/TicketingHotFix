package mg.p16.java.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieAge {
    private int id;
    private int ageMax;
    private int ageMin;
    private String description;
    private double coefficient;

    // Constructeurs
    public CategorieAge() {}

    public CategorieAge(int id, int ageMax, int ageMin, String description, double coefficient) {
        this.id = id;
        this.ageMax = ageMax;
        this.ageMin = ageMin;
        this.description = description;
        this.coefficient = coefficient;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getAgeMax() { return ageMax; }
    public void setAgeMax(int ageMax) { this.ageMax = ageMax; }
    
    public int getAgeMin() { return ageMin; }
    public void setAgeMin(int ageMin) { this.ageMin = ageMin; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getCoefficient() { return coefficient; }
    public void setCoefficient(double coefficient) { this.coefficient = coefficient; }
}

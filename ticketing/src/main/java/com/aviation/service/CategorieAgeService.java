package com.aviation.service;

import com.aviation.model.CategorieAge;
import com.aviation.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieAgeService {
    
    private final CategorieAgeRepository categorieAgeRepository;

    public CategorieAgeService(CategorieAgeRepository categorieAgeRepository) {
        this.categorieAgeRepository = categorieAgeRepository;
    }

    public List<CategorieAge> getAllCategories() {
        return categorieAgeRepository.findAll();
    }

    public void saveCategorie(CategorieAge categorie) {
        categorieAgeRepository.save(categorie);
    }

    public CategorieAge getCategorieById(Integer id) {
        return categorieAgeRepository.findById(id).orElse(null);
    }

}

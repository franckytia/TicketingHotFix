package com.aviation.controller;

import com.aviation.model.CategorieAge;
import com.aviation.service.CategorieAgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CategorieAgeController {

    private final CategorieAgeService categorieAgeService;

    public CategorieAgeController(CategorieAgeService categorieAgeService) {
        this.categorieAgeService = categorieAgeService;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categorieAgeService.getAllCategories());
        return "categories"; // Renvoie vers la page Thymeleaf
    }

    @GetMapping("/categorie/nouveau")
    public String showFormCategorie(Model model) {
        model.addAttribute("categorie", new CategorieAge());
        return "categorie-form";
    }

    @PostMapping("/categorie/save")
    public String saveCategorie(@ModelAttribute CategorieAge categorie) {
        categorieAgeService.saveCategorie(categorie);
        return "redirect:/categories";
    }

     // 1. Afficher la page de sélection de la catégorie à éditer
    @GetMapping("/categorie/edit")
    public String showEditSelection(Model model, @RequestParam(value = "id", required = false) Integer id) {
        // Si un id est envoyé, redirige vers le formulaire d'édition
        if(id != null) {
            return "redirect:/categorie/edit/" + id;
        }
        model.addAttribute("categories", categorieAgeService.getAllCategories());
        return "categorie-edit-selection";
    }

    // 2. Afficher le formulaire d'édition pour une catégorie donnée
    @GetMapping("/categorie/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        CategorieAge categorie = categorieAgeService.getCategorieById(id);
        if(categorie == null) {
            return "redirect:/categories";
        }
        model.addAttribute("categorie", categorie);
        return "categorie-edit-form";
    }

    // 3. Enregistrer les modifications apportées à la catégorie (seul le prix peut être modifié)
    @PostMapping("/categorie/update")
    public String updateCategorie(@ModelAttribute CategorieAge categorie) {
        categorieAgeService.saveCategorie(categorie);
        return "redirect:/categories";
    }
}

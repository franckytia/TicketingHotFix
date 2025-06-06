package com.aviation.controller;
import com.aviation.service.PdfService; // Assurez-vous que le service PDF est correctement importé
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import com.aviation.dto. etc. si nécessaire

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // ou ce qui convient
@RequestMapping("/pdf") // racine de l'URL
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> getReservationPdf(@PathVariable("id") Integer id) {
        try {
            byte[] pdfBytes = pdfService.generatePdf(id);
            
            // On renvoie le contenu PDF en tant que fichier téléchargeable
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    // On peut ajouter l'en-tête pour forcer le téléchargement au lieu de l'affichage
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation-" + id + ".pdf")
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erreur lors de la génération du PDF pour la réservation " + id + " : " + e.getMessage());
        }
    }
}

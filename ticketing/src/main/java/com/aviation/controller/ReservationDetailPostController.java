package com.aviation.controller;

import com.aviation.dto.ReservationDetailForm;
import com.aviation.dto.ReservationDetailDTO;
import com.aviation.model.ReservationDetail;
import com.aviation.repository.ReservationDetailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationDetailPostController {

    private final ReservationDetailRepository reservationDetailRepository;

    public ReservationDetailPostController(ReservationDetailRepository reservationDetailRepository) {
        this.reservationDetailRepository = reservationDetailRepository;
    }

    @PostMapping("/reservation/detail/addMultiple")
    public String addMultipleReservationDetails(@ModelAttribute ReservationDetailForm detailForm, Model model) {
        try {
            // Vérifie que la liste des détails n'est pas nulle
            if (detailForm.getDetails() != null || !detailForm.getDetails().isEmpty()) {
                // Parcours de chaque DTO et sauvegarde en base
                for (ReservationDetailDTO dto : detailForm.getDetails()){
                    ReservationDetail detail = new ReservationDetail();
                    detail.setIdReservation(dto.getIdReservation());
                    detail.setNomPassager(dto.getNomPassager());
                    detail.setTaille(dto.getTaille());
                    detail.setPassport(dto.getPassport());
                    detail.setIdTypeSiege(dto.getIdTypeSiege());
                    detail.setRemarque(dto.getRemarque());
                    
                    // Sauvegarde de l'entité en base de données
                    reservationDetailRepository.save(detail);
                }
            }
            // En cas de succès, on ajoute les attributs pour la vue
            model.addAttribute("status", "success");
            model.addAttribute("message", "Les réservations ont été enregistrées avec succès !");
        } catch (Exception ex) {
            // En cas d'erreur, on renseigne l'état d'erreur et le message correspondant
            model.addAttribute("status", "error");
            model.addAttribute("message", "Une erreur est survenue lors de l'enregistrement des réservations : " + ex.getMessage());
        }
        // La page "response.html" affichera le résultat
        return "response";
    }
}

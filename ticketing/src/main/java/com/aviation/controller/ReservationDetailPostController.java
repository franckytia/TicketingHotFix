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
            if (detailForm.getDetails() != null && !detailForm.getDetails().isEmpty()) {
                Integer idReservationGlobal = detailForm.getIdReservation();
                int index = 0;
                for (ReservationDetailDTO dto : detailForm.getDetails()) {
                    ReservationDetail detail = new ReservationDetail();

                    // Sécurisation de tous les champs obligatoires
                    Integer idReservation = dto.getIdReservation() != null ? dto.getIdReservation() : idReservationGlobal;
                    if (idReservation == null) {
                        throw new IllegalArgumentException("idReservation manquant pour le détail #" + index);
                    }
                    if (dto.getNomPassager() == null || dto.getNomPassager().isEmpty()) {
                        throw new IllegalArgumentException("nomPassager manquant pour le détail #" + index + " et " + dto.getNomPassager());
                    }
                    if (dto.getPassport() == null || dto.getPassport().isEmpty()) {
                        throw new IllegalArgumentException("passport manquant pour le détail #" + index);
                    }
                    if (dto.getIdTypeSiege() == null) {
                        throw new IllegalArgumentException("idTypeSiege manquant pour le détail #" + index);
                    }

                    detail.setIdReservation(idReservation);
                    detail.setNomPassager(dto.getNomPassager());
                    detail.setTaille(dto.getTaille());
                    detail.setPassport(dto.getPassport());
                    detail.setIdTypeSiege(dto.getIdTypeSiege());
                    detail.setRemarque(dto.getRemarque());

                    System.out.println("Avant save : idReservation = " + detail.getIdReservation() + ", idTypeSiege = " + detail.getIdTypeSiege());
                     System.out.println("DTO #" + index + " : nomPassager=" + dto.getNomPassager() + ", idTypeSiege=" + dto.getIdTypeSiege() + ", passport=" + dto.getPassport());
                     System.out.println(detailForm.getDetails().size() + " détails à traiter.");    
                    if (dto == null) {
                        System.out.println("details[" + index + "] = null");
                    } else {
                        System.out.println("details[" + index + "]: "
                                + "idReservation=" + dto.getIdReservation()
                                + ", idCategorie=" + dto.getIdCategorie()
                                + ", nomPassager=" + dto.getNomPassager()
                                + ", taille=" + dto.getTaille()
                                + ", passport=" + dto.getPassport()
                                + ", idTypeSiege=" + dto.getIdTypeSiege()
                                + ", remarque=" + dto.getRemarque());
                    }
                    System.out.println("===== FIN DUMP =====");
                    reservationDetailRepository.save(detail);
                    index++;
                }
            }
            model.addAttribute("status", "success");
            model.addAttribute("message", "Les réservations ont été enregistrées avec succès !");
        } catch (Exception ex) {
            model.addAttribute("status", "error");
            model.addAttribute("message", "Une erreur est survenue lors de l'enregistrement des réservations : " + ex.getMessage());
        }
        return "response";
    }
}

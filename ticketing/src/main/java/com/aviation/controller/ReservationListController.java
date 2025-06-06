package com.aviation.controller;

import com.aviation.model.Reservation;
import com.aviation.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ReservationListController {

    private final ReservationRepository reservationRepository;

    public ReservationListController(ReservationRepository reservationRepository) {
         this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations")
    public String listReservations(Model model) {
         List<Reservation> reservations = reservationRepository.findAll();
         model.addAttribute("reservations", reservations);
         return "reservationList"; // Affiche la liste des réservations
    }

    // SUPPRIMEZ (ou renommez) cette méthode pour éviter le conflit
    // @GetMapping("/reservation/detail/{reservationId}")
    // public String showReservationDetailForm(@PathVariable Integer reservationId, Model model) {
    //     // Cette méthode doit être gérée dans ReservationDetailController
    //     model.addAttribute("reservationId", reservationId);
    //     return "reservationDetailForm";
    // }
}

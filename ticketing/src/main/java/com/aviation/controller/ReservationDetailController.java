package com.aviation.controller;

import com.aviation.model.Reservation;
import com.aviation.model.ReservationPersonne;
import com.aviation.model.TypeSiege;
import com.aviation.model.ReservationDetail;
import com.aviation.repository.ReservationRepository;
import com.aviation.repository.ReservationPersonneRepository;
import com.aviation.repository.ReservationDetailRepository;
import com.aviation.repository.TypeSiegeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.aviation.service.*;
@Controller
public class ReservationDetailController {

    private final ReservationRepository reservationRepository;
    private final ReservationPersonneRepository reservationPersonneRepository;
    private final TypeSiegeRepository typeSiegeRepository;
    private final ReservationDetailRepository reservationDetailRepository;

    public ReservationDetailController(ReservationRepository reservationRepository,
                                       ReservationPersonneRepository reservationPersonneRepository,
                                       TypeSiegeRepository typeSiegeRepository,
                                       ReservationDetailRepository reservationDetailRepository) {
        this.reservationDetailRepository = reservationDetailRepository;
        this.reservationRepository = reservationRepository;
        this.reservationPersonneRepository = reservationPersonneRepository;
        this.typeSiegeRepository = typeSiegeRepository;
    }

    @GetMapping("/reservation/ajout/detail/{reservationId}")
    public String showReservationDetailForm(@PathVariable Integer reservationId, Model model) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            model.addAttribute("error", "Réservation non trouvée.");
            return "errorPage";
        }
        List<ReservationPersonne> rpList = reservationPersonneRepository.findByIdReservation(reservationId);
        List<TypeSiege> typesSiege = typeSiegeRepository.findAll();

        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationPersonnes", rpList);
        model.addAttribute("typesSiege", typesSiege);
        return "reservationDetailForm";
    }
    @GetMapping("/reservations/{reservationId}")
    public String getReservationDetails(@PathVariable Integer reservationId, Model model) {
        // Même logique que showReservationDetailForm()
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            model.addAttribute("error", "Réservation non trouvée.");
            return "response";
        }
        List<ReservationPersonne> rpList = reservationPersonneRepository.findByIdReservation(reservationId);
        List<TypeSiege> typesSiege = typeSiegeRepository.findAll();
        List<ReservationDetail> reservationDetails = reservationDetailRepository.findByIdReservation(reservationId);

        model.addAttribute("reservation", reservation);
        model.addAttribute("reservationPersonnes", rpList);
        model.addAttribute("typesSiege", typesSiege);
        model.addAttribute("reservationDetails", reservationDetails);
        return "reservationDetails";
    }

}

package com.aviation.service;

import com.aviation.model.Reservation;
import com.aviation.model.ReservationPersonne;
import com.aviation.model.ReservationDetail;
import com.aviation.model.TypeSiege;
import com.aviation.repository.ReservationRepository;
import com.aviation.repository.ReservationPersonneRepository;
import com.aviation.repository.ReservationDetailRepository;
import com.aviation.repository.TypeSiegeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PdfService {

    private final PdfGeneratorService pdfGeneratorService;
    private final ReservationRepository reservationRepository;
    private final ReservationPersonneRepository reservationPersonneRepository;
    private final TypeSiegeRepository typeSiegeRepository;
    private final ReservationDetailRepository reservationDetailRepository;

    public PdfService(PdfGeneratorService pdfGeneratorService,
                      ReservationRepository reservationRepository,
                      ReservationPersonneRepository reservationPersonneRepository,
                      TypeSiegeRepository typeSiegeRepository,
                      ReservationDetailRepository reservationDetailRepository) {
        this.pdfGeneratorService = pdfGeneratorService;
        this.reservationRepository = reservationRepository;
        this.reservationPersonneRepository = reservationPersonneRepository;
        this.typeSiegeRepository = typeSiegeRepository;
        this.reservationDetailRepository = reservationDetailRepository;
    }

    public byte[] generatePdf(Integer id) throws Exception {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new Exception("Réservation introuvable : " + id));
        List<ReservationPersonne> rpList = reservationPersonneRepository.findByIdReservation(id);
        List<TypeSiege> typesSiege = typeSiegeRepository.findAll();
        List<ReservationDetail> reservationDetails = reservationDetailRepository.findByIdReservation(id);

        // Ici, nous passons également les listes supplémentaires au générateur PDF
        return pdfGeneratorService.generatePdf(reservation, rpList, typesSiege, reservationDetails);
    }
}

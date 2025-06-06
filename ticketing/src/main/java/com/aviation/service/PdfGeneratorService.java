package com.aviation.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.aviation.model.Reservation;
import com.aviation.model.*;
import java.io.ByteArrayOutputStream;
import com.aviation.service.ReservationDetailService;
import java.util.List;


@Service
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;
    private final ReservationDetailService reservationDetailService;

    public PdfGeneratorService(TemplateEngine templateEngine,
                               ReservationDetailService reservationDetailService) {
        this.templateEngine = templateEngine;
        this.reservationDetailService = reservationDetailService;
    }

    public byte[] generatePdf(Reservation reservation,List<ReservationPersonne> rpList,List<TypeSiege> typesSiege,List<ReservationDetail> reservationDetails) throws Exception {
        
        // Récupère la liste des détails, si nécessaire
        // List<ReservationDetail> details = reservationDetailService.findByReservationId(reservation.getId());

        // Construire un contexte Thymeleaf
        Context context = new Context();
        context.setVariable("reservation", reservation);
        context.setVariable("reservationPersonnes", rpList);
        context.setVariable("typesSiege", typesSiege);
        context.setVariable("reservationDetails", reservationDetails);
        // context.setVariable("details", details);
        
        // Générer du HTML à partir d’un template Thymeleaf
        String htmlContent = templateEngine.process("reservationPDF", context);
        
        // Convertir ce HTML en PDF avec OpenHTMLToPDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        com.openhtmltopdf.pdfboxout.PdfRendererBuilder builder = new com.openhtmltopdf.pdfboxout.PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(htmlContent, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }
}

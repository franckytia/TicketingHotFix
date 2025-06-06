package mg.p16.java.controller;

import com.framework.annotations.GET;
import com.framework.annotations.POST;
import com.framework.annotations.Param;
import com.framework.annotations.AnnotationController;
import com.framework.model.ModelView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import mg.p16.java.dao.ReservationDAO;
import mg.p16.java.dao.VolDAO;
import mg.p16.java.dao.CategorieAgeDAO;
import mg.p16.java.dao.ReservationPersonneDAO;
import mg.p16.java.model.Reservation;
import mg.p16.java.model.Vol;
import mg.p16.java.model.CategorieAge;
import mg.p16.java.model.ReservationPersonne;

@AnnotationController
public class ReservationController {

    ReservationDAO reservationDao = new ReservationDAO();
    VolDAO volDao = new VolDAO();
    CategorieAgeDAO categorieAgeDAO = new CategorieAgeDAO();
    ReservationPersonneDAO reservationPersonneDAO = new ReservationPersonneDAO();

    @GET("/reserve")
    public ModelView showReservationForm(@Param(name = "id") String idVolStr) {
        ModelView view = new ModelView("web/jsp/frontoffice/flightDetails.jsp");
        try {
            int idVol = Integer.parseInt(idVolStr);
            Vol vol = volDao.getVolById(idVol);
            view.addObject("vol", vol);
        
            // Récupération dynamique des catégories d'âge
            List<CategorieAge> categories = categorieAgeDAO.getAllCategories();
            view.addObject("categories", categories);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération du vol pour réservation : " + e.getMessage());
        }
        return view;
    }

    @POST("/doReservation")
    public ModelView doReservation(
            @Param(name = "idVol") String idVolStr,
            @Param(name = "nom") String auNom,
            @Param(name = "cin") String cin,
            @Param(name = "date") String dateStr,
            HttpServletRequest request
    ) {
        ModelView view = new ModelView("web/jsp/frontoffice/reservationConfirmation.jsp");
        try {
            int idVol = Integer.parseInt(idVolStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date dateReservation = sdf.parse(dateStr);
            
            // Création de la réservation de base (prix_total initialisé à 0.0 et statut à "En attente")
            Reservation reservation = new Reservation(
                    idVol, 
                    auNom, 
                    Integer.parseInt(cin), 
                    dateReservation, 
                    0.0, 
                    "En attente"
            );
            reservationDao.addReservation(reservation);
            
            // Récupération des tableaux des identifiants de catégorie et des totaux
            String[] categorieIds = request.getParameterValues("categorieIds");
            String[] totals = request.getParameterValues("totals");
            int totalPersonnes = 0;
            if (categorieIds != null && totals != null && categorieIds.length == totals.length) {
                for (int i = 0; i < categorieIds.length; i++) {
                    int catId = Integer.parseInt(categorieIds[i]);
                    int total = Integer.parseInt(totals[i].trim());
                    if (total > 0) {
                        totalPersonnes += total;
                        ReservationPersonne rp = new ReservationPersonne(reservation.getId(), catId, total);
                        reservationPersonneDAO.addReservationPersonne(rp);
                    }
                }
            }
            
            view.addObject("message", "Réservation effectuée avec succès pour " + totalPersonnes + " personnes. Numero"+ reservation.getId());
            view.addObject("numerao", reservation.getId());
        } catch (Exception e) {
            e.printStackTrace();
            view = new ModelView("web/jsp/frontoffice/reservationConfirmation.jsp");
            view.addObject("error", "Erreur lors de la réservation : " + e.getMessage());
        }
        return view;
    }

    @GET("/cancelReservation")
    public ModelView cancelReservation(@Param(name = "id") String idStr) {
        ModelView view = new ModelView("redirect:/reservations");
        try {
            int id = Integer.parseInt(idStr);
            Reservation reservation = reservationDao.getReservationById(id);
            Vol vol = volDao.getVolById(reservation.getIdVol());
            Date now = new Date();
            // Annulation interdite moins de 24 heures avant le départ
            if (vol.getDateDepart().getTime() - now.getTime() < 24 * 60 * 60 * 1000) {
                view.addObject("error", "L'annulation n'est pas possible moins de 24 heures avant le départ.");
                return view;
            }
            reservation.setStatut("Annulé");
            reservationDao.updateReservation(reservation);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de l'annulation de la réservation : " + e.getMessage());
        }
        return view;
    }

    @GET("/reservations")
    public ModelView listReservations() {
        ModelView view = new ModelView("web/jsp/backoffice/manageReservations.jsp");
        try {
            List<Reservation> reservations = reservationDao.getAllReservations();
            view.addObject("reservations", reservations);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération des réservations : " + e.getMessage());
        }
        return view;
    }
}

package mg.p16.java.controller;

import com.framework.annotations.GET;
import com.framework.annotations.POST;
import com.framework.annotations.Param;
import com.framework.annotations.Public;
import com.framework.annotations.Authentificated;
import com.framework.annotations.AnnotationController;
import com.framework.model.ModelView;
import com.framework.model.MySession;
import mg.p16.java.dao.*;
import mg.p16.java.model.*;

import java.util.ArrayList;
import java.util.List;

@AnnotationController
public class LoginController {

    MySession session; // La session doit être initialisée via ton framework
    @POST("/authenticate")
    @Public
    public ModelView authenticate(
            @Param(name = "username") String username,
            @Param(name = "password") String password) {
        ModelView view;
        try {
            UtilisateurDAO dao = new UtilisateurDAO();
            Utilisateur utilisateur = dao.authenticate(username, password);
            if (utilisateur != null) {
                session.add("user", utilisateur.getEmail());
                session.add("role", utilisateur.getRole());
                if ("admin".equalsIgnoreCase(utilisateur.getRole())) {
                    view = new ModelView("web/jsp/backoffice/adminDashboard.jsp");
                    view.addObject("message", "Connexion réussie en tant qu'administrateur.");
                } else {
                    view = new ModelView("web/jsp/frontoffice/home.jsp");
                    view.addObject("message", "Connexion réussie.");
                }
            } else {
                view = new ModelView("web/jsp/frontoffice/login.jsp");
                view.addObject("error", "Nom d'utilisateur ou mot de passe incorrect.");
            }
        } catch (Exception e) {
            view = new ModelView("web/jsp/frontoffice/login.jsp");
            view.addObject("error", "Erreur lors de l'authentification : " + e.getMessage());
        }
        return view;
    }
    @GET("/manageFlights")
    @Authentificated
    public ModelView manageFlights() {
        ModelView view = new ModelView("/web/jsp/backoffice/manageFlights.jsp");
        view.addObject("message", "Vous etes allez dans manageFlights.");
        List<Vol> vols = new ArrayList<>();
        VolDAO volDao = new VolDAO();
        try{ 
            vols = volDao.getAllVols();
        }catch(Exception e){
             view.addObject("error", "Erreur lors de la recuperation du vol : " + e.getMessage());
        }
        view.addObject("vols", vols);
        return view;
    }
    @GET("/manageReservations")
    @Authentificated
    public ModelView manageReservations() {
        ModelView view = new ModelView("/web/jsp/backoffice/manageReservations.jsp");
        view.addObject("message", "Vous etes allez dans manageReservations.");
        List<Reservation> reservations = new ArrayList<>();
        ReservationDAO reservationDao = new ReservationDAO();
        try {
            reservations = reservationDao.getAllReservations();
        }catch(Exception e){
             view.addObject("error", "Erreur lors de la recuperation du reservation : " + e.getMessage());
        }
        view.addObject("reservations", reservations);
        return view;
    }
}

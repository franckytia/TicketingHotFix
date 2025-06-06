package mg.p16.java.controller;

import com.framework.annotations.GET;
import com.framework.annotations.POST;
import com.framework.annotations.Param;
import com.framework.annotations.AnnotationController;
import com.framework.model.ModelView;
import java.text.SimpleDateFormat;
import java.sql.Date;  // java.sql.Date
import java.util.List;
import mg.p16.java.dao.VolDAO;
import mg.p16.java.dao.*;
import mg.p16.java.model.Vol;
import mg.p16.java.model.*;
import java.text.ParseException;
@AnnotationController
public class FlightController {

    VolDAO volDao = new VolDAO();
    VilleDAO villeDao = new VilleDAO();

    @GET("/flights")
    public ModelView listFlights() {
        ModelView view = new ModelView("web/jsp/backoffice/manageFlights.jsp");
        try {
            List<Vol> vols = volDao.getAllVols();
            view.addObject("vols", vols);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération des vols : " + e.getMessage());
        }
        return view;
    }
    @GET("/flightsFO")
    public ModelView listFlightsFO() {
        ModelView view = new ModelView("web/jsp/frontoffice/flightList.jsp");
        try {
            List<Vol> vols = volDao.getAllVols();
            view.addObject("vols", vols);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération des vols : " + e.getMessage());
        }
        return view;
    }
    @GET("/flightForm")
    public ModelView showFlightForm(@Param(name = "id") String idStr) {
        ModelView view = new ModelView("web/jsp/backoffice/flightForm.jsp");
        try {
            // Récupération de la liste des villes
            List<Ville> villes = villeDao.getAllVilles();
            view.addObject("villes", villes); // Ajout des villes au ModelView

            // Si un ID est fourni, récupérer le vol correspondant
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Vol vol = volDao.getVolById(id);
                view.addObject("vol", vol); // Ajout du vol au ModelView
            }
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération des données : " + e.getMessage());
        }
        return view;
    }
    @POST("/addFlight")
    public ModelView addFlight(
            @Param(name = "numeroVol") String numeroVol,
            @Param(name = "idAvion") String idAvionStr,
            @Param(name = "idVilleDepart") String idVilleDepartStr,
            @Param(name = "idVilleArrivee") String idVilleArriveeStr,
            @Param(name = "dateDepart") String dateDepartStr,
            @Param(name = "dateArrivee") String dateArriveeStr,
            @Param(name = "prixBase") String prixBaseStr) {
        ModelView view = new ModelView("web/jsp/backoffice/manageFlights.jsp");
        try {
            int idAvion = Integer.parseInt(idAvionStr);
            int idVilleDepart = Integer.parseInt(idVilleDepartStr);
            int idVilleArrivee = Integer.parseInt(idVilleArriveeStr);
            double prixBase = Double.parseDouble(prixBaseStr);

            // Utilisez un format de date spécifique pour Java
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            
            java.util.Date utilDateDepart = sdf.parse(dateDepartStr);
            java.util.Date utilDateArrivee = sdf.parse(dateArriveeStr);
            
            // Convertissez les dates en Timestamp pour la base de données
            java.sql.Timestamp sqlTimestampDepart = new java.sql.Timestamp(utilDateDepart.getTime());
            java.sql.Timestamp sqlTimestampArrivee = new java.sql.Timestamp(utilDateArrivee.getTime());

            Vol vol = new Vol(numeroVol, idAvion, idVilleDepart, idVilleArrivee, sqlTimestampDepart, sqlTimestampArrivee, prixBase);
            volDao.addVol(vol);
            List<Vol> vols = volDao.getAllVols();
            view.addObject("vols", vols);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du vol : " + e.getMessage());
            view.addObject("error", "Erreur lors de l'ajout du vol : " + e.getMessage());
            view = new ModelView("web/jsp/backoffice/flightForm.jsp");
        }
        return view;
    }
    @POST("/updateFlight")
    public ModelView updateFlight(
            @Param(name = "id") String idStr,
            @Param(name = "numeroVol") String numeroVol,
            @Param(name = "idAvion") String idAvionStr,
            @Param(name = "idVilleDepart") String idVilleDepartStr,
            @Param(name = "idVilleArrivee") String idVilleArriveeStr,
            @Param(name = "dateDepart") String dateDepartStr,
            @Param(name = "dateArrivee") String dateArriveeStr,
            @Param(name = "prixBase") String prixBaseStr) {
        ModelView view = new ModelView("web/jsp/backoffice/manageFlights.jsp");
        try {
            int id = Integer.parseInt(idStr);
            int idAvion = Integer.parseInt(idAvionStr);
            int idVilleDepart = Integer.parseInt(idVilleDepartStr);
            int idVilleArrivee = Integer.parseInt(idVilleArriveeStr);
            double prixBase = Double.parseDouble(prixBaseStr);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            java.util.Date utilDateDepart = sdf.parse(dateDepartStr);
            java.util.Date utilDateArrivee = sdf.parse(dateArriveeStr);
            Date dateDepart = new Date(utilDateDepart.getTime());
            Date dateArrivee = new Date(utilDateArrivee.getTime());
            
            Vol vol = new Vol(numeroVol, idAvion, idVilleDepart, idVilleArrivee, dateDepart, dateArrivee, prixBase);
            vol.setId(id);
            volDao.updateVol(vol);
            List<Vol> vols = volDao.getAllVols();
            view.addObject("vols", vols);
        } catch (Exception e) {
            view = new ModelView("web/jsp/backoffice/flightForm.jsp");
            view.addObject("error", "Erreur lors de la mise à jour du vol : " + e.getMessage());
        }
        return view;
    }
    @GET("/deleteFlight")
    public ModelView deleteFlight(@Param(name = "id") String idStr) {
        ModelView view = new ModelView("web/jsp/backoffice/manageFlights.jsp");
        try {
            int id = Integer.parseInt(idStr);
            volDao.deleteVol(id);
            List<Vol> vols = volDao.getAllVols();
            view.addObject("vols", vols);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la suppression du vol : " + e.getMessage());
        }
        return view;
    }
    @GET("/searchFlights")
    public ModelView searchFlights() {
        ModelView view = new ModelView("web/jsp/frontoffice/searchFlights.jsp");
        try {
           List<Ville> villes = villeDao.getAllVilles();
            view.addObject("villes", villes);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération des détails du vol : " + e.getMessage());
        }
        return view;
    }
    @GET("/flightDetails")
    public ModelView flightDetails(@Param(name = "id") String idStr) {
        ModelView view = new ModelView("web/jsp/frontoffice/flightDetails.jsp");
        try {
            int id = Integer.parseInt(idStr);
            Vol vol = volDao.getVolById(id);
            view.addObject("vol", vol);
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la récupération des détails du vol : " + e.getMessage());
        }
        return view;
    }
   @GET("/SearchFlightK")
    public ModelView SearchFlightK(
            @Param(name = "idVilleDepart") String idVilleDepartStr,
            @Param(name = "idVilleArrivee") String idVilleArriveeStr,
            @Param(name = "dateDepart") String dateDepartStr,
            @Param(name = "dateArrive") String dateArriveStr) {

        ModelView view = new ModelView("web/jsp/frontoffice/flightList.jsp");
        Integer idVilleDepart = null;
        Integer idVilleArrivee = null;
        Date dateDepart = null;
        Date dateArrive = null;

        try {
            // Vérification et conversion des ID
            if (idVilleDepartStr != null && !idVilleDepartStr.isEmpty()) {
                try {
                    idVilleDepart = Integer.parseInt(idVilleDepartStr);
                } catch (NumberFormatException e) {
                    view.addObject("error", "ID Ville Départ invalide.");
                    return view;
                }
            }

            if (idVilleArriveeStr != null && !idVilleArriveeStr.isEmpty()) {
                try {
                    idVilleArrivee = Integer.parseInt(idVilleArriveeStr);
                } catch (NumberFormatException e) {
                    view.addObject("error", "ID Ville Arrivée invalide.");
                    return view;
                }
            }

            // Format de date attendu : "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            sdf.setLenient(false); // Désactive la tolérance pour éviter des dates invalides

            if (dateDepartStr != null && !dateDepartStr.isEmpty()) {
                try {
                    dateDepart = new Date(sdf.parse(dateDepartStr).getTime());
                } catch (ParseException e) {
                    view.addObject("error", "Format de date de départ invalide (Attendu : yyyy-MM-dd).");
                    return view;
                }
            }

            if (dateArriveStr != null && !dateArriveStr.isEmpty()) {
                try {
                    dateArrive = new Date(sdf.parse(dateArriveStr).getTime());
                } catch (ParseException e) {
                    view.addObject("error", "Format de date d'arrivée invalide (Attendu : yyyy-MM-dd).");
                    return view;
                }
            }

            // Exécution de la recherche
            List<Vol> vols = volDao.researchVols(idVilleDepart, idVilleArrivee, dateDepart, dateArrive);
            view.addObject("vols", vols);

        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la recherche des vols : " + e.getMessage());
        }

        return view;
    }

}

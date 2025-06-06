package mg.p16.java.controller;

import com.framework.annotations.GET;
import com.framework.annotations.POST;
import com.framework.annotations.Param;

import java.util.ArrayList;
import java.util.List;

import com.framework.annotations.AnnotationController;
import com.framework.model.ModelView;
import mg.p16.java.dao.*;
import mg.p16.java.model.*;

@AnnotationController
public class PromotionController {

    PromotionDAO promotionDao = new PromotionDAO();
    
     @GET("/managePromotions")
    public ModelView managePromotions() {
        ModelView view = new ModelView("/web/jsp/backoffice/managePromotions.jsp");
        // try {
        // List<Promotion> listpromo = promotionDao.findAll();
        //     view.addObject("listpromo",listpromo);
        // } catch (Exception e) {
        //      System.out.println("Erreur lors de l'ajout de la promotion : " + e.getMessage());
        //     e.printStackTrace(); // Affiche la pile d'erreurs complète dans la console
        //     view = new ModelView("/web/jsp/backoffice/managePromotions.jsp");
        //     view.addObject("error", "Erreur lors de la mise en page de la promotion : " + e.getMessage());
        // }
        return view;
    }
     @GET("/goListPromotions")
    public ModelView goListPromotions() {
       ModelView view = new ModelView("/web/jsp/backoffice/ListPromotions.jsp");
        try {
        List<Promotion> listpromo = promotionDao.findAll();
            view.addObject("listpromo",listpromo);
        } catch (Exception e) {
             System.out.println("Erreur lors de l'ajout de la promotion : " + e.getMessage());
            e.printStackTrace(); // Affiche la pile d'erreurs complète dans la console
            view = new ModelView("/web/jsp/backoffice/managePromotions.jsp");
            view.addObject("error", "Erreur lors de la mise en page de la promotion : " + e.getMessage());
        }
        return view;
    }
    @GET("/deletePromotion")
    public ModelView deletePromotion(
            @Param(name = "id") String idstr) {
        ModelView view = new ModelView("/web/jsp/backoffice/managePromotions.jsp");
        try {
            int id = Integer.parseInt(idstr);
            promotionDao.deleteById(id);
            view.addObject("success", "La promotion a été supprime avec succès !");
        } catch (Exception e) {
            view.addObject("error", "Erreur lors de la suppression de la promotion : " + e.getMessage());
        }
        return view;
    }
    
    @GET("/addPromotions")
    public ModelView addPromotions(
            @Param(name = "idVol") String idVolStr,
            @Param(name = "idTypeSiege") String typeSiege,
            @Param(name = "pourcentageReduction") String pourcentageReductionStr,
            @Param(name = "nombrePlacesPromo") String nombrePlacesPromoStr) {
        ModelView view = new ModelView("/web/jsp/backoffice/managePromotions.jsp");
        try {
            int idVol = Integer.parseInt(idVolStr);
            double pourcentageReduction = Double.parseDouble(pourcentageReductionStr);
            int nombrePlacesPromo = Integer.parseInt(nombrePlacesPromoStr);
            int typeSiegeint = Integer.parseInt(typeSiege);
            Promotion promotion = new Promotion(idVol, typeSiegeint, pourcentageReduction, nombrePlacesPromo);
            promotionDao.addPromotion(promotion);
            view.addObject("success", "La promotion a été ajoutée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de la promotion : " + e.getMessage());
            e.printStackTrace(); // Affiche la pile d'erreurs complète dans la console

            view.addObject("error", "Erreur lors de l'ajout de la promotion : " + e.getMessage());
        }
        return view;
    }
}

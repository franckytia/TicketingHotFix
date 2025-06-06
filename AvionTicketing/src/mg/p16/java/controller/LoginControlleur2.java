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
public class LoginControlleur2 {

    MySession session; // La session doit être initialisée via ton framework

    @GET("/")
    @Public
    public ModelView showLoginForm() {
        ModelView view = new ModelView("web/jsp/frontoffice/login.jsp");
        view.addObject("message", "Veuillez vous connecter pour continuer.");
        return view;
    }
    @GET("/logout")
    @Authentificated
    public ModelView logout() {
        session.delete("user");
        session.delete("role");
        ModelView view = new ModelView("web/jsp/frontoffice/login.jsp");
        view.addObject("message", "Vous avez été déconnecté.");
        return view;
    }
    @GET("/managePromotions")
    @Authentificated
    public ModelView managePromotions() {
        ModelView view = new ModelView("/web/jsp/backoffice/managePromotions.jsp");
        view.addObject("message", "Vous etes allez dans managePromotions.");
        return view;
    }
    // @GET("/flightForm")
    // @Authentificated
    // public ModelView flightForm() {
    //     ModelView view = new ModelView("/web/jsp/backoffice/flightForm.jsp");
    //     view.addObject("id", "Ajouter un vol");
    //     return view;
    // }   
    @GET("/adminDashboard")
    @Authentificated
    public ModelView adminDashboard() {
        ModelView view = new ModelView("/web/jsp/backoffice/adminDashboard.jsp");
        return view;
    }
    
    @GET("/parameter")
    @Authentificated
    public ModelView parameter() {
        ModelView view = new ModelView("/web/jsp/backoffice/parameter.jsp");
        return view;
    }
    @GET("/home")
    @Authentificated
    public ModelView home() {
        ModelView view = new ModelView("/web/jsp/frontoffice/home.jsp");
        return view;
    }
}

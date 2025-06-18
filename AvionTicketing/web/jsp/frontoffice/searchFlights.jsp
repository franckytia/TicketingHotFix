<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mg.p16.java.model.*" %>
<%@ page import="mg.p16.java.dao.*" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Forms - Kaiadmin Bootstrap 5 Admin Dashboard</title>
    <meta
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
      name="viewport"
    />
    <link
      rel="icon"
      href="${pageContext.request.contextPath}/web/jsp/static/assets/img/kaiadmin/favicon.ico"
      type="image/x-icon"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css">
    <!-- Fonts and icons -->
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/plugin/webfont/webfont.min.js"></script>
    <script>
      WebFont.load({
        google: { families: ["Public Sans:300,400,500,600,700"] },
        custom: {
          families: [
            "Font Awesome 5 Solid",
            "Font Awesome 5 Regular",
            "Font Awesome 5 Brands",
            "simple-line-icons",
          ],
          urls: ["${pageContext.request.contextPath}/web/jsp/static/assets/css/fonts.min.css"],
        },
        active: function () {
          sessionStorage.fonts = true;
        },
      });
    </script>

    <!-- CSS Files -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" />

    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/jsp/static/assets/css/demo.css" />
  </head>
  <body>
    <section class="bg-light p-3 p-md-4 p-xl-5">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-12 col-xxl-11">
              <div class="card border-light-subtle shadow-sm">
                <div class="row g-0">
                  <div class="col-12 col-md-6">
                    <img class="img-fluid rounded-start w-100 h-100 object-fit-cover" loading="lazy" src="${pageContext.request.contextPath}/web/jsp/static/assets/img/undraw/undraw_not_found_60pq.svg" alt="Welcome back you've been missed!">
                  </div>
                  <div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
                    <div class="col-12 col-lg-11 col-xl-10">
                      <div class="card-body p-3 p-md-4 p-xl-5">
                        <div class="row">
                          <div class="col-12">
                            <div class="mb-5">
                              <div class="text-center mb-4">
                                <a href="#!">
                                  <img src="${pageContext.request.contextPath}/web/jsp/static/assets/img/undraw/undraw_not_found_60pq.svg" alt="BootstrapBrain Logo" width="175" height="57">
                                </a>
                              </div>
                              <h4 class="text-center">Rechercher un vol</h4>
                            </div>
                          </div>
                        </div>    
                            <form action="SearchFlightK" method="get">
                                <div class="form-group">
                                <label for="defaultSelect">Ville de départ :</label>
                                <select name="idVilleDepart" class="form-select form-control"
                                id="defaultSelect">
                                    <option value="">Sélectionner une ville</option>
                                    <% 
                                        List<Ville> villes = (List<Ville>) request.getAttribute("villes");
                                        if (villes != null) {
                                            for (Ville ville : villes) { 
                                    %>
                                        <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
                                    <% 
                                            }
                                        }
                                    %>
                                </select>
                                </div>
                                <br/>

                                <div class="form-group">
                                    <label for="defaultSelect">Ville d'arrivée :</label>
                                <select class="form-select form-control"
                                id="defaultSelect" name="idVilleArrivee">
                                    <option value="">Sélectionner une ville</option>
                                    <% 
                                        if (villes != null) {
                                            for (Ville ville : villes) { 
                                    %>
                                        <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
                                    <% 
                                            }
                                        }
                                    %>
                                </select>
                                </div>
                                <br/>
                                <div class="mb-3">
                                    <label for="dateDepart" class="form-label">Date de départ :</label>
                                    <input type="datetime-local" id="dateDepart" name="dateDepart" class="form-control"  />
                                </div>
                                <div class="mb-3">
                                    <label for="dateDepart" class="form-label">Date d'arrivée :</label>
                                    <input type="datetime-local" id="dateDepart" name="dateArrive" class="form-control"  />
                                </div>
                                <input type="submit" class="btn btn-success" value="Rechercher" />
                            </form>    
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</section>
</body>
</html>
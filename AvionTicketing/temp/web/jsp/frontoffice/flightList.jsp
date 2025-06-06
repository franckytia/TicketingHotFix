<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mg.p16.java.model.*" %>
<%@ page import="mg.p16.java.dao.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Datatables - Kaiadmin Bootstrap 5 Admin Dashboard</title>
    <meta
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
      name="viewport"
    />
    <link
      rel="icon"
      href="${pageContext.request.contextPath}/web/jsp/static/assets/img/kaiadmin/favicon.ico"
      type="image/x-icon"
    />

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

    <!-- Datatables CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/jsp/static/assets/css/datatables.min.css" />

    <!-- JS Files -->
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/plugin/datatables/datatables.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/kaiadmin.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/setting-demo2.js"></script>
</head>
<body>
    <div class="wrapper">
    <div class="container">
        <!-- <div class="page-inner"> -->
            <div class="row">
                <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                    <h4 class="card-title">Liste des vols</h4>
                    <div class="ms-md-auto py-2 py-md-0">
                        <a href="searchFlights" class="btn btn-label-info btn-round me-2">Rechercher multi-critere vol</a>
                      </div>
                    </div>
                    <% 
                        String error = (String) request.getAttribute("error");
                        if (error != null) {
                    %>
                        <p style="color: red;"><%= error %></p>
                    <% 
                        }
                    %>
                    <div class="card-body">
                    <div class="table-responsive">
                        <table id="basic-datatables" class="display table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Numéro de vol</th>
                                    <th>Date de départ</th>
                                    <th>Date d'arrivée</th>
                                    <th>Pays Départ</th>
                                    <th>Pays Arrivée</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Numéro de vol</th>
                                    <th>Date de départ</th>
                                    <th>Date d'arrivée</th>
                                    <th>Pays Départ</th>
                                    <th>Pays Arrivée</th>
                                    <th>Action</th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <% 
                                    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
                                    if (vols != null && !vols.isEmpty()) {
                                        for (Vol vol : vols) {
                                %>
                                <tr>
                                    <td><%= vol.getNumeroVol() %></td>
                                    <td><%= vol.getDateDepart() %></td>
                                    <td><%= vol.getDateArrivee() %></td>
                                    <td><%= vol.getVilleDepart() %></td>
                                    <td><%= vol.getVilleArrivee() %></td>
                                    <td>
                                        <form action="reserve" method="get">
                                            <input type="hidden" name="id" value="<%= vol.getId() %>">
                                            <button type="submit" class="btn btn-success btn-sm">Réserver</button>
                                        </form>
                                    </td>
                                </tr>
                                <% 
                                        }
                                    } else { 
                                %>
                                <tr>
                                    <td colspan="6" style="text-align: center;">Aucun vol trouvé.</td>
                                </tr>
                                <% 
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    </div>
                </div>
                </div>
            </div>
        <!-- </div> -->
    </div>
    <footer class="footer">
        <div class="container-fluid d-flex justify-content-between">
          <nav class="pull-left">
            <ul class="nav">
              <li class="nav-item">
                <a class="nav-link" href="http://www.themekita.com">
                  ThemeKita
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#"> Help </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#"> Licenses </a>
              </li>
            </ul>
          </nav>
          <div class="copyright">
            2024, made with <i class="fa fa-heart heart text-danger"></i> by
            <a href="http://www.themekita.com">ThemeKita</a>
          </div>
          <div>
            Distributed by
            <a target="_blank" href="https://themewagon.com/">ThemeWagon</a>.
          </div>
        </div>
    </footer>
    </div>
<script>
    $(document).ready(function () {
        // Initialisation de la table DataTables
        $("#basic-datatables").DataTable({
            pageLength: 10,
            responsive: true,
            language: {
                url: "//cdn.datatables.net/plug-ins/1.11.5/i18n/fr_fr.json" // Traduction en français
            }
        });
    });
</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mg.p16.java.model.Vol"%>
<%@ page import="mg.p16.java.model.CategorieAge"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails du vol</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">
<%
    Vol vol = (Vol) request.getAttribute("vol");
    if (vol == null) {
%>
            <div class="alert alert-danger" role="alert">
                <p><%= request.getAttribute("error") %></p>
                <p>Vol non trouvé.</p>
            </div>
            <a href="searchFlights" class="btn btn-primary">Retour à la recherche</a>
<%
    } else {
%>
            <h2 class="text-center mb-4">Détails du vol</h2>
            <ul class="list-group mb-4">
                <li class="list-group-item"><strong>ID :</strong> <%= vol.getId() %></li>
                <li class="list-group-item"><strong>Numéro de vol :</strong> <%= vol.getNumeroVol() %></li>
                <li class="list-group-item"><strong>Date de départ :</strong> <%= vol.getDateDepart() %></li>
                <li class="list-group-item"><strong>Date d'arrivée :</strong> <%= vol.getDateArrivee() %></li>
                <li class="list-group-item"><strong>Prix de base :</strong> <%= vol.getPrixBase() %></li>
            </ul>

            <h3 class="text-center mb-4">Réserver ce vol</h3>
            <form action="doReservation" method="post" class="needs-validation" novalidate>
                <!-- idVol en champ caché -->
                <input type="hidden" name="idVol" value="<%= vol.getId() %>" />


                <div class="mb-3">
                    <label for="porteur" class="form-label">Au porteur du CIN :</label>
                    <input type="file" id="porteur" name="porteur" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="nom" class="form-label">Au nom de :</label>
                    <input type="text" id="nom" name="nom" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="cin" class="form-label">CIN :</label>
                    <input type="text" id="cin" name="cin" class="form-control" maxlength="14" required />
                </div>

                <div class="mb-3">
                    <label for="date" class="form-label">Date de réservation :</label>
                    <input type="datetime-local" id="date" name="date" class="form-control" required />
                </div>

                <h4 class="mb-3">Nombre de personnes par catégorie :</h4>
<%
        List<CategorieAge> categories = (List<CategorieAge>) request.getAttribute("categories");
        if (categories != null && !categories.isEmpty()) {
            for (CategorieAge cat : categories) {
%>
                <div class="mb-3">
                    <!-- Pour chaque catégorie, on insère un champ caché pour l'identifiant -->
                    <input type="hidden" name="categorieIds" value="<%= cat.getId() %>" />
                    <label class="form-label">
                        <%= cat.getDescription() %> (de <%= cat.getAgeMin() %> à <%= cat.getAgeMax() %> ans, Coefficient : <%= cat.getCoefficient() %>)
                    </label>
                    <!-- Le champ input en tableau pour saisir le total de personnes -->
                    <input type="number" name="totals" class="form-control" min="0" value="0" />
                </div>
<%
            }
        }
%>
                <div class="d-grid">
                    <button type="submit" class="btn btn-success btn-lg">Réserver</button>
                </div>
            </form>
            <br/>
            <a href="SearchFlightK?idVilleDepart=&idVilleArrivee=&dateDepart=&dateArrive=" class="btn btn-secondary">Retour à la liste des vols</a>
<%
    }
%>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>

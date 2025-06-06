<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mg.p16.java.model.Vol" %>
<%@ page import="mg.p16.java.model.Ville" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= request.getParameter("id") != null ? "Modifier le vol" : "Ajouter un vol" %></title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">
            <h2 class="text-center mb-4"><%= request.getParameter("id") != null ? "Modifier le vol" : "Ajouter un vol" %></h2>
            <form action="<%= request.getParameter("id") != null ? "updateFlight" : "addFlight" %>" method="post" class="needs-validation" novalidate>
                <% if(request.getParameter("id") != null){ %>
                    <input type="hidden" name="id" value="<%= request.getParameter("id") %>" />
                <% } %>

                <div class="mb-3">
                    <label for="numeroVol" class="form-label">Numéro de vol :</label>
                    <input type="text" id="numeroVol" name="numeroVol" class="form-control" 
                           value="<%= request.getAttribute("vol") != null ? ((Vol)request.getAttribute("vol")).getNumeroVol() : "" %>" required />
                </div>

                <div class="mb-3">
                    <label for="idAvion" class="form-label">ID Avion :</label>
                    <input type="number" id="idAvion" name="idAvion" class="form-control" 
                           value="<%= request.getAttribute("vol") != null ? ((Vol)request.getAttribute("vol")).getIdAvion() : "" %>" required />
                </div>

                <div class="mb-3">
                    <label for="idVilleDepart" class="form-label">Ville de départ :</label>
                    <select id="idVilleDepart" name="idVilleDepart" class="form-select" required>
                        <option value="">Sélectionner une ville</option>
                        <% 
                            List<Ville> villes = (List<Ville>) request.getAttribute("villes");
                            if (villes != null) {
                                for (Ville ville : villes) { 
                        %>
                            <option value="<%= ville.getId() %>" 
                                <%= request.getAttribute("vol") != null && ((Vol)request.getAttribute("vol")).getIdVilleDepart() == ville.getId() ? "selected" : "" %>>
                                <%= ville.getNom() %>
                            </option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="idVilleArrivee" class="form-label">Ville d'arrivée :</label>
                    <select id="idVilleArrivee" name="idVilleArrivee" class="form-select" required>
                        <option value="">Sélectionner une ville</option>
                        <% 
                            if (villes != null) {
                                for (Ville ville : villes) { 
                        %>
                            <option value="<%= ville.getId() %>" 
                                <%= request.getAttribute("vol") != null && ((Vol)request.getAttribute("vol")).getIdVilleArrivee() == ville.getId() ? "selected" : "" %>>
                                <%= ville.getNom() %>
                            </option>
                        <% 
                                }
                            }
                        %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="dateDepart" class="form-label">Date de départ :</label>
                    <input type="datetime-local" id="dateDepart" name="dateDepart" class="form-control" 
                           value="<%= request.getAttribute("vol") != null ? ((Vol)request.getAttribute("vol")).getDateDepart() : "" %>" required />
                </div>

                <div class="mb-3">
                    <label for="dateArrivee" class="form-label">Date d'arrivée :</label>
                    <input type="datetime-local" id="dateArrivee" name="dateArrivee" class="form-control" 
                           value="<%= request.getAttribute("vol") != null ? ((Vol)request.getAttribute("vol")).getDateArrivee() : "" %>" required />
                </div>

                <div class="mb-3">
                    <label for="prixBase" class="form-label">Prix de base :</label>
                    <input type="number" step="0.01" id="prixBase" name="prixBase" class="form-control" 
                           value="<%= request.getAttribute("vol") != null ? ((Vol)request.getAttribute("vol")).getPrixBase() : "" %>" required />
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">
                        <%= request.getParameter("id") != null ? "Modifier" : "Ajouter" %>
                    </button>
                </div>
            </form>
            <div class="mt-3 text-center">
                <a href="manageFlights" class="btn btn-secondary">Retour à la liste des vols</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>

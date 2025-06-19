<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mg.p16.java.model.Reservation" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des réservations</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">
            <h2 class="text-center mb-4">Tableau des enregistrements</h2>
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>ID Vol</th>
                        <th>Nom</th>
                        <th>CIN</th>
                        <th>Date réservation</th>
                        <th>Prix total</th>
                        <th>Statut</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                        if (reservations != null && !reservations.isEmpty()) {
                            for (Reservation res : reservations) {
                    %>
                    <tr>
                        <td><%= res.getId() %></td>
                        <td><%= res.getIdVol() %></td>
                        <td><%= res.getAuNom() %></td>
                        <td><%= res.getCIN() %></td>
                        <td><%= res.getDateReservation() %></td>
                        <td><%= res.getPrixTotal() %></td>
                        <td><%= res.getStatut() %></td>
                        <td>
                            <form action="validate" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= res.getId() %>">
                                <button type="submit" class="btn btn-success btn-sm">Valider</button>
                            </form>
                            <form action="reject" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= res.getId() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Rejeter</button>
                            </form>
                        </td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="8" class="text-center">Aucune réservation trouvée.</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
            <div class="mt-3 text-center">
                <a href="adminDashboard.jsp" class="btn btn-secondary">Retour au Dashboard</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>


</html></body></body>
</html>

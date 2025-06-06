<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mg.p16.java.model.*" %>
<%@ page import="mg.p16.java.dao.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des vols</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">
            <h2 class="text-center mb-4">Liste des vols</h2>
            <div class="mb-3 text-end">
                <a href="flightForm" class="btn btn-primary">Ajouter un vol</a>
            </div>
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Numéro de vol</th>
                        <th>Date de départ</th>
                        <th>Date d'arrivée</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Vol> vols = (List<Vol>) request.getAttribute("vols");
                        if (vols != null && !vols.isEmpty()) {
                            for (Vol vol : vols) {
                    %>
                    <tr>
                        <td><%= vol.getId() %></td>
                        <td><%= vol.getNumeroVol() %></td>
                        <td><%= vol.getDateDepart() %></td>
                        <td><%= vol.getDateArrivee() %></td>
                        <td>
                            <div class="d-flex gap-2">
                                <form action="flightForm" method="get">
                                    <input type="hidden" name="id" value="<%= vol.getId() %>">
                                    <button type="submit" class="btn btn-warning btn-sm">Modifier</button>
                                </form>
                                <form action="deleteFlight" method="get">
                                    <input type="hidden" name="id" value="<%= vol.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% 
                            }
                        } else { 
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Aucun vol trouvé.</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
            <div class="mt-3 text-center">
                <a href="adminDashboard" class="btn btn-secondary">Retour au Dashboard</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>

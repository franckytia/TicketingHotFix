<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="mg.p16.java.model.*" %>
<%@ page import="mg.p16.java.dao.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Paramétrage des promotions</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body">
            <h2 class="text-center mb-4">Gérer les promotions de vol</h2>

            <!-- Affichage des messages -->
            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    ${success}
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <form action="addPromotions" method="GET" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label for="idVol" class="form-label">ID Vol :</label>
                    <input type="number" id="idVol" name="idVol" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="idTypeSiege" class="form-label">Type de siège :</label>
                    <input type="number" id="idTypeSiege" name="idTypeSiege" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="pourcentageReduction" class="form-label">Pourcentage de réduction (%) :</label>
                    <input type="number" step="0.01" id="pourcentageReduction" name="pourcentageReduction" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label for="nombrePlacesPromo" class="form-label">Nombre de places en promo :</label>
                    <input type="number" id="nombrePlacesPromo" name="nombrePlacesPromo" class="form-control" required />
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Enregistrer la promotion</button>
                </div>
            </form>
            <div class="mt-3 text-center">
                <a href="adminDashboard" class="btn btn-secondary">Retour au Dashboard</a>
            </div>
            <div class="mt-3 text-center">
                <a href="goListPromotions?" class="btn btn-secondary">List Promo</a>
            </div>
        </div>
        </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>

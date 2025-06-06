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
        <div class="card shadow mt-4">
    <div class="card-body">
        <c:if test="${not empty listpromo}">
            <h4 class="text-center mb-4">Liste des promotions existantes</h4>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>ID Vol</th>
                            <th>ID Type Siège</th>
                            <th>Réduction (%)</th>
                            <th>Places Promo</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Promotion> promotions = (List<Promotion>) request.getAttribute("listpromo");
                            if (promotions != null && !promotions.isEmpty()) {
                                for (Promotion promo : promotions) {
                        %>
                        <tr>
                            <td><%= promo.getId() %></td>
                            <td><%= promo.getIdVol() %></td>
                            <td><%= promo.getIdTypeSiege() %></td>
                            <td><%= promo.getPourcentageReduction() %></td>
                            <td><%= promo.getNombrePlacesPromo() %></td>
                            <td>
                                <div class="d-flex gap-2">
                                    <form action="editPromotionForm" method="get">
                                        <input type="hidden" name="id" value="<%= promo.getId() %>">
                                        <button type="submit" class="btn btn-warning btn-sm">Modifier</button>
                                    </form>
                                    <form action="deletePromotion" method="get">
                                        <input type="hidden" name="id" value="<%= promo.getId() %>">
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
                            <td colspan="6" class="text-center">Aucune promotion enregistrée.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>

</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>

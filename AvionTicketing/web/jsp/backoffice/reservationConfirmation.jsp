<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmation de réservation</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow">
        <div class="card-body text-center">
<%
    if (request.getAttribute("error") != null) { 
%>
            <div class="alert alert-danger" role="alert">
                <p><%= request.getAttribute("error") %></p>
            </div>
<%
    } else { 
%>
            <div class="alert alert-success" role="alert">
                <h4 class="alert-heading">Réservation réussie !</h4>
                <p>Merci d'avoir réservé avec nous.</p>
                <p><%= request.getAttribute("message") %></p>

            </div>
<%
    } 
%>
            <a href="home" class="btn btn-primary mt-3">Retour à l'accueil</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>
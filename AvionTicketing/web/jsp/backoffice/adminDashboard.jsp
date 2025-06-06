<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Office - Dashboard</title>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/plugins.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/web/jsp/static/assets/css/kaiadmin.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <style>
        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #343a40;
            color: #fff;
            padding: 20px;
            width: 250px;
        }
        .sidebar h3 {
            font-size: 1.5rem;
            margin-bottom: 20px;
            text-align: center;
        }
        .sidebar a {
            color: #fff;
            text-decoration: none;
            display: block;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        .sidebar a:hover {
            background-color: #495057;
            padding-left: 15px;
        }
        .main-content {
            margin-left: 260px;
            padding: 20px;
        }
        .card {
            border: none;
            border-radius: 10px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }
        .btn-light {
            font-weight: bold;
        }
    </style>
</head>
<body class="bg-light">
    <!-- Sidebar -->
    <div class="sidebar">
        <h3>Back Office</h3>
        <a href="manageFlights"><i class="bi bi-airplane"></i> Gérer les vols (CRUD vol)</a>
        <a href="searchFlights"><i class="bi bi-search"></i> Recherche multi-critère vol</a>
        <a href="parameter"><i class="bi bi-gear"></i> Paramétrer</a>
        <a href="logout" class="text-danger"><i class="bi bi-box-arrow-right"></i> Déconnexion</a>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="container">
            <h2 class="mb-4">Tableau de bord administrateur</h2>
            <div class="row">
                <!-- Example Cards -->
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Gérer les vols</h5>
                            <p class="card-text">Ajoutez, modifiez ou supprimez des vols.</p>
                            <a href="manageFlights" class="btn btn-light">Voir plus</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Recherche multi-critère</h5>
                            <p class="card-text">Recherchez des vols selon plusieurs critères.</p>
                            <a href="searchFlights" class="btn btn-light">Voir plus</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-warning mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Paramétrer</h5>
                            <p class="card-text">Configurez les paramètres du système.</p>
                            <a href="parameter" class="btn btn-light">Voir plus</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/web/jsp/static/assets/js/core/bootstrap.bundle.min.js"></script>
</body>
</html>

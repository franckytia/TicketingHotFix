-- Insertion des utilisateurs
INSERT INTO Utilisateur (id, nom, prenom, email, password, role) VALUES
(1, 'Dupont', 'Jean', 'jean.dupont@aviation.fr', 'password123', 'admin'),
(2, 'Martin', 'Marie', 'marie.martin@aviation.fr', 'password456', 'admin'),
(3, 'Durand', 'Pierre', 'pierre.durand@aviation.fr', 'password789', 'client'),
(4, 'Dubois', 'Sophie', 'sophie.dubois@aviation.fr', 'password012', 'client');

-- Insertion des villes
INSERT INTO Ville (id, nom) VALUES
(1, 'Paris'),
(2, 'Lyon'),
(3, 'Marseille'),
(4, 'Nice'),
(5, 'Toulouse'),
(6, 'Bordeaux'),
(7, 'Strasbourg'),
(8, 'Nantes');

-- Insertion des avions
INSERT INTO Avion (id, modele, capacite, date_fabrication) VALUES
(1, 'Airbus A320', 150, '2018-05-15'),
(2, 'Boeing 737', 160, '2019-08-20'),
(3, 'Airbus A321', 200, '2020-03-10');

-- Insertion des types de sièges
INSERT INTO TypeSiege (id, nom, coefficient_prix) VALUES
(1, 'Economique', 1.00),
(2, 'Premium Economique', 1.50),
(3, 'Business', 2.75),
(4, 'Première Classe', 4.00);

-- Insertion des vols
INSERT INTO Vol (id, numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_base) VALUES
(1, 'AF101', 1, 1, 2, '2025-03-05 08:00:00', '2025-03-05 09:30:00', 150.00),
(4, 'AF104', 1, 2, 1, '2025-03-05 10:00:00', '2025-03-05 11:30:00', 150.00),
(3, 'AF103', 2, 1, 3, '2025-03-05 12:00:00', '2025-03-05 13:40:00', 180.00);

-- Insertion des réservations
INSERT INTO Reservation (id, id_vol, id_Utilisateur, id_type_siege, date_reservation, prix_final, statut) VALUES
(1, 1, 3, 1, '2025-03-04 10:00:00', 150.00, 'Confirmé'),
(2, 1, 4, 2, '2025-03-04 11:00:00', 225.00, 'Confirmé'),
(3, 2, 3, 3, '2025-03-04 12:00:00', 412.50, 'En attente');

-- Insertion des promotions
INSERT INTO Promotion (id, id_vol, type_siege, pourcentage_reduction, nombre_places_promo) VALUES
(1, 1, 'Economique', 20.00, 30),
(2, 2, 'Premium Economique', 15.00, 20),
(3, 3, 'Business', 25.00, 10);
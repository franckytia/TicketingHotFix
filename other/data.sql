TRUNCATE TABLE promotion RESTART IDENTITY CASCADE;
TRUNCATE TABLE reservation_personne RESTART IDENTITY CASCADE;
TRUNCATE TABLE reservation_detail RESTART IDENTITY CASCADE;
TRUNCATE TABLE reservation RESTART IDENTITY CASCADE;
TRUNCATE TABLE vol RESTART IDENTITY CASCADE;
TRUNCATE TABLE avion RESTART IDENTITY CASCADE;
TRUNCATE TABLE categorie_age RESTART IDENTITY CASCADE;
TRUNCATE TABLE typesiege RESTART IDENTITY CASCADE;
TRUNCATE TABLE utilisateur RESTART IDENTITY CASCADE;
TRUNCATE TABLE ville RESTART IDENTITY CASCADE;

-- Insertion des villes
INSERT INTO ville (id, nom) VALUES
(1, 'Antananarivo'),
(2, 'Paris'),
(3, 'New York');

-- Insertion des avions
INSERT INTO avion (id, modele, capacite, date_fabrication) VALUES
(1, 'Airbus A320', 150, '2018-05-15'),
(2, 'Boeing 737', 160, '2019-08-20'),
(3, 'Airbus A321', 200, '2020-03-10');

-- Insertion des types de siège
INSERT INTO typesiege (id, nom, coefficient_prix) VALUES
(1, 'Economique', 1.0),
(2, 'Premium Economique', 1.2),
(3, 'Business', 1.5);

-- Insertion des catégories d'âge
INSERT INTO categorie_age (id, age_max, age_min, description, coefficient, prix_billet) VALUES
(1, 12, 0, 'Enfant', 0.50, 75.00),
(2, 59, 13, 'Adulte', 1.00, 150.00),
(3, 120, 60, 'Senior', 0.80, 120.00);

-- Insertion des utilisateurs
INSERT INTO utilisateur (id, nom, prenom, email, password, role) VALUES
(1, 'Admin', 'Super', 'admin@you', 'adminpass', 'admin'),
(2, 'User', 'Normal', 'user@you', 'userpass', 'client');

-- Insertion des vols
INSERT INTO vol (id, numero_vol, id_avion, id_ville_depart, id_ville_arrivee, date_depart, date_arrivee, prix_base, id_cat_age) VALUES
(1, 'AF101', 1, 1, 2, '2025-03-05 08:00:00', '2025-03-05 09:30:00', 150.00, 2),
(2, 'AF102', 2, 1, 3, '2025-03-05 12:00:00', '2025-03-05 13:40:00', 180.00, 2),
(3, 'AF103', 1, 2, 1, '2025-03-05 10:00:00', '2025-03-05 11:30:00', 150.00, 2);

-- Insertion des promotions (id_type_siege au lieu de type_siege)
INSERT INTO promotion (id, id_vol, pourcentage_reduction, nombre_places_promo, id_type_siege) VALUES
(1, 1, 20.00, 30, 1),
(2, 2, 15.00, 20, 2),
(3, 3, 25.00, 10, 3);

-- Les insertions pour reservation, reservation_detail, reservation_personne viendront après si besoin.





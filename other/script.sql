create database gestion_avion;
\c gestion_avion;

CREATE TABLE ville (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE avion (
    id SERIAL PRIMARY KEY,
    modele VARCHAR(100) NOT NULL,
    capacite INTEGER NOT NULL,
    date_fabrication DATE NOT NULL
);

CREATE TABLE typesiege (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE,
    coefficient_prix DOUBLE PRECISION NOT NULL
);

CREATE TABLE categorie_age (
    id SERIAL PRIMARY KEY,
    age_max INTEGER,
    age_min INTEGER,
    description VARCHAR(255),
    coefficient DOUBLE PRECISION,
    prix_billet DOUBLE PRECISION
);

CREATE TABLE utilisateur (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL CHECK (role IN ('admin', 'client'))
);

CREATE TABLE vol (
    id SERIAL PRIMARY KEY,
    numero_vol VARCHAR(50) NOT NULL UNIQUE,
    id_avion INTEGER REFERENCES avion(id) ON DELETE CASCADE,
    id_ville_depart INTEGER REFERENCES ville(id) ON DELETE CASCADE,
    id_ville_arrivee INTEGER REFERENCES ville(id) ON DELETE CASCADE,
    date_depart TIMESTAMP NOT NULL,
    date_arrivee TIMESTAMP NOT NULL,
    prix_base NUMERIC(10,2) NOT NULL,
    id_cat_age INTEGER REFERENCES categorie_age(id)
);

CREATE TABLE promotion (
    id SERIAL PRIMARY KEY,
    id_vol INTEGER REFERENCES vol(id) ON DELETE CASCADE,
    pourcentage_reduction NUMERIC(5,2) NOT NULL,
    nombre_places_promo INTEGER NOT NULL,
    id_type_siege INTEGER REFERENCES typesiege(id)
);

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    id_vol INTEGER REFERENCES vol(id) ON DELETE CASCADE,
    au_nom VARCHAR(200) NOT NULL,
    cin INTEGER NOT NULL,
    date_reservation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut VARCHAR(20) DEFAULT 'En attente' CHECK (statut IN ('Confirmé', 'Annulé', 'En attente')),
    prix_total DOUBLE PRECISION
);

CREATE TABLE reservation_personne (
    id SERIAL PRIMARY KEY,
    id_reservation INTEGER REFERENCES reservation(id) ON DELETE CASCADE,
    id_categorie_peronne INTEGER REFERENCES categorie_age(id) ON DELETE CASCADE,
    total INTEGER
);

CREATE TABLE reservation_detail (
    id SERIAL PRIMARY KEY,
    id_reservation INTEGER NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
    id_type_siege INTEGER NOT NULL REFERENCES typesiege(id),
    nom_passager VARCHAR(100) NOT NULL,
    passport VARCHAR(50) NOT NULL,
    remarque VARCHAR(255),
    taille DOUBLE PRECISION
);
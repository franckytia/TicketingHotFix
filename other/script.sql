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





-----trigger
-- xplications
-- Ce trigger se déclenche après chaque INSERT, UPDATE ou DELETE sur reservation_detail.
-- Il recalcule le total pour la réservation concernée en prenant en compte :
-- le prix de base du vol,
-- le coefficient du type de siège,
-- le coefficient de la catégorie d’âge (si renseigné dans reservation_personne),
-- la promotion éventuelle.
-- Il met à jour le champ prix_total dans la table reservation.
CREATE OR REPLACE FUNCTION maj_prix_total_reservation()
RETURNS TRIGGER AS $$
DECLARE
    total DOUBLE PRECISION := 0;
BEGIN
    -- Calcule le total pour la réservation concernée
    SELECT
        COALESCE(SUM(
            v.prix_base
            * ts.coefficient_prix
            * ca.coefficient
            * (1 - COALESCE(p.pourcentage_reduction, 0) / 100)
        ), 0)
    INTO total
    FROM reservation_detail rd
    JOIN reservation r ON rd.id_reservation = r.id
    JOIN vol v ON r.id_vol = v.id
    JOIN typesiege ts ON rd.id_type_siege = ts.id
    LEFT JOIN reservation_personne rp ON rp.id_reservation = r.id
    LEFT JOIN categorie_age ca ON rp.id_categorie_peronne = ca.id
    LEFT JOIN promotion p ON p.id_vol = r.id_vol AND p.id_type_siege = rd.id_type_siege
    WHERE rd.id_reservation = NEW.id_reservation;

    -- Met à jour le prix_total dans la table reservation
    UPDATE reservation
    SET prix_total = total
    WHERE id = NEW.id_reservation;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Création du trigger sur INSERT et UPDATE sur reservation_detail
DROP TRIGGER IF EXISTS maj_prix_total_reservation_trigger ON reservation_detail;

CREATE TRIGGER maj_prix_total_reservation_trigger
AFTER INSERT OR UPDATE OR DELETE ON reservation_detail
FOR EACH ROW
EXECUTE FUNCTION maj_prix_total_reservation();



-- Ce trigger :

-- Vérifie la capacité de l’avion du vol lié à la réservation.
-- Compte le nombre de passagers déjà enregistrés pour ce vol.
-- Empêche l’insertion si la capacité est dépassée, avec un message d’erreur explicite.

CREATE OR REPLACE FUNCTION check_surplus_passager()
RETURNS TRIGGER AS $$
DECLARE
    capacite_avion INTEGER;
    nb_passagers INTEGER;
    vol_id INTEGER;
BEGIN
    -- Récupère l'id du vol lié à la réservation
    SELECT id_vol INTO vol_id FROM reservation WHERE id = NEW.id_reservation;

    -- Récupère la capacité de l'avion pour ce vol
    SELECT a.capacite
    INTO capacite_avion
    FROM vol v
    JOIN avion a ON v.id_avion = a.id
    WHERE v.id = vol_id;

    -- Compte le nombre de passagers déjà enregistrés pour ce vol
    SELECT COUNT(rd.id)
    INTO nb_passagers
    FROM reservation_detail rd
    JOIN reservation r ON rd.id_reservation = r.id
    WHERE r.id_vol = vol_id;

    -- Si on est en INSERT, on ajoute 1 (le nouveau passager)
    IF (TG_OP = 'INSERT') THEN
        nb_passagers := nb_passagers + 1;
    END IF;

    -- Si le nombre dépasse la capacité, on bloque l'insertion
    IF nb_passagers > capacite_avion THEN
        RAISE EXCEPTION 'Impossible d''ajouter le passager : capacité maximale de l''avion atteinte (%/%).', nb_passagers, capacite_avion;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Création du trigger sur INSERT sur reservation_detail
DROP TRIGGER IF EXISTS check_surplus_passager_trigger ON reservation_detail;

CREATE TRIGGER check_surplus_passager_trigger
BEFORE INSERT ON reservation_detail
FOR EACH ROW
EXECUTE FUNCTION check_surplus_passager();

 SELECT
      r.id AS reservation_id,
      r.id_vol,
      r.au_nom,
      r.CIN,
      r.date_reservation,
      r.statut,
      r.prix_total,
      -- Informations sur les détails individuels (passagers)
      rd.id AS reservation_detail_id,
      rd.nom_passager,
      rd.taille,
      rd.passport,
      rd.remarque,
      ts.id AS type_siege_id,
      ts.nom AS type_siege,
      ts.coefficient_prix,
      -- Informations sur la répartition par catégories
      rp.total AS nbr_personne,
      ca.description AS categorie_personne
  FROM Reservation r
  LEFT JOIN Reservation_detail rd ON r.id = rd.id_reservation
  LEFT JOIN TypeSiege ts ON rd.id_type_siege = ts.id
  LEFT JOIN Reservation_personne rp ON r.id = rp.id_reservation
  LEFT JOIN categorie_age ca ON rp.id_categorie_peronne = ca.id
  WHERE r.id = 3;
package com.aviation.repository;

import com.aviation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées si nécessaire
}

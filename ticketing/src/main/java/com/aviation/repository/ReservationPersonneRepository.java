package com.aviation.repository;

import com.aviation.model.ReservationPersonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationPersonneRepository extends JpaRepository<ReservationPersonne, Integer> {
    List<ReservationPersonne> findByIdReservation(Integer idReservation);
}

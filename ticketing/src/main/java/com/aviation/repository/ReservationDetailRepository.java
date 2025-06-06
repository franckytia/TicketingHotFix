package com.aviation.repository;

import com.aviation.model.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
    List<ReservationDetail> findByIdReservation(Integer idReservation);
}

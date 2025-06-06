package com.aviation.service;

import com.aviation.model.Reservation;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findById(Integer id);
}

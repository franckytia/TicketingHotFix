package com.aviation.service;

import com.aviation.model.ReservationDetail;
import com.aviation.repository.ReservationDetailRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationDetailService {

    private final ReservationDetailRepository repository;

    public ReservationDetailService(ReservationDetailRepository repository) {
        this.repository = repository;
    }

    public ReservationDetail save(ReservationDetail detail) {
        return repository.save(detail);
    }

    public List<ReservationDetail> getByReservationId(Integer idReservation) {
        return repository.findByIdReservation(idReservation);
    }

    public List<ReservationDetail> getAll() {
        return repository.findAll();
    }
}

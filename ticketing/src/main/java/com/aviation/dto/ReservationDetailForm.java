package com.aviation.dto;

import java.util.List;

public class ReservationDetailForm {
    private Integer idReservation;
    private List<ReservationDetailDTO> details;

    // Getters et Setters
    public Integer getIdReservation() { return idReservation; }
    public void setIdReservation(Integer idReservation) { this.idReservation = idReservation; }
    public List<ReservationDetailDTO> getDetails() { return details; }
    public void setDetails(List<ReservationDetailDTO> details) { this.details = details; }
}

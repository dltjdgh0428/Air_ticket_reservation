package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface MyTicketRepository {


    List<ReservationEntity> findAllReservation();

    List<ReservationEntity> findCondReservation(ReservationEntity reservationEntity);
    Optional<ReservationEntity> findByIdReservation(Long ticketId);

    void delete(ReservationEntity reservationEntity);
}

package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface BuyTicketRepository {

    List<JourneyEntity> findAllJourney();

    List<JourneyEntity> findCondJourney(JourneyEntity journeyEntity);
    Optional<JourneyEntity> findByIdJourney(Long ticketId);
    List<String> findBuyTicketById(Long journey_id);
    JourneyEntity findByIdJourney2(Long ticketId);
    void saveReservation(ReservationEntity reservation);
}

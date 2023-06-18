package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.JourneyEntity;

import java.util.List;
import java.util.Optional;

public interface BuyTicketRepository {

    List<JourneyEntity> findAllJourney();

    List<JourneyEntity> findCondJourney(JourneyEntity journeyEntity);
    List<String> findByIdJourney(Long ticketId);
    List<String> findBuyTicketById(Long journey_id);
}

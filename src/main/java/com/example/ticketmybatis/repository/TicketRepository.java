package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.AirportEntity;
import com.example.ticketmybatis.entity.JourneyEntity;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    List<AirportEntity> findAll();
    Optional<AirportEntity> findById(Long ticketId);
    List<JourneyEntity> findCond(Long d_city, Long a_city, String d_time, String a_time);
}

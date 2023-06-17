package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.ReservationEntity;

import java.util.List;
import java.util.Optional;

public interface MybatisTicketRepository {
    void save(ReservationEntity ticketEntity);

    List<ReservationEntity> findAll();
    List<ReservationEntity> findCond(ReservationEntity ticketEntity);
    Optional<ReservationEntity> findById(Long ticketId);

    void update(ReservationEntity ticketEntity);
    void delete(ReservationEntity ticketEntity);
}
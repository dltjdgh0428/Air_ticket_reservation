package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.AirportEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisTicketRepository {
    void save(AirportEntity var1);
//
//    List<TicketEntity> findAll();
//
//    List<TicketEntity> findCond(TicketEntity var1);
//
//    Optional<TicketEntity> findById(Long var1);
//
//    void update(TicketEntity var1);
//
//    void delete(BookEntity var1);

}

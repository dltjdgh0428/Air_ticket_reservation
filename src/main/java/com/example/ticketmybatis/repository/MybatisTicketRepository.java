package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.AirportEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisTicketRepository {
    void save(AirportEntity var1);

}

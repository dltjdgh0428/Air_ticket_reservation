package com.example.ticketmybatis.domain;

import com.example.ticketmybatis.entity.AirportEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

public class Journey {

    @Data
    public static class Simple{
        private Long journey_id;
        private Date d_time;
        private Date a_time;
        private AirportEntity d_airport_id;
        private AirportEntity a_airport_id;

        private String d_airport;
        private String a_airport;

    }

}
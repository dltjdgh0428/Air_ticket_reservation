package com.example.ticketmybatis.domain;

import lombok.Data;

import java.sql.Date;

public class Journey {

    @Data
    public static class Simple{
        private Long journey_id;
        private Date d_time;
        private Date a_time;
        private Long d_airport_id;
        private Long a_airport_id;
    }

}

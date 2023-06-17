package com.example.ticketmybatis.domain;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

public class Journey {
    @Data
    public static class Create{
        private Long journey_id;
        private Date d_time;
        private Date a_time;
        private String d_airport;
        private String a_airport;
    }

    @Data
    public static class Simple{
        private Long journey_id;
        private Date d_time;
        private Date a_time;
        private String d_airport;
        private String a_airport;
    }

}

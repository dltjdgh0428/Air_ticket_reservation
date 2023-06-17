package com.example.ticketmybatis.domain;

import lombok.Data;

import java.util.Date;

public class Journey {
    @Data
    public static class Create{
        private Long journey_id;
        private Date d_time;
        private Date a_time;
    }

    @Data
    public static class Simple{
        private Long journey_id;
        private Date d_time;
        private Date a_time;
    }

}

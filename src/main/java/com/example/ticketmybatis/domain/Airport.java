package com.example.ticketmybatis.domain;

import jakarta.persistence.Column;
import lombok.Data;

public class Airport {

    @Data
    public static class Simple {
        private Long airport_id;
        private String country;
        private String city;
        private String code;
    }
}

package com.example.ticketmybatis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "journey")
public class JourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journey_id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String code;
}

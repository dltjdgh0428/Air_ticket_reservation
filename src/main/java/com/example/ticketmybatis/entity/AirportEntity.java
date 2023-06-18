package com.example.ticketmybatis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "airport")
public class AirportEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airport_id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String code;
}

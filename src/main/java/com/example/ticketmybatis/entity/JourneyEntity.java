package com.example.ticketmybatis.entity;

import java.sql.Date;

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
    private Date d_time;

    @Column
    private Date a_time;

    @Column
    private Long price;

    @ManyToOne
    @JoinColumn(name = "d_airport_id", referencedColumnName = "airport_id")
    private AirportEntity d_airport_id;

    @ManyToOne
    @JoinColumn(name = "a_airport_id", referencedColumnName = "airport_id")
    private AirportEntity a_airport_id;
}

package com.example.ticketmybatis.entity;

import java.sql.Date;

import com.example.ticketmybatis.domain.Airport;
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

    @ManyToOne
    @JoinColumn(name = "airport", insertable=false, updatable=false)
    private AirportEntity d_airport_id;

    @ManyToOne
    @JoinColumn(name = "airport", insertable=false, updatable=false)
    private AirportEntity a_airport_id;

    @Column
    private String d_airport;

    @Column
    private String a_airport;
}

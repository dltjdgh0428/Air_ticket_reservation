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

    @ManyToOne
    @JoinColumn(name = "airport")
    private Long d_airport_id;

    @ManyToOne
    @JoinColumn(name = "airport")
    private Long a_airport_id;
}

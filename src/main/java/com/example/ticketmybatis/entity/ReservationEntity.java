package com.example.ticketmybatis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    @Column
    private String reservation_name;

    @Column
    private String passport;

    @Column
    private String seat;
    
    @ManyToOne
    @JoinColumn(name="journey_id", referencedColumnName = "journey_id")
    private JourneyEntity journey_id;

}

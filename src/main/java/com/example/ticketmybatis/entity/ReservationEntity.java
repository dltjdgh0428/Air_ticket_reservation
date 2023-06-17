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

    @ManyToOne
    @JoinColumn(name="journey")
    private Long journey_id;

    @ManyToOne
    @JoinColumn(name="seat")
    private Long seat_id;

}

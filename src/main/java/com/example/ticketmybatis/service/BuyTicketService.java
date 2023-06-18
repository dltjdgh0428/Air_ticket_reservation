package com.example.ticketmybatis.service;

import com.example.ticketmybatis.domain.Journey;
import com.example.ticketmybatis.domain.Reservation;
import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.repository.BuyTicketRepository;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class BuyTicketService {

    private final BuyTicketRepository buyTicketRepository;//JPA용

    public BuyTicketService(BuyTicketRepository buyTicketRepository){
        this.buyTicketRepository = buyTicketRepository;
    }

    /**
     * 내 티켓 조회
     */
    public List<Journey.Simple> findBuyTickets() {
        List<Journey.Simple> list = new ArrayList<>();
        for (JourneyEntity ticketEntity : buyTicketRepository.findAllJourney() ) {
            Journey.Simple ticket = new Journey.Simple();
            ticket.setJourney_id(ticketEntity.getJourney_id());
            ticket.setD_time(ticketEntity.getD_time());
            ticket.setA_time(ticketEntity.getA_time());
            ticket.setD_airport_id(ticketEntity.getD_airport_id());
            ticket.setA_airport_id(ticketEntity.getA_airport_id());
            list.add(ticket);
        }
        return list;
    }

    /**
     * 조건에 맞는 티켓 조회
     */
    public List<Journey.Simple> findCondBuyTickets(Journey.Simple ticketForm) {
        JourneyEntity ticketEntity = new JourneyEntity();
        ticketEntity.setJourney_id(ticketForm.getJourney_id());
        ticketEntity.setD_time((Date) ticketForm.getD_time());
        ticketEntity.setA_time((Date) ticketForm.getA_time());
        ticketEntity.setD_airport_id(ticketForm.getD_airport_id());
        ticketEntity.setA_airport_id(ticketForm.getD_airport_id());

        List<Journey.Simple> list = new ArrayList<>();
        for(JourneyEntity ticketEntity2 : buyTicketRepository.findCondJourney(ticketEntity)) {
//        for(TicketEntity ticketEntity2 : ticketRepository.findCond(ticketForm.getName(), ticketForm.getPublisher())) {
            Journey.Simple ticket2 = new Journey.Simple();
            ticket2.setJourney_id(ticketEntity2.getJourney_id());
            ticket2.setD_time(ticketEntity2.getD_time());
            ticket2.setA_time(ticketEntity2.getA_time());
            ticket2.setD_airport_id(ticketForm.getD_airport_id());
            ticket2.setA_airport_id(ticketForm.getA_airport_id());
            list.add(ticket2);
        }
        return list;
    }
    public List<String> findBuyTicketById(Long ticketId) {
        return buyTicketRepository.findBuyTicketById(ticketId);
    }
    // public Long addReservation(Reservation.Simple form) {
    //     ReservationEntity reservationEntity = new ReservationEntity();
    //     reservationEntity.setReservation_id(form.getReservation_id());
    //     reservationEntity.setJourney_id(form.getJourney_id());
    //     reservationEntity.setSeat(form.getSeat());
    //     reservationEntity.setPassport(form.getPassport());
    //     return 
    // }

    public JourneyEntity getTicketById(Long ticketId) {
        return buyTicketRepository.findByIdJourney(ticketId).orElseThrow(
                IllegalArgumentException::new
        );
    }
}
package com.example.ticketmybatis.service;

import com.example.ticketmybatis.domain.Airport;
import com.example.ticketmybatis.domain.Journey;
import com.example.ticketmybatis.domain.Reservation;
import com.example.ticketmybatis.entity.AirportEntity;
import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
public class TicketService {
    private static TicketRepository ticketRepository;//JPA용

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }




    /**
     * 가능한 티켓 조회 (전체 티켓)
     */
    public static List<Airport.Simple> findTickets() {
        List<Airport.Simple> list = new ArrayList<>();

        for (AirportEntity ticketEntity : ticketRepository.findAll()) {
            System.out.println("*** ticket service ***");
            Airport.Simple ticket = new Airport.Simple();
            ticket.setAirport_id(ticketEntity.getAirport_id());
            ticket.setCode(ticketEntity.getCode());
            ticket.setCity(ticketEntity.getCity());
            ticket.setCountry(ticketEntity.getCountry());
            list.add(ticket);
        }
        return list;
    }

    /**
     * 조건에 맞는 도서 목록 조회
     */
    public List<Journey.Simple> findCondTickets(Long d_city, Long a_city, String d_time, String a_time) {

        System.out.println("<<<findCondTickets>>>");
        List<Journey.Simple> list = new ArrayList<>();
        for(JourneyEntity ticketEntity2 : ticketRepository.findCond(d_city, a_city, d_time, a_time)) {
//        for(TicketEntity ticketEntity2 : ticketRepository.findCond(ticketForm.getName(), ticketForm.getPublisher())) {
            Journey.Simple ticket2 = new Journey.Simple();
            ticket2.setJourney_id(ticketEntity2.getJourney_id());
            ticket2.setD_airport_id(ticketEntity2.getD_airport_id());
            ticket2.setA_airport_id(ticketEntity2.getA_airport_id());
            ticket2.setD_time(ticketEntity2.getD_time());
            ticket2.setA_time(ticketEntity2.getA_time());
            ticket2.setPrice(ticketEntity2.getPrice());
            list.add(ticket2);
        }
        if(!list.isEmpty()) {
            System.out.println("*** getJourney_id : " + list.get(0).getJourney_id());
        } else {
            System.out.println("*** getJourney_id : Empty");
        }
        return list;
    }

}
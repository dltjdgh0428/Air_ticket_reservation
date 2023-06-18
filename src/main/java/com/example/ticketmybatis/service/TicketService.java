package com.example.ticketmybatis.service;

import com.example.ticketmybatis.domain.Airport;
import com.example.ticketmybatis.domain.Journey;
import com.example.ticketmybatis.domain.Reservation;
import com.example.ticketmybatis.entity.AirportEntity;
import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.repository.TicketRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
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
    public List<Reservation.Simple> findCondTickets(Reservation.Create ticketForm) {
        ReservationEntity ticketEntity = new ReservationEntity();
        ticketEntity.setReservation_id(ticketForm.getReservation_id());
        ticketEntity.setReservation_name(ticketForm.getReservation_name());
        ticketEntity.setPassport(ticketForm.getPassport());

        List<Reservation.Simple> list = new ArrayList<>();
        for(ReservationEntity ticketEntity2 : ticketRepository.findCond(ticketEntity)) {
//        for(TicketEntity ticketEntity2 : ticketRepository.findCond(ticketForm.getName(), ticketForm.getPublisher())) {
            Reservation.Simple ticket2 = new Reservation.Simple();
            ticket2.setReservation_id(ticketEntity2.getReservation_id());
            ticket2.setReservation_name(ticketEntity2.getReservation_name());
            ticket2.setPassport(ticketEntity2.getPassport());
            list.add(ticket2);
        }
        return list;
    }

    /**
     * 도서추가
     */
    public Long addTicket(Reservation.Create ticketForm) {
        ReservationEntity ticketEntity = new ReservationEntity();
        ticketEntity.setReservation_id(ticketForm.getReservation_id());
        ticketEntity.setReservation_name(ticketForm.getReservation_name());
        ticketEntity.setPassport(ticketForm.getPassport());
        ticketRepository.save(ticketEntity);
        return ticketEntity.getReservation_id();
    }

    public void updateTicketService(Long ticketId, Reservation.Update updateForm) {
        ReservationEntity ticketEntity = ticketRepository.findById(ticketId).orElseThrow(
                IllegalArgumentException::new
        );

        ticketEntity.setReservation_id(updateForm.getReservation_id());
        ticketEntity.setReservation_name(updateForm.getReservation_name());
        ticketEntity.setPassport(updateForm.getPassport());
//        ticketRepository.update(ticketEntity);
        ticketRepository.save(ticketEntity);
    }

    public ReservationEntity getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public void deleteTicket(Long ticketId) {
        ReservationEntity ticketEntity = ticketRepository.findById(ticketId).orElseThrow(
                IllegalArgumentException::new
        );

        ticketRepository.delete(ticketEntity);
    }
}


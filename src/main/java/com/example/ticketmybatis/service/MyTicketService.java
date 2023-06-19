package com.example.ticketmybatis.service;

import com.example.ticketmybatis.domain.Reservation;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.repository.MyTicketRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class MyTicketService {
    private final MyTicketRepository myTicketRepository;//JPA용
    public MyTicketService(MyTicketRepository myTicketRepository){
        this.myTicketRepository = myTicketRepository;
    }

    /**
     * 내 티켓 조회
     */
    public List<Reservation.Simple> findMyTickets() {
        List<Reservation.Simple> list = new ArrayList<>();
        for (ReservationEntity ticketEntity : myTicketRepository.findAllReservation() ) {
            Reservation.Simple ticket = new Reservation.Simple();
            ticket.setReservation_id(ticketEntity.getReservation_id());
            ticket.setReservation_name(ticketEntity.getReservation_name());
            ticket.setPassport(ticketEntity.getPassport());
            ticket.setSeat(ticketEntity.getSeat());
            ticket.setJourney_id(ticketEntity.getJourney_id());
            list.add(ticket);
        }
        return list;
    }

    

    /**
     * 조건에 맞는 도서 목록 조회
     */
    public List<Reservation.Simple> findCondMyTickets(Reservation.Create ticketForm) {
        ReservationEntity ticketEntity = new ReservationEntity();
        ticketEntity.setReservation_id(ticketForm.getReservation_id());
        ticketEntity.setReservation_name(ticketForm.getReservation_name());
        ticketEntity.setPassport(ticketForm.getPassport());
        ticketEntity.setSeat(ticketEntity.getSeat());

        List<Reservation.Simple> list = new ArrayList<>();
        for(ReservationEntity ticketEntity2 : myTicketRepository.findCondReservation(ticketEntity)) {
//        for(TicketEntity ticketEntity2 : ticketRepository.findCond(ticketForm.getName(), ticketForm.getPublisher())) {
            Reservation.Simple ticket2 = new Reservation.Simple();
            ticket2.setReservation_id(ticketEntity2.getReservation_id());
            ticket2.setReservation_name(ticketEntity2.getReservation_name());
            ticket2.setPassport(ticketEntity2.getPassport());
            ticket2.setSeat(ticketEntity2.getSeat());
            list.add(ticket2);
        }
        return list;
    }


    public ReservationEntity getTicketById(Long ticketId) {
        return myTicketRepository.findByIdReservation(ticketId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    public void deleteTicket(Long ticketId) {
        ReservationEntity ticketEntity = myTicketRepository.findByIdReservation(ticketId).orElseThrow(
                IllegalArgumentException::new
        );

        myTicketRepository.delete(ticketEntity);
    }

}
package com.example.ticketmybatis.service;

import com.example.ticketmybatis.domain.Reservation;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.repository.TicketRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class TicketService {

//    private final MybatisTicketRepository ticketRepository;
//    private final DataJpaTicketRepository ticketRepository; // DataJPA용
    private final TicketRepository ticketRepository;//JPA용

//    public TicketService(MybatisTicketRepository ticketRepository) {
//        this.ticketRepository = ticketRepository; 
//    }
//    public TicketService(DataJpaTicketRepository ticketRepository){
//        this.ticketRepository = ticketRepository;
//    }
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }




    /**
     * 전체 도서 목록 조회
     */
    public List<Reservation.Simple> findTickets() {
        List<Reservation.Simple> list = new ArrayList<>();
        for (ReservationEntity ticketEntity : ticketRepository.findAll()) {
            Reservation.Simple ticket = new Reservation.Simple();
            ticket.setReservation_id(ticketEntity.getReservation_id());
            ticket.setReservation_name(ticketEntity.getReservation_name());
            ticket.setPassport(ticketEntity.getPassport());
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


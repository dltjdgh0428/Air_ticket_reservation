package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.domain.Journey;
import com.example.ticketmybatis.entity.AirportEntity;
import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class JpaTicketRepository implements MyTicketRepository,BuyTicketRepository,TicketRepository {
    private final EntityManager em;

    public JpaTicketRepository(EntityManager em) { this.em = em;}


    @Override
    public void save(ReservationEntity ticketEntity) {

    }

    public List<AirportEntity> findAll() {

        List<AirportEntity> result = em.createQuery("select a from airport a", AirportEntity.class).getResultList();
        System.out.println("print result");
        return result;
    }

    @Override
    public List<ReservationEntity> findCond(ReservationEntity ticketEntity) {
        return null;
    }

    @Override
    public Optional<ReservationEntity> findById(Long ticketId) {
        return Optional.empty();
    }

    public List<ReservationEntity> findAllReservation() {
        List<ReservationEntity> result = em.createQuery("select r from reservation r", ReservationEntity.class)          //JPQL 이라는 쿼리 사용
                .getResultList();
        return result;
    }
    @Override
    public List<JourneyEntity> findAllJourney() {
        List<JourneyEntity> result = em.createQuery("select j from journey j", JourneyEntity.class).getResultList();          //JPQL 이라는 쿼리 사용

        return result;
    }
    @Override
    public Optional<ReservationEntity> findByIdReservation(Long id) {
        ReservationEntity ticket = em.find(ReservationEntity.class, id);
        return Optional.ofNullable(ticket);
    }
    @Override
    public Optional<JourneyEntity> findByIdJourney(Long id) {
        JourneyEntity ticket = em.find(JourneyEntity.class, id);
        return Optional.ofNullable(ticket);
    }
    /**
     * 여기는 안건드려도 될거같음.
     * */
    @Override
    public List<ReservationEntity> findCondReservation(ReservationEntity ticket) {
        String jpql = "select r from reservation r where r.passport = inputPassport";
        TypedQuery<ReservationEntity> query = em.createQuery(jpql, ReservationEntity.class);
        query.setParameter("inputPassport", ticket.getPassport());
        List<ReservationEntity> result = query.getResultList();
        return result;
    }
    /**
     * 여기 쿼리를 짜야함. 검색한 출발지 도착지 가는날짜, 오는날짜가 다 일치하는걸 알려줘야함
     * 그래서 DB부분을 다시짜야함 getJourney에서 출발지 도착지 가는날짜 오는날짜가
     * 다 있어야지 여기서 검색이 가능한데 그게 없어서 지금 못함.
     * */
    @Override
    public List<JourneyEntity> findCondJourney(JourneyEntity ticket) {
        String jpql = "select j from journey j where j.journey_id = inputId";
        TypedQuery<JourneyEntity> query = em.createQuery(jpql, JourneyEntity.class);
        query.setParameter("inputId", ticket.getJourney_id());
        List<JourneyEntity> result = query.getResultList();
        return result;
    }

    // 자석 정보 가져오기
    @Override
    public List<String> findBuyTicketById(Long journey_id) {
        String[] seat={
            "A1","A2","A3","A4","A5","A6","A7","A8","A9","A10",
            "B1","B2","B3","B4","B5","B6","B7","B8","B9","B10",
            "C1","C2","C3","C4","C5","C6","C7","C8","C9","C10",
            "D1","D2","D3","D4","D5","D6","D7","D8","D9","D10",
            "E1","E2","E3","E4","E5","E6","E7","E8","E9","E10",
            "F1","F2","F3","F4","F5","F6","F7","F8","F9","F10",
            "G1","G2","G3","G4","G5","G6","G7","G8","G9","G10",
            "H1","H2","H3","H4","H5","H6","H7","H8","H9","H10",
            "I1","I2","I3","I4","I5","I6","I7","I8","I9","I10"
        };
        String jpql = String.format("select seat from reservation r where r.journey_id = %d", journey_id);
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        List<String> result = query.getResultList();
        List<String> list = new ArrayList<>(Arrays.asList(seat));
        list.removeAll(result);
        return list;
    }

}
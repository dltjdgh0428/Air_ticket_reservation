package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.AirportEntity;
import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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

    @Override
    public void delete(ReservationEntity ticket) {
        System.out.println("*** Repository.delete 시작 ***");
        em.remove(ticket);
    }

}
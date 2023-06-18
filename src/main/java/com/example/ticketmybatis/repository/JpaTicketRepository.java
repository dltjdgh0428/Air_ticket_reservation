package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.AirportEntity;
import com.example.ticketmybatis.entity.JourneyEntity;
import com.example.ticketmybatis.entity.ReservationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


public class JpaTicketRepository implements MyTicketRepository,BuyTicketRepository,TicketRepository{
    private final EntityManager em;

    public JpaTicketRepository(EntityManager em) { this.em = em;}

    @Override
    public List<JourneyEntity> findCond(Long d_code, Long a_code, String d_time, String a_time) {
        System.out.println("findCond : " + d_code + a_code + d_time + a_time);

        String jpql = "select j from journey j where j.d_airport_id LIKE concat('%', :inputD_code, '%') and j.a_airport_id LIKE concat('%', :inputA_code, '%') and j.d_time LIKE concat('%', :inputD_time, '%') and j.a_time LIKE concat('%', :inputA_time, '%')";
        TypedQuery<JourneyEntity> query = em.createQuery(jpql, JourneyEntity.class);

        query.setParameter("inputD_code", d_code);
        query.setParameter("inputA_code", a_code);
        query.setParameter("inputD_time", d_time);
        query.setParameter("inputA_time", a_time);
        List<JourneyEntity> result = query.getResultList();

        if(!result.isEmpty()) {
            System.out.println("findCond - result : " + result.get(0).getA_airport_id());
        }
        return result;
    }
    @Override
    public List<AirportEntity> findAll() {
        List<AirportEntity> result = em.createQuery("select a from airport a", AirportEntity.class)          //JPQL 이라는 쿼리 사용
                .getResultList();
        return result;
    }

    @Override
    public Optional<AirportEntity> findById(Long id) {
        AirportEntity ticket = em.find(AirportEntity.class, id);
        return Optional.ofNullable(ticket);
    }


    @Override
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




    //

}
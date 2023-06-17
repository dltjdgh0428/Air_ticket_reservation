package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.ReservationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


public class JpaTicketRepository implements TicketRepository {
    private final EntityManager em;

    public JpaTicketRepository(EntityManager em) { this.em = em;}

    @Override
    public void save(ReservationEntity ticket) {
        em.persist(ticket);
    }

    @Override
    public List<ReservationEntity> findAll() {
        List<ReservationEntity> result = em.createQuery("select * from reservation", ReservationEntity.class)          //JPQL 이라는 쿼리 사용
                .getResultList();
        return result;
    }

    @Override
    public Optional<ReservationEntity> findById(Long id) {
        ReservationEntity ticket = em.find(ReservationEntity.class, id);
        return Optional.ofNullable(ticket);
    }

    @Override
    public List<ReservationEntity> findCond(ReservationEntity ticket) {
        String jpql = "select b from ticket b where b.name LIKE concat('%', :inputName, '%') and b.publisher LIKE concat('%', :inputPublisher, '%')";
        TypedQuery<ReservationEntity> query = em.createQuery(jpql, ReservationEntity.class);
        query.setParameter("inputId", ticket.getReservation_id());
        query.setParameter("inputName", ticket.getReservation_name());
        List<ReservationEntity> result = query.getResultList();
        return result;
    }

    @Override
    public void delete(ReservationEntity ticket) {
        System.out.println("*** Repository.delete 시작 ***");
        em.remove(ticket);
    }

}
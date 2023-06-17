package com.example.ticketmybatis.repository;

import com.example.ticketmybatis.entity.TicketEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


public class JpaTicketRepository implements TicketRepository {
    private final EntityManager em;

    public JpaTicketRepository(EntityManager em) { this.em = em;}

    @Override
    public void save(TicketEntity ticket) {
        em.persist(ticket);
    }

    @Override
    public List<TicketEntity> findAll() {
        List<TicketEntity> result = em.createQuery("select * from reservation", TicketEntity.class)          //JPQL 이라는 쿼리 사용
                .getResultList();
        return result;
    }

    @Override
    public Optional<TicketEntity> findById(Long id) {
        TicketEntity ticket = em.find(TicketEntity.class, id);
        return Optional.ofNullable(ticket);
    }

    @Override
    public List<TicketEntity> findCond(TicketEntity ticket) {
        String jpql = "select b from ticket b where b.name LIKE concat('%', :inputName, '%') and b.publisher LIKE concat('%', :inputPublisher, '%')";
        TypedQuery<TicketEntity> query = em.createQuery(jpql, TicketEntity.class);
        query.setParameter("inputId", ticket.getReservation_id());
        query.setParameter("inputName", ticket.getReservation_name());
        List<TicketEntity> result = query.getResultList();
        return result;
    }

    @Override
    public void delete(TicketEntity ticket) {
        System.out.println("*** Repository.delete 시작 ***");
        em.remove(ticket);
    }

}
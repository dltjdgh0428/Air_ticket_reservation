package com.example.ticketmybatis;


import com.example.ticketmybatis.repository.TicketRepository;
import com.example.ticketmybatis.repository.JpaTicketRepository;
import com.example.ticketmybatis.service.TicketService;
import jakarta.persistence.EntityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.ticketmybatis.repository")
@EnableJpaRepositories("com.example.ticketmybatis.repository")
public class SpringConfig {
//    private final MybatisTicketRepository ticketRepository;
//    private final DataJpaTicketRepository ticketRepository; // DataJPA용
    private EntityManager em; // JPA용


//    public SpringConfig(MybatisTicketRepository ticketRepository) {
//        this.ticketRepository = ticketRepository;
//    }
//    public SpringConfig(DataJpaTicketRepository ticketRepository){
//        this.ticketRepository = ticketRepository;
//    }
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public TicketService ticketService(){
//        return new TicketService(ticketRepository); // myBatis와 dataJPA용
        return new TicketService(ticketRepository());
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new JpaTicketRepository(em);
    }
}

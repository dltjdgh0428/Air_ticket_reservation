package com.example.ticketmybatis.controller;

import com.example.ticketmybatis.domain.Journey;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.service.BuyTicketService;
import com.example.ticketmybatis.service.MyTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;
@Controller
public class TicketController {

    private final MyTicketService myTicketService;
    private final BuyTicketService buyTicketService;

    @Autowired
    public TicketController(MyTicketService myTicketService,BuyTicketService buyTicketService) {
        this.myTicketService = myTicketService;
        this.buyTicketService = buyTicketService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

/*
* 항공권 검색 결과를 여기서 보여줌 (모든 항공권)
* */
    @GetMapping(value = "/tickets") //원래는 getMapping에 경로는 tickets
    public String list(Model model) {
        System.out.println("*** tickets mapping *** ");
        List<Journey.Simple> tickets = buyTicketService.findBuyTickets();
        model.addAttribute("tickets", tickets);
        return "tickets/ticketList";
    }

    @GetMapping(value = "/tickets/buy")        //멤버 등록화면 데이터 인수
    public String searchForm() {
        return "tickets/buyTicketForm";

    }

    @PostMapping(value = "/tickets/search")                                //ticketSearchform에서 받은 데이터 반영
    public String search(Journey.Create form, Model model) {
        List<Journey.Simple> tickets = buyTicketService.findCondBuyTickets(form);
        model.addAttribute("tickets", tickets);
        return "tickets/ticketList";
    }

    @GetMapping("/tickets/{ticketId}")
    public String getTicketById(@PathVariable Long ticketId, Model model) {

        List<ReservationEntity> ticketSimpleListSingle = new LinkedList<>();
        ticketSimpleListSingle.add(myTicketService.getTicketById(ticketId));

        model.addAttribute("tickets", ticketSimpleListSingle);
        return "tickets/ticketList";
    }

    @GetMapping("/tickets/{ticketId}/delete")
    public String getTicketDeleteForm(@PathVariable Long ticketId, Model model) {
        ReservationEntity ticketEntity = myTicketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticketEntity);
        return "tickets/deleteTicketForm";
    }

    @PostMapping("/tickets/{ticketId}/delete")
    public String getTicketDelete(@PathVariable Long ticketId, Model model) {
        model.addAttribute("id", ticketId);
        myTicketService.deleteTicket(ticketId);
        return "redirect:/tickets";
    }

}

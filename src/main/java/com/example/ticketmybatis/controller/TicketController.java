package com.example.ticketmybatis.controller;

import com.example.ticketmybatis.domain.Ticket;
import com.example.ticketmybatis.entity.TicketEntity;
import com.example.ticketmybatis.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;
@Controller
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @RequestMapping(value = "/") //원래는 getMapping에 경로는 tickets
    public String list(Model model) {
        System.out.println("*** tickets mapping *** ");
        List<Ticket.Simple> tickets = ticketService.findTickets();
        model.addAttribute("tickets", tickets);
        return "home";
    }

    @GetMapping(value = "/tickets/new") //도서 입력 화면 데이터 인수
    public String createForm() {
        return "tickets/inputTicketForm";
    }

    @PostMapping(value = "/tickets/new")          //ticketform에서 받은 데이터 반영
    public String create(Ticket.Create form) {
        ticketService.addTicket(form);
        return "redirect:/";
    }

    @GetMapping(value = "/tickets/search")        //멤버 등록화면 데이터 인수
    public String searchForm() {
        return "tickets/buyTicketForm";

    }


    @PostMapping(value = "/tickets/search")                                //ticketSearchform에서 받은 데이터 반영
    public String search(Ticket.Create form, Model model) {
        List<Ticket.Simple> tickets = ticketService.findCondTickets(form);
        model.addAttribute("tickets", tickets);
        return "tickets/ticketList";
    }

    @GetMapping("/tickets/{ticketId}/update")
    public String getMemberUpdateForm(@PathVariable Long ticketId, Model model) {
        TicketEntity ticketEntity = ticketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticketEntity);
        return "tickets/updateTicketForm";
    }

    @PostMapping("/tickets/{ticketId}")
    public String updateTicket(@PathVariable Long ticketId, Ticket.Update updateForm) {
        ticketService.updateTicketService(ticketId, updateForm);
        return "redirect:/tickets/" + ticketId;
    }

    @GetMapping("/tickets/{ticketId}")
    public String getTicketById(@PathVariable Long ticketId, Model model) {

        List<TicketEntity> ticketSimpleListSingle = new LinkedList<>();
        ticketSimpleListSingle.add(ticketService.getTicketById(ticketId));

        model.addAttribute("tickets", ticketSimpleListSingle);
        return "tickets/ticketList";
    }

    @GetMapping("/tickets/{ticketId}/delete")
    public String getTicketDeleteForm(@PathVariable Long ticketId, Model model) {
        TicketEntity ticketEntity = ticketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticketEntity);
        return "tickets/deleteTicketForm";
    }

    @PostMapping("/tickets/{ticketId}/delete")
    public String getTicketDelete(@PathVariable Long ticketId, Model model) {
        model.addAttribute("id", ticketId);
        ticketService.deleteTicket(ticketId);
        return "redirect:/tickets";
    }

}

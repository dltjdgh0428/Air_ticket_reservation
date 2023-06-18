package com.example.ticketmybatis.controller;
import com.example.ticketmybatis.domain.Airport;
import com.example.ticketmybatis.domain.Journey;
import com.example.ticketmybatis.domain.Reservation;
import com.example.ticketmybatis.entity.ReservationEntity;
import com.example.ticketmybatis.service.BuyTicketService;
import com.example.ticketmybatis.service.MyTicketService;
import com.example.ticketmybatis.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;
@Controller
public class TicketController {

    private final MyTicketService myTicketService;
    private final BuyTicketService buyTicketService;
    private final TicketService ticketService;

    @Autowired
    public TicketController(MyTicketService myTicketService,BuyTicketService buyTicketService, TicketService ticketService) {
        this.myTicketService = myTicketService;
        this.buyTicketService = buyTicketService;
        this.ticketService = ticketService;
    }

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }



    @GetMapping("/")
    public String home(Model model) {
        System.out.println("*** tickets mapping *** ");
        List<Airport.Simple> tickets = TicketService.findTickets();

        System.out.println("*** tickets mapping *** " + tickets.get(0).getAirport_id());
        model.addAttribute("tickets", tickets);
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
        return "home";
    }

    @GetMapping(value = "/tickets/buy/{journeyId}")
    public String searchForm(@PathVariable Long journeyId,Model model) {
        System.out.println("ticket buy form--------- ");
        List<String> seat = buyTicketService.findBuyTicketById(journeyId);
        model.addAttribute("seats",seat);
        model.addAttribute("journey_id", journeyId.toString());
        return "tickets/buyTicketForm";
    }

    @PostMapping(value = "/tickets/buy")                                //ticketSearchform에서 받은 데이터 반영
    public String search(Reservation.Simple form, Model model) {
        System.out.println("ticket buy --------- ");
        System.out.println(form.getPassport());
        System.out.println(form.getSeat());
        System.out.println(form.getJourney_id());
        //model.addAttribute("tickets", tickets);
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

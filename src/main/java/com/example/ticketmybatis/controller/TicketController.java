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
import org.springframework.web.bind.annotation.RequestParam;

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
        return "tickets/ticketList";
    }
    @GetMapping(value = "/tickets/my") //원래는 getMapping에 경로는 tickets
    public String mylist(Model model) {
        System.out.println("*** tickets mapping *** ");
        List<Reservation.Simple> myTickets = myTicketService.findMyTickets();
        model.addAttribute("myTickets", myTickets);
        return "tickets/myTicketForm";
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
        return "tickets/buyTicketForm";
    }
    
    @PostMapping(value = "/tickets")   //홈에서 선택한 데이터 반영
    public String search(@RequestParam("d_city") Long d_id, @RequestParam("a_city") Long a_id, @RequestParam("d_time") String d_time, @RequestParam("a_time") String a_time, Model model){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<Ddd>>>>>>>>>>>>>>>>>>>>>>>");
        // 포맷터
        //SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");

        System.out.println(d_id + a_id + d_time + a_time);
        List<Journey.Simple> tickets = ticketService.findCondTickets(d_id, a_id, d_time, a_time);
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

    @GetMapping("/tickets/{reservationId}/delete")
    public String getTicketDeleteForm(@PathVariable Long reservationId, Model model) {
        ReservationEntity reservationEntity = myTicketService.getTicketById(reservationId);
        model.addAttribute("myTicket", reservationEntity);
        System.out.println(reservationEntity);
        return "tickets/deleteTicketForm";
    }

    @PostMapping("/tickets/{reservationId}/delete")
    public String getTicketDelete(@PathVariable Long reservationId, Model model) {
        model.addAttribute("id", reservationId);
        myTicketService.deleteTicket(reservationId);
        return "redirect:/tickets";
    }



}

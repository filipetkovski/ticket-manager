package project.ticket_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.ticket_manager.dto.TicketDto;
import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.UserEntity;
import project.ticket_manager.security.SecurityUtil;
import project.ticket_manager.service.TicketService;
import project.ticket_manager.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TicketController {
    TicketService ticketService;
    UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, UserService userService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @GetMapping("/daily/tickets")
    public String getTickets(Model model) {
        UserEntity user = new UserEntity();
        LocalDateTime date = LocalDateTime.now();
        List<TicketDto> tickets = ticketService.getDailyTickets(date);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("tickets", tickets);
        return "daily_tickets";
    }

    @GetMapping("/ticket/create")
    public String createTicket(Model model) {
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "create_ticket";
    }

    @PostMapping("/ticket/create")
    public String updateTicket(@ModelAttribute("ticket") Ticket ticket, HttpSession session) {
        ticket.setPrice(ticket.getNumberOfPeople() * 120);
        ticketService.save(ticket);
        session.setAttribute("ticket", ticket);
        return "redirect:/ticket/create/perday";
    }

    @GetMapping("/ticket/{ticketId}/delete")
    public String deleteTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteWithId(ticketId);
        return "redirect:/daily/tickets";
    }
}

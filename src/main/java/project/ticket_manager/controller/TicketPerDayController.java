package project.ticket_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.ticket_manager.dto.TicketDto;
import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.TicketPerDay;
import project.ticket_manager.model.UserEntity;
import project.ticket_manager.security.SecurityUtil;
import project.ticket_manager.service.TicketPerDayService;
import project.ticket_manager.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TicketPerDayController {
    TicketPerDayService ticketPerDayService;
    UserService userService;

    public TicketPerDayController(TicketPerDayService ticketPerDayService, UserService userService) {
        this.ticketPerDayService = ticketPerDayService;
        this.userService = userService;
    }

    @GetMapping("/ticket/create/perday")
    public String updatePerDay(HttpSession session) {
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        ticketPerDayService.updateDatabase(ticket);
        return "redirect:/ticket/create";
    }

    @GetMapping("/perday/tickets")
    public String getRenderPerDayTickets(Model model) {
        UserEntity user = new UserEntity();
        List<TicketPerDay> tickets = ticketPerDayService.findAll();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByEmail(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("tickets", tickets);
        return "perday_tickets";
    }
}

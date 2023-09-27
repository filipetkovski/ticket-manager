package project.ticket_manager.service;

import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.TicketPerDay;
import project.ticket_manager.repository.TicketPerDayRepository;

import java.util.List;

public interface TicketPerDayService {
    void updateDatabase(Ticket ticket);

    List<TicketPerDay> findAll();
}

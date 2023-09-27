package project.ticket_manager.service.impl;

import org.springframework.stereotype.Service;
import project.ticket_manager.dto.TicketPerDayDto;
import project.ticket_manager.mapping.TicketMapping;
import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.TicketPerDay;
import project.ticket_manager.repository.TicketPerDayRepository;
import project.ticket_manager.service.TicketPerDayService;

import java.util.List;

@Service
public class TicketPerDayServiceImpl implements TicketPerDayService {
    TicketPerDayRepository ticketPerDayRepository;

    public TicketPerDayServiceImpl(TicketPerDayRepository ticketPerDayRepository) {
        this.ticketPerDayRepository = ticketPerDayRepository;
    }

    @Override
    public void updateDatabase(Ticket ticket) {
        TicketPerDay ticketPerDay = ticketPerDayRepository.findByCreatedOn(ticket.getCreatedOn());
        if(ticketPerDay != null) {
            ticketPerDay.setPrice(ticketPerDay.getPrice() + ticket.getPrice());
            ticketPerDay.setNumberOfPeople(ticketPerDay.getNumberOfPeople() + ticket.getNumberOfPeople());
            ticketPerDayRepository.save(ticketPerDay);
        } else {
            TicketPerDay ticketPerDayNew = new TicketPerDay();
            ticketPerDayNew.setPrice(ticket.getPrice());
            ticketPerDayNew.setNumberOfPeople(ticket.getNumberOfPeople());
            ticketPerDayRepository.save(ticketPerDayNew);
        }
    }

    @Override
    public List<TicketPerDay> findAll() {
        return ticketPerDayRepository.findAll();
    }
}

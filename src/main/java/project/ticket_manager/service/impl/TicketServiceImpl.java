package project.ticket_manager.service.impl;

import org.springframework.stereotype.Service;
import project.ticket_manager.dto.TicketDto;
import project.ticket_manager.mapping.TicketMapping;
import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.UserEntity;
import project.ticket_manager.repository.TicketRepository;
import project.ticket_manager.repository.UserRepository;
import project.ticket_manager.security.SecurityUtil;
import project.ticket_manager.service.TicketService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static project.ticket_manager.mapping.TicketMapping.mapToTicket;
import static project.ticket_manager.mapping.TicketMapping.mapToTicketDto;


@Service
public class TicketServiceImpl implements TicketService {
    TicketRepository ticketRepository;
    UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TicketDto> getDailyTickets(LocalDateTime date) {
        List<Ticket> tickets = ticketRepository.findByCreatedOn(date);
        return tickets.stream().map((ticket) -> mapToTicketDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public void save(Ticket ticket) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByEmail(username);
        ticket.setCreatedBy(user);
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteWithId(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<TicketDto> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map((ticket) -> mapToTicketDto(ticket)).collect(Collectors.toList());
    }
}

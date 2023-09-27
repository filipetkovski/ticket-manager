package project.ticket_manager.mapping;

import project.ticket_manager.dto.TicketDto;
import project.ticket_manager.model.Ticket;

public class TicketMapping {

    public static Ticket mapToTicket(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .createdOn(ticketDto.getCreatedOn())
                .Id(ticketDto.getId())
                .numberOfPeople(ticketDto.getNumberOfPeople())
                .price(ticketDto.getPrice())
                .updatedOn(ticketDto.getUpdatedOn())
                .createdBy(ticketDto.getCreatedBy())
                .build();
        return ticket;
    }

    public static TicketDto mapToTicketDto(Ticket ticket) {
        TicketDto ticketDto = TicketDto.builder()
                .createdOn(ticket.getCreatedOn())
                .Id(ticket.getId())
                .numberOfPeople(ticket.getNumberOfPeople())
                .price(ticket.getPrice())
                .updatedOn(ticket.getUpdatedOn())
                .createdBy(ticket.getCreatedBy())
                .build();
        return ticketDto;
    }
}

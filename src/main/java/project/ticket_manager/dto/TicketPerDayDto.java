package project.ticket_manager.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketPerDayDto {
    private Long Id;
    private int numberOfPeople;
    private int price;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}

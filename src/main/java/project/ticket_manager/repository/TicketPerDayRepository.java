package project.ticket_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.TicketPerDay;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketPerDayRepository extends JpaRepository<TicketPerDay, Long> {
    @Query("SELECT t FROM TicketPerDay t WHERE FUNCTION('DATE_TRUNC', 'DAY', CAST(t.createdOn AS date)) = FUNCTION('DATE_TRUNC', 'DAY', CAST(:date AS date))")
    TicketPerDay findByCreatedOn(@Param("date") LocalDateTime date);

}

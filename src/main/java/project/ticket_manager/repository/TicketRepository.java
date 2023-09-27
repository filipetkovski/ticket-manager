package project.ticket_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.ticket_manager.model.Ticket;
import project.ticket_manager.model.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE DATE(t.createdOn) = DATE(:date)")
    List<Ticket> findByCreatedOn(@Param("date") LocalDateTime date);
}

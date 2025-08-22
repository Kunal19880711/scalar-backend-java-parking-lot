package repository;

import models.Ticket;

import java.util.Optional;

public interface TicketRepository extends EntityRepository<Ticket> {
    Optional<Ticket> getTicketByNumber(String ticketNumber);
}

package repository.impl;

import models.Ticket;
import repository.TicketRepository;

import java.util.Map;
import java.util.Optional;

public class TicketRepositoryImpl extends EntityRepositoryImpl<Ticket> implements TicketRepository {

    private Map<String, Long> ticketNumberToIdMap;

    public TicketRepositoryImpl() {
        // Initialize the map to store ticket numbers and their corresponding IDs
        ticketNumberToIdMap = new java.util.HashMap<>();
    }

    @Override
    public Optional<Ticket> getTicketByNumber(String ticketNumber) {
        // Check if the ticket number exists in the map and retrieve the corresponding ID
        return Optional.ofNullable(ticketNumberToIdMap.get(ticketNumber))
                       .flatMap(this::findById);
    }
}

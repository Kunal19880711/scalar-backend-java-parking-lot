package services;

import exceptions.GateNotFound;
import exceptions.NoParkingSlotAvailable;
import models.Gate;
import models.Ticket;
import models.Vehicle;

public interface TicketService {
    Ticket createTicket(Vehicle vehicle, Gate gate) throws GateNotFound, NoParkingSlotAvailable;
}

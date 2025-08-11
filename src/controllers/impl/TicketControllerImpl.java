package controllers.impl;

import controllers.TicketController;
import utilities.ResponseUtility;
import exceptions.GateNotFound;
import exceptions.NoParkingSlotAvailable;
import models.*;
import services.TicketService;

public class TicketControllerImpl implements TicketController {
    private final TicketService ticketService;

    public TicketControllerImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Response<IssueTicketResponseDTO> createTicket(IssueTicketRequestDTO request) {
        Vehicle vehicle = createVehicleFromIssueTicketRequest(request);
        Gate gate = createGateFromIssueTicketRequest(request);
        try {
            Ticket ticket = ticketService.createTicket(vehicle, gate);
            return ResponseUtility.fromSuccess(createIssueTicketResponseDTO(ticket));
        } catch (GateNotFound | NoParkingSlotAvailable e) {
            return ResponseUtility.fromFailure(e.getMessage());
        }
    }

    private Vehicle createVehicleFromIssueTicketRequest(IssueTicketRequestDTO request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setOwnerName(request.getOwnerName());
        vehicle.setOwnerContactNumber(request.getOwnerContactNumber());
        vehicle.setVehicleType(Enum.valueOf(VehicleType.class, request.getVehicleType().toUpperCase()));
        return vehicle;
    }

    private Gate createGateFromIssueTicketRequest(IssueTicketRequestDTO request) {
        Gate gate = new Gate();
        gate.setId(request.getGateId());
        return gate;
    }

    private IssueTicketResponseDTO createIssueTicketResponseDTO(Ticket ticket) {
        IssueTicketResponseDTO response = new IssueTicketResponseDTO();
        response.setTicketNumber(ticket.getNumber());
        response.setEntryTime(ticket.getEntryTime());
        response.setSlotNumber(ticket.getParkingSlot().getNumber());
        return response;
    }
}

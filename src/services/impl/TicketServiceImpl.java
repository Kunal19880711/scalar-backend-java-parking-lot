package services.impl;

import exceptions.GateNotFound;
import exceptions.NoParkingSlotAvailable;
import models.*;
import repository.GateRepository;
import repository.ParkingSlotRepository;
import repository.TicketRepository;
import repository.VehicleRepository;
import services.TicketService;

import java.util.Date;

public class TicketServiceImpl implements TicketService {
    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final TicketRepository ticketRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    public TicketServiceImpl(GateRepository gateRepository,
                             VehicleRepository vehicleRepository,
                             TicketRepository ticketRepository,
                             ParkingSlotRepository parkingSlotRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }


    @Override
    public Ticket createTicket(Vehicle vehicle, Gate gate) throws GateNotFound, NoParkingSlotAvailable {
        Vehicle vehicleFromDB = getOrCreateVehicle(vehicle);
        Gate gateFromDB = getGateById(gate.getId());
        synchronized (TicketServiceImpl.class) {
            ParkingSlot parkingSlot = occupyAvailableParkingSlot(vehicleFromDB);
            Ticket ticket = generateTicket(vehicleFromDB, gateFromDB, parkingSlot);
            return saveTicket(ticket);
        }
    }

    private Vehicle getOrCreateVehicle(Vehicle vehicle) {
        return vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber())
                .orElseGet(() -> vehicleRepository.save(vehicle));
    }

    private Gate getGateById(Long gateId) throws GateNotFound {
        return gateRepository.findById(gateId)
                .orElseThrow(() -> new GateNotFound("Gate not found with id: " + gateId));
    }

    private ParkingSlot occupyAvailableParkingSlot(Vehicle vehicle) throws NoParkingSlotAvailable {
        ParkingSlot parkingSlot = parkingSlotRepository.getAvailableParkingSlot(vehicle.getVehicleType())
                .orElseThrow(() -> new NoParkingSlotAvailable(
                        "No parking slot available for vehicle type: " + vehicle.getVehicleType()
                ));
        parkingSlot.setParkingSlotStatus(ParkingSlotStatus.OCCUPIED);
        return parkingSlotRepository.save(parkingSlot);
    }

    private Ticket generateTicket(Vehicle vehicle, Gate gate, ParkingSlot parkingSlot) {
        Ticket ticket = new Ticket();
        ticket.setNumber("TICKET-" + System.currentTimeMillis());
        ticket.setEntryTime(new Date());
        ticket.setEntryGate(gate);
        ticket.setOperator(gate.getOperator());
        ticket.setParkingSlot(parkingSlot);
        ticket.setVehicle(vehicle);
        return ticket;
    }

    private Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}

import controllers.TicketController;
import controllers.impl.TicketControllerImpl;
import models.*;
import repository.GateRepository;
import repository.ParkingSlotRepository;
import repository.TicketRepository;
import repository.VehicleRepository;
import repository.impl.GateRepositoryImpl;
import repository.impl.ParkingSlotRepositoryImpl;
import repository.impl.TicketRepositoryImpl;
import repository.impl.VehicleRepositoryImpl;
import services.TicketService;
import services.impl.TicketServiceImpl;

import java.util.List;

public class Client {
    public static void main(String[] args) {

        // Initialize repositories
        GateRepository gateRepository = new GateRepositoryImpl();
        ParkingSlotRepository parkingSlotRepository = new ParkingSlotRepositoryImpl();
        TicketRepository ticketRepository = new TicketRepositoryImpl();
        VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

        // Initialize services
        TicketService ticketService = new TicketServiceImpl(gateRepository, vehicleRepository, ticketRepository,
                parkingSlotRepository);

        // Initialize controllers
        TicketController ticketController = new TicketControllerImpl(ticketService);

//        // Create a vehicle
        Vehicle vehicle = generateVehicle("KA-01-HH-1234", "John Doe",
                "9876543210", VehicleType.CAR);

        // Create an operator
        Operator operator = generateOperator("Alice Smith", "EMP001");

        // Create a gate
        Gate gate = gateRepository.save(generateGate(GateType.ENTRY, 1, GateStatus.OPEN, operator));

        // Create allowed vehicles
        List<AllowedVehicles> allowedVehicles = generateAllowedVehicles();

        // Create some parking slots
        ParkingSlot parkingSlot1 = parkingSlotRepository.save(
                generateParkingSlot("PS-001", ParkingSlotStatus.AVAILABLE, allowedVehicles)
        );
        ParkingSlot parkingSlot2 = parkingSlotRepository.save(
                generateParkingSlot("PS-002", ParkingSlotStatus.AVAILABLE, allowedVehicles)
        );

        // Create a IssueTicketRequest
        IssueTicketRequestDTO issueTicketRequest = generateIssueTicketRequest(vehicle, gate);

        // Issue a ticket
        Response<IssueTicketResponseDTO> response = ticketController.createTicket(issueTicketRequest);

        // check DB state
        ParkingSlot parkingSlotInDb = parkingSlotRepository.findById(parkingSlot1.getId()).orElse(null);
        Vehicle vehicleInDb = vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber()).orElse(null);

        // Print the issued ticket details
        System.out.println("Issue Ticket Request: " + issueTicketRequest);
        System.out.println("Issued Ticket: " + response);

        // Print DB state
        System.out.println("Parking Slot in DB: " + parkingSlotInDb);
        System.out.println("Vehicle in DB: " + vehicleInDb);

    }

    private static Vehicle generateVehicle(String registrationNumber, String ownerName, String ownerContactNumber,
                                           VehicleType vehicleType) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(registrationNumber);
        vehicle.setOwnerName(ownerName);
        vehicle.setOwnerContactNumber(ownerContactNumber);
        vehicle.setVehicleType(vehicleType);
        return vehicle;
    }

    private static Operator generateOperator(String name, String employeeId) {
        Operator operator = new Operator();
        operator.setName(name);
        operator.setEmployeeId(employeeId);
        return operator;
    }

    private static Gate generateGate(GateType gateType, int gateNumber, GateStatus gateStatus, Operator operator) {
        Gate gate = new Gate();
        gate.setGateType(gateType);
        gate.setGateNumber(gateNumber);
        gate.setGateStatus(gateStatus);
        gate.setOperator(operator);
        return gate;
    }

    private static List<AllowedVehicles> generateAllowedVehicles() {
        AllowedVehicles car = new AllowedVehicles();
        car.setVehicleType(VehicleType.CAR);
        car.setCapacity(1);

        AllowedVehicles bike = new AllowedVehicles();
        bike.setVehicleType(VehicleType.BIKE);
        bike.setCapacity(1);

        return List.of(car, bike);
    }

    private static ParkingSlot generateParkingSlot(String number, ParkingSlotStatus parkingSlotStatus,
                                                   List<AllowedVehicles> allowedVehicles) {
        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setNumber(number);
        parkingSlot.setParkingSlotStatus(parkingSlotStatus);
        parkingSlot.setAllowedVehicles(allowedVehicles);
        return parkingSlot;
    }

    private static IssueTicketRequestDTO generateIssueTicketRequest(Vehicle vehicle, Gate gate) {
        IssueTicketRequestDTO request = new IssueTicketRequestDTO();
        request.setRegistrationNumber(vehicle.getRegistrationNumber());
        request.setOwnerName(vehicle.getOwnerName());
        request.setOwnerContactNumber(vehicle.getOwnerContactNumber());
        request.setVehicleType(vehicle.getVehicleType().name());
        request.setGateId(gate.getId());
        return request;
    }
}
package repository;

import models.ParkingSlot;
import models.VehicleType;

import java.util.Optional;

public interface ParkingSlotRepository extends EntityRepository<ParkingSlot> {
    Optional<ParkingSlot> getAvailableParkingSlot(VehicleType vehicleType);
}

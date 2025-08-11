package repository.impl;

import models.AllowedVehicles;
import models.ParkingSlot;
import models.ParkingSlotStatus;
import models.VehicleType;
import repository.ParkingSlotRepository;

import java.util.*;

public class ParkingSlotRepositoryImpl extends EntityRepositoryImpl<ParkingSlot>
        implements ParkingSlotRepository {

    private final Map<VehicleType, Set<Long>> vehicleTypeToSlotIds;

    public ParkingSlotRepositoryImpl() {
        this.vehicleTypeToSlotIds = new HashMap<>();
    }

    @Override
    public Optional<ParkingSlot> getAvailableParkingSlot(VehicleType vehicleType) {
        return Optional.ofNullable(vehicleTypeToSlotIds.get(vehicleType))
                .flatMap(idSet -> idSet.stream().findFirst())
                .flatMap(this::findById);
    }

    @Override
    public ParkingSlot save(ParkingSlot entity) {
        ParkingSlot parkingSlot = super.save(entity);
        for(AllowedVehicles allowedVehicles : parkingSlot.getAllowedVehicles()) {
            VehicleType vehicleType = allowedVehicles.getVehicleType();
            Set<Long> slotIds = vehicleTypeToSlotIds
                    .computeIfAbsent(vehicleType, k -> new HashSet<>());
            if(parkingSlot.getParkingSlotStatus().equals(ParkingSlotStatus.AVAILABLE)) {
                slotIds.add(parkingSlot.getId());
            } else {
                slotIds.remove(parkingSlot.getId());
            }
        }
        return parkingSlot;
    }
}

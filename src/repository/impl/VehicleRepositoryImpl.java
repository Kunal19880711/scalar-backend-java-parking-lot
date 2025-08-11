package repository.impl;

import models.Vehicle;
import repository.VehicleRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepositoryImpl extends EntityRepositoryImpl<Vehicle> implements VehicleRepository {
    private final Map<String, Long> registrationNumberToIdMap;

    public VehicleRepositoryImpl() {
        registrationNumberToIdMap = new HashMap<>();
    }

    @Override
    public Vehicle save(Vehicle entity) {
        Vehicle vehicle = super.save(entity);
        if (vehicle != null && vehicle.getRegistrationNumber() != null) {
            registrationNumberToIdMap.put(vehicle.getRegistrationNumber(), vehicle.getId());
        }
        return vehicle;
    }

    @Override
    public Optional<Vehicle> findByRegistrationNumber(String registrationNumber) {
        return Optional.ofNullable(registrationNumberToIdMap.get(registrationNumber)).flatMap(this::findById);
    }
}

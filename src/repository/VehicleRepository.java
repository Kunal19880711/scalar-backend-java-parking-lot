package repository;

import models.Operator;
import models.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends EntityRepository<Vehicle> {
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}

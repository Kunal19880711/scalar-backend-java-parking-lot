package models;

import java.util.List;

public class ParkingSlot extends BaseClass{
    private String number;
    private ParkingSlotStatus status;
    private List<AllowedVehicles> allowedVehicles;
    private ParkingFloor parkingFloor;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ParkingSlotStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingSlotStatus status) {
        this.status = status;
    }

    public List<AllowedVehicles> getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(List<AllowedVehicles> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
}

package models;

import java.util.List;

public class ParkingSlot extends BaseModel {
    private String number;
    private ParkingSlotStatus parkingSlotStatus;
    private List<AllowedVehicles> allowedVehicles;
    private ParkingFloor parkingFloor;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ParkingSlotStatus getParkingSlotStatus() {
        return parkingSlotStatus;
    }

    public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
        this.parkingSlotStatus = parkingSlotStatus;
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

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "baseModel=" + super.toString() +
                ", number='" + number + '\'' +
                ", parkingSlotStatus=" + parkingSlotStatus +
                ", allowedVehicles=" + allowedVehicles +
                ", parkingFloor=" + parkingFloor +
                '}';
    }
}

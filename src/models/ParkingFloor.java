package models;

import java.util.List;

public class ParkingFloor extends BaseModel {
    private Integer floorName;
    private ParkingFloorStatus parkingFloorStatus;
    private List<ParkingSlot> parkingSlots;
    private List<AllowedVehicles> allowedVehicles;

    public List<AllowedVehicles> getAllowedVehicles() {
        return allowedVehicles;
    }

    public void setAllowedVehicles(List<AllowedVehicles> allowedVehicles) {
        this.allowedVehicles = allowedVehicles;
    }

    public List<ParkingSlot> getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(List<ParkingSlot> parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public ParkingFloorStatus getParkingFloorStatus() {
        return parkingFloorStatus;
    }

    public void setParkingFloorStatus(ParkingFloorStatus parkingFloorStatus) {
        this.parkingFloorStatus = parkingFloorStatus;
    }

    public Integer getFloorName() {
        return floorName;
    }

    public void setFloorName(Integer floorName) {
        this.floorName = floorName;
    }
}



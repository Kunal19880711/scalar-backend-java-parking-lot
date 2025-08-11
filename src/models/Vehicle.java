package models;

public class Vehicle extends BaseClass{
    private String registrationNumber;
    private String ownerName;
    private String ownerContactNumber;
    private VehicleType vehicleType;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContactNumber() {
        return ownerContactNumber;
    }

    public void setOwnerContactNumber(String ownerContactNumber) {
        this.ownerContactNumber = ownerContactNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}

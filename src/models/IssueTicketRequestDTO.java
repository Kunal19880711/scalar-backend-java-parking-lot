package models;

public class IssueTicketRequestDTO {
    private String registrationNumber;
    private String ownerName;
    private String ownerContactNumber;
    private String vehicleType;
    private long gateId;

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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public long getGateId() {
        return gateId;
    }

    public void setGateId(long gateId) {
        this.gateId = gateId;
    }

    @Override
    public String toString() {
        return "IssueTicketRequestDTO{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerContactNumber='" + ownerContactNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", gateId='" + gateId + '\'' +
                '}';
    }
}

package models;

import java.util.Date;

public class IssueTicketResponseDTO {
    private String ticketNumber;
    private Date entryTime;
    private String slotNumber;

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return "IssueTicketResponseDTO{" +
                "ticketNumber='" + ticketNumber + '\'' +
                ", entryTime=" + entryTime +
                ", slotNumber='" + slotNumber + '\'' +
                '}';
    }
}

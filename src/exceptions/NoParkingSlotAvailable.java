package exceptions;

public class NoParkingSlotAvailable extends Exception {
    public NoParkingSlotAvailable(String message) {
        super(message);
    }
}

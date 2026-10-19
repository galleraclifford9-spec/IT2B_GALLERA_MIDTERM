package IT2B_GALLERA_MIDTERM.model;

public class Reservation {
    private int id;
    private String guestName;
    private String roomType;
    private double nightlyRate;
    private String payment;

    public Reservation(String guestName, String roomType, double nightlyRate, String payment) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nightlyRate = nightlyRate;
        this.payment = payment;
    }

    public Reservation(int id, String guestName, String roomType, double nightlyRate, String payment) {
        this(guestName, roomType, nightlyRate, payment);
        this.id = id;
    }

    public int getId() { return id; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
    public double getNightlyRate() { return nightlyRate; }
    public String getPayment() { return payment; }
}
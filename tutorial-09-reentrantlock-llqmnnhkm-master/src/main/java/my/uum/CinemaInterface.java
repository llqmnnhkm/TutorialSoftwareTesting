package my.uum;

// CinemaInterface.java
interface CinemaInterface {
    boolean bookSeat(int seatNumber, String counterName);
    boolean isSeatBooked(int seatNumber);
    int getTotalSeats();
}

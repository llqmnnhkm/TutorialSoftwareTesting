package my.;

// CinemaHall.java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class CinemaHall implements CinemaInterface {
    private static final int TOTAL_SEATS = 50;
    private boolean[] bookedSeats;
    private final Lock lock;

    public CinemaHall() {
        this.bookedSeats = new boolean[TOTAL_SEATS + 1]; // Index 0 unused, seats 1-50
        this.lock = new ReentrantLock(true); // Fair locking for fairness
    }

    @Override
    public boolean bookSeat(int seatNumber, String counterName) {
        // Validate seat number
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            System.out.println("Invalid seat number: " + seatNumber + ". " + counterName + " could not book it.");
            return false;
        }

        // Acquire lock to ensure thread safety
        lock.lock();
        try {
            // Check if seat is already booked
            if (bookedSeats[seatNumber]) {
                System.out.println("Seat " + seatNumber + " is already booked. " + counterName + " could not book it.");
                return false;
            }

            // Simulate some processing time
            try {
                Thread.sleep(100); // Small delay to demonstrate concurrency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            // Book the seat
            bookedSeats[seatNumber] = true;
            System.out.println("Seat " + seatNumber + " successfully booked by " + counterName);
            return true;

        } finally {
            // Always release the lock
            lock.unlock();
        }
    }

    @Override
    public boolean isSeatBooked(int seatNumber) {
        if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
            return false;
        }

        lock.lock();
        try {
            return bookedSeats[seatNumber];
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getTotalSeats() {
        return TOTAL_SEATS;
    }

    public int getBookedSeatsCount() {
        lock.lock();
        try {
            int count = 0;
            for (int i = 1; i <= TOTAL_SEATS; i++) {
                if (bookedSeats[i]) {
                    count++;
                }
            }
            return count;
        } finally {
            lock.unlock();
        }
    }
}

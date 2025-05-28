package my.uum;

// MyMain.java
public class MyMain {
    public static void main(String[] args) {
        // Create cinema hall instance
        CinemaHall cinemaHall = new CinemaHall();

        System.out.println("=== Cinema Ticket Booking System ===");
        System.out.println("Total seats available: " + cinemaHall.getTotalSeats());
        System.out.println();

        // Test Case 1: Multiple counters trying to book the same seat
        System.out.println("Test Case 1: Multiple counters trying to book seat 25");
        testSameSeatBooking(cinemaHall, 25);

        System.out.println();

        // Test Case 2: Multiple counters booking different seats
        System.out.println("Test Case 2: Multiple counters booking different seats");
        testDifferentSeatsBooking(cinemaHall);

        System.out.println();

        // Test Case 3: Invalid seat numbers
        System.out.println("Test Case 3: Testing invalid seat numbers");
        testInvalidSeats(cinemaHall);

        System.out.println();
        System.out.println("Total booked seats: " + cinemaHall.getBookedSeatsCount());
    }

    private static void testSameSeatBooking(CinemaHall cinemaHall, int seatNumber) {
        Thread[] threads = new Thread[3];

        // Create threads for different counters trying to book the same seat
        for (int i = 0; i < 3; i++) {
            String counterName = "Counter " + (i + 1);
            threads[i] = new Thread(new TicketCounter(cinemaHall, counterName, seatNumber));
        }

        // Start all threads simultaneously
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void testDifferentSeatsBooking(CinemaHall cinemaHall) {
        Thread[] threads = new Thread[5];
        int[] seats = {10, 15, 20, 30, 35};

        // Create threads for different counters booking different seats
        for (int i = 0; i < 5; i++) {
            String counterName = "Counter " + (i + 1);
            threads[i] = new Thread(new TicketCounter(cinemaHall, counterName, seats[i]));
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void testInvalidSeats(CinemaHall cinemaHall) {
        Thread[] threads = new Thread[3];
        int[] invalidSeats = {0, 51, -5};

        // Create threads trying to book invalid seats
        for (int i = 0; i < 3; i++) {
            String counterName = "Counter " + (i + 1);
            threads[i] = new Thread(new TicketCounter(cinemaHall, counterName, invalidSeats[i]));
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


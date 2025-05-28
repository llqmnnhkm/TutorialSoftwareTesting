package my.uum;

// TicketCounter.java
class TicketCounter implements Runnable {
    private final CinemaInterface cinema;
    private final String counterName;
    private final int seatToBook;

    public TicketCounter(CinemaInterface cinema, String counterName, int seatToBook) {
        this.cinema = cinema;
        this.counterName = counterName;
        this.seatToBook = seatToBook;
    }

    @Override
    public void run() {
        // Attempt to book the specified seat
        cinema.bookSeat(seatToBook, counterName);
    }
}


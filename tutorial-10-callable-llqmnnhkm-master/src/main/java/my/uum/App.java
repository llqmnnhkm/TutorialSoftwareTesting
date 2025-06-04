package my.uu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Hotel Price Comparison Platform
 * Simulates asynchronous price fetching from multiple hotels using Callable and Future
 */
public class App {

    /**
     * PriceFetcher class implements Callable to fetch prices asynchronously
     */
    static class PriceFetcher implements Callable<String> {
        private final String hotelName;
        private final String roomType;

        public PriceFetcher(String hotelName, String roomType) {
            this.hotelName = hotelName;
            this.roomType = roomType;
        }

        @Override
        public String call() throws Exception {
            // Simulate network delay (1-3 seconds)
            Thread.sleep((long) (Math.random() * 2000) + 1000);

            // Fetch price using the provided mock method
            double price = fetchPrice(roomType);

            // Format and return the result
            return String.format("Room Type: %s, Hotel: %s, Price: $%.2f",
                    roomType, hotelName, price);
        }

        /**
         * Mock price fetching logic as provided in the requirements
         */
        public double fetchPrice(String roomType) {
            // Mock price fetching logic
            return Math.random() * 100; // Random price for demonstration
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Hotel Price Comparison Platform ===");
        System.out.println("Fetching prices from multiple hotels asynchronously...\n");

        // Create ExecutorService with fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // List of hotels to fetch prices from
        String[] hotels = {"Hotel A", "Hotel B", "Hotel C", "Hotel D", "Hotel E"};
        String roomType = "Standard Room";

        // List to store Future objects
        List<Future<String>> futures = new ArrayList<>();

        try {
            long startTime = System.currentTimeMillis();

            // Submit tasks for each hotel
            for (String hotel : hotels) {
                PriceFetcher priceFetcher = new PriceFetcher(hotel, roomType);
                Future<String> future = executorService.submit(priceFetcher);
                futures.add(future);
            }

            System.out.println("All price fetching tasks submitted. Waiting for results...\n");

            // Collect and display results
            for (int i = 0; i < futures.size(); i++) {
                try {
                    String result = futures.get(i).get(); // This blocks until result is available
                    System.out.println(result);
                } catch (Exception e) {
                    System.err.println("Error fetching price from " + hotels[i] + ": " + e.getMessage());
                }
            }

            long endTime = System.currentTimeMillis();
            System.out.println("\n=== Summary ===");
            System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
            System.out.println("All price fetching operations completed successfully!");

            // Demonstrate concurrent execution by fetching prices for different room types
            System.out.println("\n=== Fetching prices for different room types ===");
            fetchPricesForDifferentRoomTypes(executorService);

        } finally {
            // Shutdown the executor service
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Additional method to demonstrate fetching prices for different room types
     */
    private static void fetchPricesForDifferentRoomTypes(ExecutorService executorService) {
        String[] roomTypes = {"Deluxe Room", "Suite", "Economy Room"};
        String[] hotels = {"Hotel A", "Hotel B", "Hotel C"};

        List<Future<String>> futures = new ArrayList<>();

        // Submit tasks for different combinations
        for (String roomType : roomTypes) {
            for (String hotel : hotels) {
                PriceFetcher priceFetcher = new PriceFetcher(hotel, roomType);
                Future<String> future = executorService.submit(priceFetcher);
                futures.add(future);
            }
        }

        // Collect results
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

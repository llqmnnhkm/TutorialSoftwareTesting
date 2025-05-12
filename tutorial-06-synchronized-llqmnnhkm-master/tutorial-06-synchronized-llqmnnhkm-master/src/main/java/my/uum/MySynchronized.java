package my.uum;

import java.util.Scanner;

public class MySynchronized {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input x: ");
        int x = scanner.nextInt();
        scanner.close();

        System.out.println();

        // Non-synchronized test
        Counter nonSyncCounter = new Counter();

        Thread incrementThread1 = new Thread(new MyIncrement(nonSyncCounter, x, false));
        Thread decrementThread1 = new Thread(new MyDecrement(nonSyncCounter, x, false));

        long startTimeNonSync = System.nanoTime();

        // Start both threads concurrently
        incrementThread1.start();
        decrementThread1.start();

        // Wait for both threads to finish
        try {
            incrementThread1.join();
            decrementThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTimeNonSync = System.nanoTime();
        long durationNonSync = (endTimeNonSync - startTimeNonSync) / 1000000; // Convert to milliseconds

        System.out.println("Result (non-synchronized method): " + nonSyncCounter.getCount());
        System.out.println("Execution Time: " + durationNonSync + " milliseconds");

        System.out.println();

        // Synchronized test
        Counter syncCounter = new Counter();

        Thread incrementThread2 = new Thread(new MyIncrement(syncCounter, x, true));
        Thread decrementThread2 = new Thread(new MyDecrement(syncCounter, x, true));

        long startTimeSync = System.nanoTime();

        // Start both threads concurrently
        incrementThread2.start();
        decrementThread2.start();

        // Wait for both threads to finish
        try {
            incrementThread2.join();
            decrementThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTimeSync = System.nanoTime();
        long durationSync = (endTimeSync - startTimeSync) / 1000000; // Convert to milliseconds

        System.out.println("Result (synchronized method): " + syncCounter.getCount());
        System.out.println("Execution Time: " + durationSync + " milliseconds");
    }
}
package my.uum;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MyPrimeNumber {

    // AtomicInteger to safely track sum across multiple threads
    private static AtomicInteger totalSum = new AtomicInteger(0);

    // Method to check if a number is prime
    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Class with method that will be referenced by threads
    static class PrimeNumberFinder {
        private int start;
        private int end;

        public PrimeNumberFinder(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // Method to find prime numbers in a range
        public void find() {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    totalSum.addAndGet(i); // Add prime number to total sum
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input x: ");
        int x = scanner.nextInt();
        scanner.close();

        // Create instances of PrimeNumberFinder
        PrimeNumberFinder finder1 = new PrimeNumberFinder(x, x + 5);
        PrimeNumberFinder finder2 = new PrimeNumberFinder(x + 5, x + 10);

        // Create two threads using method reference
        Thread thread1 = new Thread(finder1::find);
        Thread thread2 = new Thread(finder2::find);

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the total sum of prime numbers
        System.out.println("Total: " + totalSum.get());
    }
}
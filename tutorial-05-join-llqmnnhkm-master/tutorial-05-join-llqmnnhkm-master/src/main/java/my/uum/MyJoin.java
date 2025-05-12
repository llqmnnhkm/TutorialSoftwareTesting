package my.uum;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyJoin {
    private static int total = 0;
    private static final List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input x: ");
        int x = scanner.nextInt();
        scanner.close();

        // Create countdown latches to control execution order
        CountDownLatch latch1 = new CountDownLatch(1); // Thread-1 signals completion
        CountDownLatch latch2 = new CountDownLatch(1); // Thread-2 signals completion

        // Create three threads with proper synchronization mechanisms
        Thread thread0 = new Thread(new PrimeNumberWorker(x, x + 5, null, latch1, latch2));
        Thread thread1 = new Thread(new PrimeNumberWorker(x + 5, x + 10, latch1, null, null));
        Thread thread2 = new Thread(new PrimeNumberWorker(x + 10, x + 15, latch2, latch1, null));

        // Set names for the threads
        thread0.setName("Thread-0");
        thread1.setName("Thread-1");
        thread2.setName("Thread-2");

        // Start all threads
        thread0.start();
        thread1.start();
        thread2.start();

        try {
            // Wait for all threads to finish
            thread0.join();
            thread1.join();
            thread2.join();

            // Display the total of prime numbers
            System.out.println("Total: " + total);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class PrimeNumberWorker implements Runnable {
        private final int start;
        private final int end;
        private final CountDownLatch completionSignal;  // This thread signals when done
        private final CountDownLatch waitForSignal;     // This thread waits for this signal
        private final CountDownLatch secondWaitSignal;  // Additional wait signal (for Thread-0)

        public PrimeNumberWorker(int start, int end, CountDownLatch completionSignal,
                                 CountDownLatch waitForSignal, CountDownLatch secondWaitSignal) {
            this.start = start;
            this.end = end;
            this.completionSignal = completionSignal;
            this.waitForSignal = waitForSignal;
            this.secondWaitSignal = secondWaitSignal;
        }

        @Override
        public void run() {
            try {
                // Thread-0 waits for both Thread-1 and Thread-2 to complete
                if (Thread.currentThread().getName().equals("Thread-0")) {
                    if (waitForSignal != null) {
                        waitForSignal.await(); // Wait for Thread-1 to complete
                    }
                    if (secondWaitSignal != null) {
                        secondWaitSignal.await(); // Wait for Thread-2 to complete
                    }
                }

                // Thread-2 waits for Thread-1 to complete
                if (Thread.currentThread().getName().equals("Thread-2")) {
                    if (waitForSignal != null) {
                        waitForSignal.await(); // Wait for Thread-1 to complete
                    }
                }

                // Find and display prime numbers
                for (int i = start; i < end; i++) {
                    if (isPrime(i)) {
                        synchronized (primes) {
                            System.out.println(Thread.currentThread().getName() + ": " + i);
                            primes.add(i);
                            total += i;
                        }
                    }
                }

                // Signal completion
                if (completionSignal != null) {
                    completionSignal.countDown();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private boolean isPrime(int n) {
            if (n <= 1) {
                return false;
            }

            if (n <= 3) {
                return true;
            }

            if (n % 2 == 0 || n % 3 == 0) {
                return false;
            }

            for (int i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
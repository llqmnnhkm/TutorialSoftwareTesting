package my.uum;

public class MyDecrement implements Runnable {
    private Counter counter;
    private int times;
    private boolean synchronized_method;

    public MyDecrement(Counter counter, int times, boolean synchronized_method) {
        this.counter = counter;
        this.times = times;
        this.synchronized_method = synchronized_method;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            if (synchronized_method) {
                counter.decrementSync();
            } else {
                counter.decrement();
            }

            // Add a small delay to make thread interleaving more visible
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // Ignore exception
            }
        }
    }
}
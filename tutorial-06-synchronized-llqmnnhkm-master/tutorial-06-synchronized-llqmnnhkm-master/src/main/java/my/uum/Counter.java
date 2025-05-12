package my.uum;

public class Counter {
    private int count = 0;

    // Non-synchronized methods
    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    // Synchronized methods
    public synchronized void incrementSync() {
        count++;
    }

    public synchronized void decrementSync() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
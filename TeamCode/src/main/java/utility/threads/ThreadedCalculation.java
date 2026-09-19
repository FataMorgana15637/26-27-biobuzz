package utility.threads;

public class ThreadedCalculation<T> implements ThreadedRunnable {

    private T lastResult;
    private final long refreshRate;
    private final Calculation<T> calculation;

    public final Object objectLock = new Object();

    private long lastUpdate = System.currentTimeMillis();

    public ThreadedCalculation(
            T initialValue,
            long refreshRate,
            Calculation<T> calculation
    ) {
        this.lastResult = initialValue;
        this.refreshRate = refreshRate;
        this.calculation = calculation;
    }

    public void run() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastUpdate < refreshRate) {
            return;
        }

        lastUpdate = currentTime;

        T previous;

        synchronized (objectLock) {
            previous = lastResult;
        }

        T next = calculation.calculate(previous);

        synchronized (objectLock) {
            lastResult = next;
        }
    }

    public T getValue() {
        synchronized (objectLock) {
            return lastResult;
        }
    }

    @FunctionalInterface
    public interface Calculation<T> {
        T calculate(T previous);
    }
}

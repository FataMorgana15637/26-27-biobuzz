package utility.threads;

public class ThreadedTask<T> implements ThreadedRunnable {

    private final T object;
    private final Runnable update;
    private final Object lock;

    public ThreadedTask(
            T object,
            Runnable update
    ) {
        this(object, update, new Object());
    }

    public ThreadedTask(
            T object,
            Runnable update,
            Object lock
    ) {
        this.object = object;
        this.update = update;
        this.lock = lock;
    }

    public T get() {
        return object;
    }

    @Override
    public void run() {
        synchronized (lock) {
            update.run();
        }
    }
}
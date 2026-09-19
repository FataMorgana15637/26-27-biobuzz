package utility.threads;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadedRunner {
    public static long safety = 5;

    private static final CopyOnWriteArrayList<ThreadedRunnable>
            tasks = new CopyOnWriteArrayList<>();

    private static Thread runnerThread;

    private ThreadedRunner() {}

    public static void add(ThreadedRunnable runnable) {
        tasks.add(runnable);
    }

    public static synchronized Thread start() {

        if (runnerThread != null && runnerThread.isAlive()) {
            return runnerThread;
        }

        runnerThread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {

                for (ThreadedRunnable calculation :
                        tasks) {

                    calculation.run();
                }

                try {
                    Thread.sleep(safety);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        runnerThread.setName("ThreadedRunner");
        runnerThread.start();

        return runnerThread;
    }

    public static synchronized void stop() {

        if (runnerThread != null) {
            runnerThread.interrupt();
            runnerThread = null;
        }
    }

}

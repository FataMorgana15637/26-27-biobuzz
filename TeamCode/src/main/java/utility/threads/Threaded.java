package utility.threads;

public final class Threaded {

    private Threaded() {}

    public static <T> ThreadedCalculation<T> threaded(
            T initialValue,
            long refreshRate,
            ThreadedCalculation.Calculation<T> calculation
    ) {
        ThreadedCalculation<T> threadedCalculation =
                new ThreadedCalculation<>(
                        initialValue,
                        refreshRate,
                        calculation
                );

        ThreadedRunner.add(threadedCalculation);

        return threadedCalculation;
    }

    public static <T> ThreadedTask<T> threaded(
            T object,
            long refreshRate,
            Runnable update
    ) {
        ThreadedTask<T> task =
                new ThreadedTask<>(object, update);

        ThreadedRunner.add(task);

        return task;
    }
}
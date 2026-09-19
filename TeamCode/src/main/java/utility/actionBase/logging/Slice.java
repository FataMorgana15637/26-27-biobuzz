package utility.actionBase.logging;

public interface Slice {
    boolean isEnabled();

    String getName();

    void start();

    void complete();

    long getStartTime();

    long getEndTime();

    default boolean isRunning() {
        return getStartTime() != -1;
    }

    default boolean isCompleted() {
        return getEndTime() != -1;
    }

    default long getRunningTime() {
        if (!isEnabled()) return -1;

        if (!isCompleted()) return System.currentTimeMillis() - getStartTime();

        return getEndTime() - getStartTime();
    }
}

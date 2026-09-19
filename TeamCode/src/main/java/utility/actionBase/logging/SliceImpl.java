package utility.actionBase.logging;

import android.util.Log;

public class SliceImpl implements Slice {
    private final boolean enabled;
    private long startTime = -1;
    private long endTime = -1;
    private final String name;

    public SliceImpl(boolean enabled, String name) {
        this.enabled = enabled;
        this.name = name;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void start() {
        if (!enabled) return;

        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void complete() {
        if (!enabled) return;

        this.endTime = System.currentTimeMillis();

        Log.i(name,
            "start time = " + this.getStartTime() +
                    ", runtime = " + this.getRunningTime() +
                    ", end time = " + this.getEndTime() +
                    ", completed = " + this.isCompleted());
    }

    @Override
    public long getStartTime() {
        return this.startTime;
    }

    @Override
    public long getEndTime() {
        return this.endTime;
    }

}

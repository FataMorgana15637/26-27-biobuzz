package utility.actionBase;


import utility.actionBase.logging.Slice;
import utility.actionBase.logging.SliceContainer;
import utility.actionBase.logging.SliceImpl;

public abstract class AbstractAction implements Action, SliceContainer {
    protected boolean started = false;
    protected boolean finished = false;
    protected boolean interruptible = true;
    private final Slice slice;

    public AbstractAction() {
        this(false);
    }

    public AbstractAction(boolean enabled) {
        slice = new SliceImpl(enabled, this.getClass().getSimpleName());
    }

    public AbstractAction(boolean enabled, String name) {
        slice = new SliceImpl(enabled, name);
    }

    @Override
    public Slice getSlice() {
        return slice;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean isInterruptible() {
        return interruptible;
    }

    @Override
    public void start() {
        started = true;
        slice.start();
    }

    @Override
    public void stop() {
        slice.complete();
    }

    @Override
    public void setStarted(boolean started) {
        this.started = started;
    }

    @Override
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public AbstractAction asNonInterruptible() {
        interruptible = false;
        return this;
    }
}
package utility.actionbase.actions;

import utility.actionbase.AbstractAction;
import utility.actionbase.logging.Slice;
import utility.actionbase.logging.SliceImpl;

public class SimpleAction extends AbstractAction {
    private final Runnable action;
    private final Slice slice;

    public SimpleAction(Runnable action, String name) {
        this.action = action;
        slice = new SliceImpl(true, name);
    }

    public SimpleAction(Runnable action) {
        this.action = action;
        slice = new SliceImpl(false, null);
    }

    @Override
    public Slice getSlice() {
        return slice;
    }

    @Override
    public void update() {
        slice.start();
        action.run();
        slice.complete();
        finished = true;
    }
}

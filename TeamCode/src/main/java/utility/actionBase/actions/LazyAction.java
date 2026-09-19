package utility.actionBase.actions;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

import java.util.function.Supplier;

public class LazyAction extends AbstractAction {

    private final Supplier<Action> actionFactory;
    private Action action;

    public LazyAction(Supplier<Action> actionFactory) {
        this.actionFactory = actionFactory;
    }

    @Override
    public boolean isInterruptible() {
        return action != null && action.isInterruptible();
    }

    @Override
    public void start() {
        action = actionFactory.get();
        action.start();

        super.start();
    }

    @Override
    public void update() {
        action.update();

        if (action.isFinished()) {
            finished = true;
        }
    }

    @Override
    public void stop() {
        action.stop();
    }
}
package utility.actionbase.runners;

import utility.actionbase.AbstractAction;

import utility.actionbase.Action;

public class ParallelActionRunner extends AbstractAction {
    private final Action[] actions;

    private final boolean[] stopped;

    public ParallelActionRunner(Action... actions) {
        this.actions = actions;
        this.stopped = new boolean[actions.length];
    }

    @Override
    public boolean isInterruptible() {
        if (!interruptible) {
            return false;
        }

        for (Action action : actions) {
            if (!action.isInterruptible()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void start() {
        super.start();

        for (Action action : actions) {
            action.start();
        }
    }

    @Override
    public void update() {
        boolean allFinished = true;

        for (int i = 0; i < actions.length; i++) {
            Action action = actions[i];

            if (!action.isFinished()) {
                action.update();
            }

            if (action.isFinished() && !stopped[i]) {
                action.stop();
                stopped[i] = true;
            }

            allFinished &= action.isFinished();

        }

        finished = allFinished;
    }
}

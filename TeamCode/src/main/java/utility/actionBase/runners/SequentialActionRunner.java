package utility.actionBase.runners;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

public class SequentialActionRunner extends AbstractAction {

    private final Action[] actions;
    private int actionIndex = 0;

    public SequentialActionRunner(Action... actions) {
        this.actions = actions;
    }

    @Override
    public void start() {
        super.start();

        if (actions.length == 0) {
            finished = true;
            return;
        }

        actions[0].start();
    }

    @Override
    public void update() {

        Action current = actions[actionIndex];

        current.update();

        if (current.isFinished()) {

            current.stop();

            actionIndex++;

            if (actionIndex < actions.length) {
                actions[actionIndex].start();
            } else {
                finished = true;
            }
        }
    }
}
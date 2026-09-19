package utility.actionBase.runners;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

import java.util.Arrays;

public class RaceActionRunner extends AbstractAction {
    private final Action[] actions;

    public RaceActionRunner(Action... actions){
        this.actions = actions;
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

        for (Action action : actions) {
            action.update();
        }

        boolean anyFinished = Arrays.stream(actions).anyMatch(Action::isFinished);


        if (anyFinished) {
            for (Action action : actions) {
                action.setFinished(true);
                action.stop();
            }

            finished = true;
        }
    }
}

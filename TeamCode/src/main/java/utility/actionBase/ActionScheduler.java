package utility.actionBase;

import java.util.ArrayList;
import java.util.List;

public class ActionScheduler {
    private final ArrayList<Action> actions = new ArrayList<>();

    public void schedule(Action action){
        if (actions.contains(action)) throw new UnsupportedOperationException(
                "Trying to schedule an already scheduled action instance of type " + action.getClass().getName()
        );

        actions.add(action);
    }

    public void update(){
        ArrayList<Action> temp =new ArrayList<>(actions);
        for (Action action : temp){
            if (!action.isStarted()) action.start();

            if (!action.isFinished()) action.update();

            if (action.isFinished()) action.stop();
        }
        actions.removeIf(Action::isFinished);
    }

    public void clear(){
        actions.clear();
    }
}

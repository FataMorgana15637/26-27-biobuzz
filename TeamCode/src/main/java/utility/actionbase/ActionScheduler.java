package utility.actionbase;

import java.util.ArrayList;

public class ActionScheduler {
    private static final ArrayList<Action> actions = new ArrayList<>();

    public static void schedule(Action action){
        if (actions.contains(action)) throw new UnsupportedOperationException(
                "Trying to schedule an already scheduled action instance of type " + action.getClass().getName()
        );

        actions.add(action);
    }

    public static void update(){
//        FataMain.getTelemetry().addData("thing", 1);
        ArrayList<Action> temp = new ArrayList<>(actions);
        for (Action action : temp){
            if (!action.isStarted()) action.start();

            if (!action.isFinished()) action.update();

            if (action.isFinished()) action.stop();
        }
        actions.removeIf(Action::isFinished);
    }

    public static void clear(){
        actions.clear();
    }
}

package utility.actionBase.actions;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

import java.util.function.IntFunction;
import java.util.function.Supplier;

public class RepeatAction extends AbstractAction {
    private final int repetitons;
    private final IntFunction<Action> actionFactory;

    private int currentIteration = 0;
    private Action currentAction;

    public RepeatAction(int repetitions, IntFunction<Action> actionFactory){
        this.repetitons = repetitions;
        this.actionFactory = actionFactory;
    }

    public RepeatAction(int repetitions, Supplier<Action> actionFactory){
        this(repetitions, i  -> actionFactory.get());
    }

    @Override
    public void start(){
        currentAction = actionFactory.apply(0);
        currentAction.start();
        super.start();
    }

    @Override
    public void update(){
        currentAction.update();

        if(currentAction.isFinished()) {
            currentAction.stop();
            currentIteration++;


            if (currentIteration > repetitons) {
                finished = true;
                return;
            }
            currentAction = actionFactory.apply(currentIteration);
            currentAction.start();
        }
    }

}

package utility.actionbase.actions;

import utility.actionbase.AbstractAction;
import utility.actionbase.Action;

import java.util.function.BooleanSupplier;
import java.util.function.IntFunction;
import java.util.function.Supplier;
public class RepeatUntilAction extends AbstractAction {
    private final BooleanSupplier condition;
    private final IntFunction<Action> actionFactory;

    private int currentIteration = 0;
    private Action currentAction;

    public RepeatUntilAction(BooleanSupplier condition, IntFunction<Action> actionFactory){
        this.condition = condition;
        this.actionFactory = actionFactory;
    }

    public RepeatUntilAction(BooleanSupplier condition, Supplier<Action> actionFactory){
        this(condition, i  -> actionFactory.get());
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


            if (condition.getAsBoolean()) {
                finished = true;
                return;
            }
            currentAction = actionFactory.apply(currentIteration);
            currentAction.start();
        }
    }

}


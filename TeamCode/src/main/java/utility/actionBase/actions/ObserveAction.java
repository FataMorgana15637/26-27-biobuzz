package utility.actionBase.actions;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

public class ObserveAction extends AbstractAction {

    private final BooleanSupplier condition;
    private final Function<Boolean, Action> actionFactory;

    private Action action;
    public ObserveAction(BooleanSupplier condition, Function<Boolean, Action> actionFactory){
        this.actionFactory = actionFactory;
        this.condition = condition;
    }

    @Override
    public boolean isInterruptible(){
        return action != null && action.isInterruptible();
    }

    @Override
    public void start(){
        action = actionFactory.apply(condition.getAsBoolean());
        action.start();
        super.start();
    }

    @Override
    public void update() {
            action.update();
            if (action.isFinished()) finished = true;
    }

    @Override
    public void stop(){
        action.stop();
    }

}

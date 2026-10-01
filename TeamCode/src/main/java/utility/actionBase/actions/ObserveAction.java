package utility.actionBase.actions;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

public class ObserveAction extends AbstractAction {

    private final BooleanSupplier condition;
    private final Supplier<Action> a, b;


    private Action action;
    public ObserveAction(BooleanSupplier condition, Supplier<Action> a, Supplier<Action> b){
        this.a = a;
        this.b = b;
        this.condition = condition;
    }

    @Override
    public boolean isInterruptible(){
        return action != null && action.isInterruptible();
    }

    @Override
    public void start(){
        action = (condition.getAsBoolean() ? a : b).get();
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

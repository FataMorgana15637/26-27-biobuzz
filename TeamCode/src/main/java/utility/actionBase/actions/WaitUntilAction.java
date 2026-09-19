package utility.actionBase.actions;

import utility.actionBase.AbstractAction;

import java.util.function.BooleanSupplier;

public class WaitUntilAction extends AbstractAction {
    private final BooleanSupplier condition;

    public WaitUntilAction(BooleanSupplier condition) {
        this.condition = condition;
    }

    @Override
    public void update(){
        finished = condition.getAsBoolean();
    }

}

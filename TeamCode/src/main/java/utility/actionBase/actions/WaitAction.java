package utility.actionBase.actions;

import utility.actionBase.AbstractAction;

public class WaitAction extends AbstractAction {
    private final int delay;
    private long startTime;

    public WaitAction(int delay) {
        this.delay = delay;
    }

    @Override
    public void start(){
        startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        finished = System.currentTimeMillis() - startTime > delay;
    }
}

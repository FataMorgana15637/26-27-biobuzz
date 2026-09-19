package org.firstinspires.ftc.teamcode.fataopmode.api.fataUtil.action;

import utility.actionBase.AbstractAction;
import utility.actionBase.Action;

import java.util.function.Supplier;

public class GamepadAction extends AbstractAction {
    private final Supplier<Action> whenPressed, whenHeld, whenReleased;
    private final Supplier<Boolean> isPressed;

    private Action lastWhenPressed = Action.empty();
    private Action lastWhenHeld = Action.empty();
    private Action lastWhenReleased = Action.empty();

    private boolean lastPressed = false;

    public GamepadAction(
            Supplier<Action> whenPressed,
            Supplier<Action> whenHeld,
            Supplier<Action> whenReleased,
            Supplier<Boolean> isPressed
    ) {
        this.whenPressed = whenPressed;
        this.whenHeld = whenHeld;
        this.whenReleased = whenReleased;
        this.isPressed = isPressed;
    }

    @Override
    public void start() {
        super.start();

        lastPressed = false;
    }

    @Override
    public void update() {
        boolean pressed = isPressed.get();

        if (pressed) {
            if (!lastPressed) {
                if (!lastWhenPressed.isFinished())
                    lastWhenPressed.interrupt();

                (lastWhenPressed = whenPressed.get()).schedule();
            }

            if (lastWhenHeld.isFinished())
                (lastWhenHeld = whenHeld.get()).schedule();
        } else if (lastPressed) {
            if (!lastWhenReleased.isFinished())
                lastWhenReleased.interrupt();

            (lastWhenReleased = whenReleased.get()).schedule();
        }

        lastPressed = pressed;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

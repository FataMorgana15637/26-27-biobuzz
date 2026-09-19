package org.firstinspires.ftc.teamcode.fataopmode.api.fataUtil.action;

import utility.actionBase.Action;

import java.util.function.Supplier;

public class GamepadFactory {

    private final Supplier<Boolean> isPressed;

    private Supplier<Action> whenPressed = () -> Action.empty(),
            whenHeld = () -> Action.empty(),
            whenReleased = () -> Action.empty();

    private GamepadFactory(Supplier<Boolean> isPressed) {
        this.isPressed = isPressed;
    }

    public static GamepadFactory button(Supplier<Boolean> button) {
        return new GamepadFactory(button);
    }

    public GamepadFactory whenPressed(Supplier<Action> whenPressed) {
        this.whenPressed = whenPressed;

        return this;
    }

    public GamepadFactory whenHeld(Supplier<Action> whenHeld) {
        this.whenHeld = whenHeld;

        return this;
    }

    public GamepadFactory whenReleased(Supplier<Action> whenReleased) {
        this.whenReleased = whenReleased;

        return this;
    }

    public Action create() {
        return new GamepadAction(whenPressed, whenHeld, whenReleased, isPressed);
    }
}

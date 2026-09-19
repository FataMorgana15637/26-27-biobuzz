package utility.actionBase.actions;

import utility.actionBase.Action;

import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class Actions {

    public static Action lazy(Supplier<Action> action){
        return new LazyAction(action);
    }

    public static Action observe(BooleanSupplier condition, Function<Boolean, Action> actionFactory){
        return new ObserveAction(condition, actionFactory);
    }

    public static Action repeat(int times, IntFunction<Action> actionFactory){
        return new RepeatAction(times, actionFactory);
    }

    public static Action repeat(int times, Supplier<Action> actionFactory){
        return new RepeatAction(times, actionFactory);
    }

    public static Action repeatUntil(BooleanSupplier condition, Supplier<Action> actionFactory){
        return new RepeatUntillAction(condition, actionFactory);
    }

    public static Action repeatUntil(BooleanSupplier condition, IntFunction<Action> actionFactory){
        return new RepeatUntillAction(condition, actionFactory);
    }

    public static Action perpetually(Supplier<Action> actionFactory){
        return new RepeatUntillAction(() -> false, actionFactory);
    }

    public static Action perpetually(IntFunction<Action> actionFactory){
        return new RepeatUntillAction(() -> false, actionFactory);
    }
    public static Action simply(Runnable action){
        return new SimpleAction(action);
    }

    public static Action simply(Runnable action, String name){
        return new SimpleAction(action, name);
    }

    public static Action delay(int delay){
        return new WaitAction(delay);
    }

    public static Action waitUntil(BooleanSupplier condition){
        return new WaitUntilAction(condition);
    }

}

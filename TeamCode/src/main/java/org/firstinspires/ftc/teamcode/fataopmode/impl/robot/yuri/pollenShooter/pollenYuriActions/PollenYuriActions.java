package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.pollenYuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.YuriPollenConstents.p;

import utility.actionBase.Action;
import utility.actionBase.runners.SequentialActionRunner;
import static utility.actionBase.actions.Actions.simply;

import java.util.function.Supplier;

public class PollenYuriActions extends SequentialActionRunner {

    public static Action setTargetVelocity(Supplier<Double> velocity) {
        return new PollenYuriSetTargetVelocityAction(velocity);
    }

    public static Action setHoodTarget(Supplier<Double> target) {
        return new PollenYuriSetHoodTargetAction(target);
    }

}

package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.nectarYuriActions;

import java.util.function.Supplier;

import utility.actionBase.Action;
import utility.actionBase.runners.SequentialActionRunner;

public class NectarYuriActions extends SequentialActionRunner {

    public static Action setNecterTargetVelocity(Supplier<Double> velocity) {
        return new NectarYuriSetTargetVelocityAction(velocity);
    }

    public static Action setHoodTarget(Supplier<Double> target) {
        return new NectarYuriSetHoodTargetAction(target);
    }
}

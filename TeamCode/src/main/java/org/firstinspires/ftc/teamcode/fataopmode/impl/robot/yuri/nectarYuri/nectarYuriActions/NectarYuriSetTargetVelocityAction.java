package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.nectarYuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.YuriNectarSubsystem.yuri;
import static utility.actionBase.actions.Actions.simply;

import java.util.function.Supplier;

import utility.actionBase.runners.SequentialActionRunner;

public class NectarYuriSetTargetVelocityAction extends SequentialActionRunner {

    NectarYuriSetTargetVelocityAction(Supplier<Double> velocity) {
        super(
                simply(() -> {
                    yuri().setTargetVelocity(velocity);
                })
        );
    }

}


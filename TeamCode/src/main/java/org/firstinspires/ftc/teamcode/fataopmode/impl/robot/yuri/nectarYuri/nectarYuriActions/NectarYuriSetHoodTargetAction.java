package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.nectarYuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.YuriNectarSubsystem.yuri;
import static utility.actionBase.actions.Actions.simply;

import java.util.function.Supplier;

import utility.actionBase.Action;
import utility.actionBase.runners.SequentialActionRunner;

public class NectarYuriSetHoodTargetAction extends SequentialActionRunner {

    NectarYuriSetHoodTargetAction(Supplier<Double> target) {
        super(
                simply(() -> {
                    yuri().setHoodTarget(target);
                })
        );
    }
}

package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.pollenYuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.YuriPollenSubsystem.yuri;

import java.util.function.Supplier;

import utility.actionBase.runners.SequentialActionRunner;

import static utility.actionBase.actions.Actions.simply;
public class PollenYuriSetHoodTargetAction extends SequentialActionRunner {

    PollenYuriSetHoodTargetAction(Supplier<Double> target) {
        super(
                simply(() -> {
                    yuri().setHoodTarget(target);
                })
        );
    }
}

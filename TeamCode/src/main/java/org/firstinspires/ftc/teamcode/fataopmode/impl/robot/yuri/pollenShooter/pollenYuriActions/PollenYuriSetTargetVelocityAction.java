package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.pollenYuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.YuriPollenSubsystem.yuri;

import utility.actionBase.runners.SequentialActionRunner;
import static utility.actionBase.actions.Actions.simply;

import java.util.function.Supplier;

public class PollenYuriSetTargetVelocityAction extends SequentialActionRunner {

    PollenYuriSetTargetVelocityAction(Supplier<Double> velocity) {
        super(
                simply(() ->{
                    yuri().setTargetVelocity(velocity);
                })
        );
    }
}

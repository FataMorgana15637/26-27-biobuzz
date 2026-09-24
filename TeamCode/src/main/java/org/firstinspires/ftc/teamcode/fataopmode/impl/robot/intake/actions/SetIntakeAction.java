package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;
import static utility.actionBase.actions.Actions.simply;

import java.util.function.Supplier;

import utility.actionBase.runners.SequentialActionRunner;

public class SetIntakeAction extends SequentialActionRunner {
    SetIntakeAction(Supplier<Double> power) {
        super(
                simply(() -> {
                    intake().setIntakePower(power);
                })
        );
    }
}

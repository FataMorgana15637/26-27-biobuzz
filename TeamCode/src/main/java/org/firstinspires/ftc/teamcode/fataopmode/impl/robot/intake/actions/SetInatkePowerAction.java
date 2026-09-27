package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import utility.actionBase.runners.SequentialActionRunner;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;
import static utility.actionBase.actions.Actions.simply;

public class SetInatkePowerAction extends SequentialActionRunner {
    SetInatkePowerAction(double power){
        super(
        simply(() -> intake().setIntakePower(power))
        );
    }
}

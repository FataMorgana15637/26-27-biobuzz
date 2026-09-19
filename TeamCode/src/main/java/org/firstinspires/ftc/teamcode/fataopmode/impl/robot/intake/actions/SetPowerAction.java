package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem;
import utility.actionBase.runners.SequentialActionRunner;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;
import static utility.actionBase.actions.Actions.simply;

public class SetPowerAction extends SequentialActionRunner {
    SetPowerAction(double power){
        super(
        simply(() -> intake().setPower(power))
        );
    }
}

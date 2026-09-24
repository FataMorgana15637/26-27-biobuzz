package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.*;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions.IntakeActions.*;

import static utility.actionBase.actions.Actions.repeatUntil;

import utility.actionBase.runners.SequentialActionRunner;

public class SeparateIntakeAction extends SequentialActionRunner {
    SeparateIntakeAction(){
        super(
            repeatUntil(() -> intake().getBallCount() > 1,
                    () -> setIntake(intakeSpeed).also(setTransfer(transferSpeed)).then(
                            repeatUntil(() -> intake().isFull(), () ->
                                    setIntake(stopSpeed).also(setTransfer(transferStopSpeed))
                            ))
                    )
        );
    }
}

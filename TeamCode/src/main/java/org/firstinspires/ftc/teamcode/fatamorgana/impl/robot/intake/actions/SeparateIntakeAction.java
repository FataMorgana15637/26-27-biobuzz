package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.*;

import static utility.actionbase.actions.Actions.repeatUntil;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeServoPose;

import utility.actionbase.actions.RepeatUntilAction;

public class SeparateIntakeAction extends RepeatUntilAction {
    SeparateIntakeAction() {
        super(
                () -> intake().getBallCount() > 1,

                () -> setIntake(intakeSpeed).also(setTransfer(transferSpeed)).then(
                        repeatUntil(() -> intake().isFull(), () ->
                                setIntake(stopSpeed).also(setTransfer(transferStopSpeed))
                                        .also(setIntakePose(IntakeServoPose.UP)))
                )
        );
    }
}

package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeServoPose.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.*;

import static utility.actionbase.actions.Actions.repeatUntil;

import utility.actionbase.runners.SequentialActionRunner;

public class SeparateIntakeAction extends SequentialActionRunner {
    SeparateIntakeAction() {
        super(
                intake().empty(),

                intake().floatTransfer(),

                repeatUntil(
                        () -> intake().isFull(),

                        () -> setIntake(intakeSpeed)
                                .also(setTransfer(stopSpeed))
                ),

                setIntake(holdSpeed)
                        .also(intake().brakeTransfer())
                        .also(setIntakePose(UP))
        );
    }
}

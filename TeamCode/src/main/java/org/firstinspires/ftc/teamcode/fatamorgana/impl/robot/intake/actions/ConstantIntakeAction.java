package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.intakeSpeed;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.setIntake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.setIntakePose;

import static utility.actionbase.actions.Actions.waitUntil;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeServoPose;

import utility.actionbase.runners.SequentialActionRunner;

public class ConstantIntakeAction extends SequentialActionRunner {
     ConstantIntakeAction() {
        super(
                setIntake(intakeSpeed).then(
                        waitUntil(() -> intake().isFull())
                                .then(setIntakePose(IntakeServoPose.UP))
                )
        );
    }
}

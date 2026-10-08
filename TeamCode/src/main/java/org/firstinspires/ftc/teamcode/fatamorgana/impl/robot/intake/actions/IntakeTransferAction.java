package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.intakeSpeed;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.transferSpeed;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.setIntake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.setTransfer;

import utility.actionbase.runners.SequentialActionRunner;

public class IntakeTransferAction extends SequentialActionRunner {
    IntakeTransferAction() {
        super(
                setIntake(intakeSpeed),

                setTransfer(transferSpeed)
        );
    }
}

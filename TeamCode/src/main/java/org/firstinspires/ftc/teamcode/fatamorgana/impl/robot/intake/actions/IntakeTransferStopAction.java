package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.setIntake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.setTransfer;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants;

import utility.actionbase.runners.ParallelActionRunner;

public class IntakeTransferStopAction extends ParallelActionRunner {
    IntakeTransferStopAction(){
        super(
                setIntake(stopSpeed),
                setTransfer(stopSpeed)
        );
    }
}

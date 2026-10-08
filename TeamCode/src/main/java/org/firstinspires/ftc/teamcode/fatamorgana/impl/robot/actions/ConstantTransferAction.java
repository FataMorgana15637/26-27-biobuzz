package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions;

import utility.actionbase.runners.SequentialActionRunner;

public class ConstantTransferAction extends SequentialActionRunner {
    ConstantTransferAction() {
        super(
                YuriActions.setStopper(StopperPose.FLOW),

                IntakeActions.constantIntake(),

                intake().empty()
        );
    }
}

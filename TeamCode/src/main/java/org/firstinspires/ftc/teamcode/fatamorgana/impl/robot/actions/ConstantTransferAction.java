package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions;

import utility.actionbase.actions.RepeatUntilAction;
import utility.actionbase.runners.SequentialActionRunner;

public class ConstantTransferAction extends RepeatUntilAction {
    ConstantTransferAction() {
        super(
                () -> /*!drive().getHive().isUp ||*/ yuri().getYuriState() == YuriState.SEPARATE,

                () -> YuriActions.setStopper(StopperPose.FLOW)
                        .also(IntakeActions.constantIntake())
                        .also(intake().empty())
        );
    }
}

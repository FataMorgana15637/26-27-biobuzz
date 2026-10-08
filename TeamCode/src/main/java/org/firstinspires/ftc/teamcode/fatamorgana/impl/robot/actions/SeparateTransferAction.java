package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions.*;

import utility.actionbase.runners.SequentialActionRunner;

public class SeparateTransferAction extends SequentialActionRunner {
    SeparateTransferAction() {
        super(
                setStopper(FLOW),

                transfer(),

                intake().empty()
        );
    }
}

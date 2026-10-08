package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose.*;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions;

import utility.actionbase.runners.ParallelActionRunner;

public class SeparateIntakeAction extends ParallelActionRunner {
    public SeparateIntakeAction() {
        super(
                YuriActions.setStopper(STOP),

                IntakeActions.separateIntake()
                        .then(YuriActions.setStopper(FLOW))
        );
    }
}

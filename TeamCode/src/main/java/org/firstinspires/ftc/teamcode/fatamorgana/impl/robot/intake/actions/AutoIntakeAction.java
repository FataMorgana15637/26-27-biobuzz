package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions.IntakeActions.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import utility.actionbase.actions.ObserveAction;

public class AutoIntakeAction extends ObserveAction {
    AutoIntakeAction() {
        super(
                () -> yuri().getYuriState() == SEPARATE,
                () -> separateIntake(),
                () -> constantIntake()
        );
    }
}

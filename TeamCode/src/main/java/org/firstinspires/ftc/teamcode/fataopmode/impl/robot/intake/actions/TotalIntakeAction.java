package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions.IntakeActions.*;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions.YuriState.*;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriSubsystem.yuri;
import static utility.actionBase.actions.Actions.observe;
import static utility.actionBase.actions.Actions.simply;

import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeState;
import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions.YuriState;

import utility.actionBase.runners.SequentialActionRunner;

public class TotalIntakeAction extends SequentialActionRunner {
    TotalIntakeAction(){
        super(
                observe(
                        () -> yuri().getYuriState() == SEPARATE,
                        () -> separateIntake(),
                        () -> constantIntake()
                )
        );
    }
}

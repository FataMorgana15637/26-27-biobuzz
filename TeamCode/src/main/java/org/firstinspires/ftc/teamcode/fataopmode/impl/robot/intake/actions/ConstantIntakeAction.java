package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.*;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions.IntakeActions.*;

import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents;

import utility.actionBase.runners.SequentialActionRunner;

public class ConstantIntakeAction extends SequentialActionRunner {
    ConstantIntakeAction(){
        setIntake(intakeSpeed).also(setTransfer(transferSpeed));
    }
}

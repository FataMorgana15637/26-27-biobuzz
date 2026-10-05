package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;
import static utility.actionbase.actions.Actions.simply;

import utility.actionbase.actions.SimpleAction;

public class SetIntakeAction extends SimpleAction {
    SetIntakeAction(double power) {
        super(() -> intake().setIntakePower(power));
    }
}

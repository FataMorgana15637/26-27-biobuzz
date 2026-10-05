package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;

import utility.actionbase.actions.SimpleAction;

public class SetTransferAction extends SimpleAction {
    SetTransferAction(double power) {
        super(() -> intake().setTransferPower(power));
    }
}

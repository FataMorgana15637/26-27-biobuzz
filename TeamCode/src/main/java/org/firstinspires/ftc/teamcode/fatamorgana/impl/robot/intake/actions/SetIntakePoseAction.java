package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem.intake;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeServoPose;

import utility.actionbase.actions.SimpleAction;

public class SetIntakePoseAction extends SimpleAction {
    SetIntakePoseAction(IntakeServoPose pose) {
        super(() -> intake().setIntakePose(pose));
    }
}

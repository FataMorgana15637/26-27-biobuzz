package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.actions;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeServoPose;

import utility.actionbase.Action;

public class IntakeActions {
    public static Action setIntake(double power) {
        return new SetIntakeAction(power);
    }

    public static Action separateIntake() {
        return new SeparateIntakeAction();
    }

    public static Action setTransfer(double power) {
        return new SetTransferAction(power);
    }

    public static Action setIntakePose(IntakeServoPose pose) {
        return new SetIntakePoseAction(pose);
    }

    public static Action constantIntake() {
        return new ConstantIntakeAction();
    }

    public static Action autoIntake() {
        return new AutoIntakeAction();
    }

    public static Action transfer(){
        return new IntakeTransferAction();
    }
}

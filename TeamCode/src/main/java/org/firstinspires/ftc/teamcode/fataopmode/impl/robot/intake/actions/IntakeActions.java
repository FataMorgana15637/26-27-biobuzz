package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import utility.actionBase.Action;

public class IntakeActions {
    public static Action setIntake(double power) {
        return new SetInatkePowerAction(power);
    }

    public static Action setIntakeTransfer(double power) {
        return new IntakeTransferAction(power);
    }
}

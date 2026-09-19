package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import utility.actionBase.Action;

public class IntakeActions {
    public static Action setPower(double power){return new SetPowerAction(power);}
}

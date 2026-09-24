package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class IntakeActions {
    public static Action setIntake(Supplier<Double> power){
        return new SetIntakeAction(power);
    }
    public static Action setTransfer(Supplier<Double> power){
        return new SetTransferAction(power);
    }
    public static Action separateIntake(){
        return new SeparateIntakeAction();
    }

    public static Action constantIntake(){
        return new ConstantIntakeAction();
    }
}

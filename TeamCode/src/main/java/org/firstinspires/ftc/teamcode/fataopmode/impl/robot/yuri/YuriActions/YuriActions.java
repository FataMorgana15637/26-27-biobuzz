package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class YuriActions {
    public static Action setHood(Supplier<Double> target){
        return new SetHoodAction(target);
    }
}

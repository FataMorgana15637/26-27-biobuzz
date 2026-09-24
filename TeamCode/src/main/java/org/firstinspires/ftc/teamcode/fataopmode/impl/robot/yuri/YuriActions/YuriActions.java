package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions;

import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.HoodPose;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class YuriActions {
    public static Action setHood(HoodPose hoodPose){
        return new SetHoodAction(hoodPose);
    }
}

package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.HoodPose;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState;

import utility.actionbase.Action;

public class YuriActions {
    public static Action setHood(HoodPose hoodPose){
        return new SetHoodAction(hoodPose);
    }
    public static Action setStopper(StopperPose stopperPose) {
        return new SetStopperAction(stopperPose);
    }

    public static Action setYuriState(YuriState state){
        return new SetYuriState(state);
    }
}

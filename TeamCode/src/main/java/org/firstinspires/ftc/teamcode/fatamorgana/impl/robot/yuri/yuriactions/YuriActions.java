package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.HoodPose;

import utility.actionbase.Action;

public class YuriActions {
    public static Action setHood(HoodPose hoodPose){
        return new SetHoodAction(hoodPose);
    }
}

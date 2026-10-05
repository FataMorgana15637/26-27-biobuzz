package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import java.util.function.Supplier;


public enum HoodPose {
    HOOD_CLOSED(() -> YuriConstants.hoodClosed),
    CALC(() -> yuri().getCalcHoodAngle());

    public Supplier<Double> pose;

    HoodPose(Supplier<Double> pose){
        this.pose = pose;
    }
}

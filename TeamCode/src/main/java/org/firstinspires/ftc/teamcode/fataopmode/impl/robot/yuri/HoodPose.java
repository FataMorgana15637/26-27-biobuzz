package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriSubsystem.yuri;

import java.util.function.Supplier;


public enum HoodPose {
    HOOD_CLOSED(() -> YuriConstents.hoodClosed),
    CALC(yuri().getCalcHoodAngle());

    public Supplier<Double> pose;

    HoodPose(Supplier<Double> pose){
        this.pose = pose;
    }
}

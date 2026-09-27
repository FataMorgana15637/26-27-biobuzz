package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter;

import java.util.function.Supplier;

public enum PollenHoodPose {
    HOOD_CLOSED(() -> YuriPollenConstents.hoodClosed);

    public Supplier<Double> pose;

    PollenHoodPose(Supplier<Double> pose){
        this.pose = pose;
    }
}


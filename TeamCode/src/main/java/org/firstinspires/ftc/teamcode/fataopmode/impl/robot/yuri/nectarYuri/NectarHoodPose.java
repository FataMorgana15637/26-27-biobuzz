package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri;

import java.util.function.Supplier;

public enum NectarHoodPose {
    HOOD_CLOSED(() -> YuriNectarConstents.hoodClosed);

    public Supplier<Double> pose;

    NectarHoodPose(Supplier<Double> pose){
        this.pose = pose;
    }
}


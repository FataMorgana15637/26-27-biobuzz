package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import java.util.function.DoubleSupplier;

public enum StopperPose {
    STOP(() -> YuriConstants.stopperStop),
    FLOW(()-> YuriConstants.stopperFlow);

    private final DoubleSupplier pose;

    StopperPose(DoubleSupplier pose) {
        this.pose = pose;
    }

    public double get() {
        return pose.getAsDouble();
    }
}

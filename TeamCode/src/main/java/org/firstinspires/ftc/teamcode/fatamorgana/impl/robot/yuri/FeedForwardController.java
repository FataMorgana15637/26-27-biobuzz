package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import java.util.function.Supplier;

public class FeedForwardController {
    private final Supplier<Double> kS;
    private final Supplier<Double> kV;
    private final Supplier<Double> kP;
    private Supplier<Double> target = () -> 0.0;
    private Supplier<Double> currentVelocity = () -> 0.0;

    public FeedForwardController(Supplier<Double> ks, Supplier<Double> kv, Supplier<Double> ka) {
        kS = ks;
        kV = kv;
        kP = ka;
    }

    public double update(Supplier<Double> targetVelocity){
        double error = targetVelocity.get() - currentVelocity.get();

        double feedForward = kS.get() * Math.signum(targetVelocity.get())
                + kV.get() * targetVelocity.get();

        double feedBack = kP.get() * error;

        return feedForward + feedBack;
    }

    public void setCurrentVelocity(Supplier<Double> currentVelocity) {
        this.currentVelocity = currentVelocity;
    }
}

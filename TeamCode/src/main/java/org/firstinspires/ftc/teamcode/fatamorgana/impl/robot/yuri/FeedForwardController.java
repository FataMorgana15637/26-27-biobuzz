package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import java.util.function.Supplier;

public class FeedForwardController {
    private final Supplier<Double> kS;
    private final Supplier<Double> kV;
    private final Supplier<Double> kA;
    private Supplier<Double> target;
    private static final double MAX_ACCELERATION = 2000;
    private double profiledVelocity = 0;

    private long lastTime = System.nanoTime();


    public FeedForwardController(Supplier<Double> ks, Supplier<Double> kv, Supplier<Double> ka) {
        kS = ks;
        kV = kv;
        kA = ka;
    }

    public double update(Supplier<Double> targetVelocity){
        long now = System.nanoTime();

        double dt = (now - lastTime) / 1e9;
        lastTime = now;

        double maxDelta = MAX_ACCELERATION * dt;

        double previousVelocity = profiledVelocity;

        double error = targetVelocity.get() - profiledVelocity;

        profiledVelocity += Math.max(
                -maxDelta,
                Math.min(maxDelta, error)
        );

        double acceleration = (profiledVelocity - previousVelocity) / dt;

        if (profiledVelocity <= 0) {
            return 0;
        }

        return kS.get()
                + kV.get() * profiledVelocity
                + kA.get() * acceleration;
    }


}

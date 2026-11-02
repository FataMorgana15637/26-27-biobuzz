package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import java.util.function.Supplier;

public class PFController {
    private final Supplier<Double> p;
    private final Supplier<Double> f;
    private final Supplier<Double> bigP;
    private final Supplier<Double> bigF;
    private final Supplier<Double> pidSwitch;
    private double currentVelocity;
    private Supplier<Double> target;

    private double power;

    public PFController(Supplier<Double> p, Supplier<Double> f, Supplier<Double> bigP, Supplier<Double> bigF, Supplier<Double> pidSwitch) {
        this.p = p;
        this.f = f;
        this.bigP = bigP;
        this.bigF = bigF;
        this.pidSwitch = pidSwitch;
    }

    public double update(Supplier<Double> target) {
        this.target = target;
        double error = target.get() - currentVelocity;

        power = Math.abs(error) < pidSwitch.get() ?
                f.get() * target.get()  + p.get() * error :
                bigF.get() * target.get() + bigP.get() * error;

        return power;
    }

    public void setCurrentVelocity(double currentVelocity){
        this.currentVelocity = currentVelocity;
    }

    public double getPower(){
        return power;
    }

}


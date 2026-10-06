package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive;

import com.pedropathing.math.Pose;

public enum Hive {
//    front is towards the garden pf each alliance
    FRONT_RED(0,0,0,0),
    FRONT_BLUE(0,0,0,0),
    BACK_RED(0,0,0,0),
    BACK_BLUE(0,0,0,0);

    private final double maxLeft;
    private final double maxRight;
    private final double targetLeft;
    private final double targetRight;
    public static final double height = 0.0;
    private static double y = 0;


    Hive(double maxLeft, double maxRight, double targetLeft, double targetRight){
        this.maxLeft = maxLeft;
        this.maxRight = maxRight;
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
    }

    public Pose getTarget(double x){
        double targetX =  targetLeft + (x - maxLeft)
                / (maxRight - maxLeft)
                * (targetRight - targetLeft);
        return new Pose(targetX, y, 0);
    }


}

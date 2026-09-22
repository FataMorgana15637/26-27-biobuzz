package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public enum HivePose {
//    front is towards the garden pf each alliance
    FRONT_RED(0,0,0,0,0),
    FRONT_BLUE(0,0,0,0,0),
    BACK_RED(0,0,0,0,0),
    BACK_BLUE(0,0,0,0,0);

    private final double maxLeft;
    private final double maxRight;
    private final double targetLeft;
    private final double targetRight;

    private final double y;

    HivePose(double maxLeft, double maxRight, double targetLeft, double targetRight, double y){
        this.maxLeft = maxLeft;
        this.maxRight = maxRight;
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
        this.y = y;
    }

    public Pose getTarget(double x){
        double targetX =  targetLeft + (x - maxLeft)
                / (maxRight - maxLeft)
                * (targetRight - targetLeft);
        return new Pose(targetX, y, 0);
    }
}

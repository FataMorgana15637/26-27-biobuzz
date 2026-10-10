package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive;

import static org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour.*;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour;

public enum Hive {
//    front is towards the garden of each alliance
    FRONT_RED(0,0,0,0, RED,34, true),
    FRONT_BLUE(0,0,0,0, BLUE, 42,false),
    BACK_RED(0,0,0,0, RED, 30,false),
    BACK_BLUE(0,0,0,0, BLUE, 38,true);

    private final double maxLeft;
    private final double maxRight;
    private final double targetLeft;
    private final double targetRight;
    private final AllianceColour allianceColour;
    public final int minApril;
    public final int maxApril;
    public static final double height = 0.0;
    private static double y = 0;
    public boolean isUp;


    Hive(double maxLeft, double maxRight, double targetLeft, double targetRight, AllianceColour allianceColour, int minApril, boolean isUp){
        this.maxLeft = maxLeft;
        this.maxRight = maxRight;
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
        this.allianceColour = allianceColour;
        this.minApril = minApril;
        this.maxApril = minApril + 3;
        this.isUp = isUp;
    }

    public Pose getTarget(double x){
        double targetX =  targetLeft + (x - maxLeft)
                / (maxRight - maxLeft)
                * (targetRight - targetLeft);
        return new Pose(targetX, y, 0);
    }


}

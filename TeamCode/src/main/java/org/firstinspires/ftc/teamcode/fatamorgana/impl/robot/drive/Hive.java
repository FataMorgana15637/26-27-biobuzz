package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive;

import static org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour.*;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour;

public enum Hive {
//    front is towards the garden of each alliance
    FRONT_RED(10.3937/2,
        144-(10.3937/2),
        144 - 94.75+3,
        144 - 74.75-3,
        63.846-3,
        RED,
        34,
        true),
    FRONT_BLUE(144-(10.3937/2),
            10.3937/2,
            94.75-3,
            74.75+3,
            144-63.846-3,
            BLUE,
            42,
            false),
    BACK_RED(144-(10.3937/2),
            10.3937/2,
            144 - 74.75-3,
            144 - 94.75+3,
            144-63.846-3,
            RED,
            30,
            false),
    BACK_BLUE(10.3937/2,
            144-(10.3937/2),
            74.75+3,
            94.75-3,
            63.846-3,
            BLUE,
            38,
            true);

    private final double maxLeft;
    private final double maxRight;
    private final double targetLeft;
    private final double targetRight;
    private final AllianceColour allianceColour;
    public final int minApril;
    public final int maxApril;
    public static final double height = 0.0;
    private final double y;
    public boolean isUp;


    Hive(double maxLeft, double maxRight, double targetLeft, double targetRight,double y, AllianceColour allianceColour, int minApril, boolean isUp){
        this.maxLeft = maxLeft;
        this.maxRight = maxRight;
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
        this.y = y;
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

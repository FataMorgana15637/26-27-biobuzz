package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class YuriConstants {
    public static double f = 0.0013;
    public static double p = 0.005;
    public static double maxBangConstants = 1;
    public static double cmToInches = 0.393701;

    public static double hoodDebug = -1;
    public static double hoodOpen = 0.0;
    public static double hoodClosed = 0.2;

    public static double stopperDebug = -1;
    public static double stopperStop = 0.43;
    public static double stopperFlow = 0.528;

    public static double shooterOffset = 0.0;
    public static double shooterHeight = 9.7638;
    public static double g = 386.0886;
    public static double minBallVelocity;
    public static double maxBallVelocity;
    public static double passHeightDiff = -shooterHeight;
    public static double hiveHeight = 53.15 * cmToInches;
    public static double passHeight = 0.0;
    public static double passTrajectoryAngle = Math.toRadians(-70);
    public static double scoreTrajectoryAngle = Math.toRadians(-35);
    public static double tolerance = 100;
    public static double hoodMinTicks = 0.0;
    public static double hoodMinAngle = Math.toRadians(32.43);
    public static double hoodMaxAngle = Math.toRadians(55);
    public static double hoodMaxTicks = 0.375;

}

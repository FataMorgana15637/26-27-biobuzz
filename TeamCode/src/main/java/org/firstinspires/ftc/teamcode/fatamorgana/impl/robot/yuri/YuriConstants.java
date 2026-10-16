package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class YuriConstants {
    public static double f = 0.0013;
    public static double p = 0.005;
    public static double maxBangConstants = 1;

    public static double hoodDebug = -1;
    public static double hoodOpen = 0.0;
    public static double hoodClosed = 0.2;

    public static double stopperDebug = -1;
    public static double stopperStop = 0.43;
    public static double stopperFlow = 0.528;

    public static double shooterOffset = 0.0;
    public static double shooterHeight = 0.0;
    public static double g = 9.80665;
    public static double minBallVelocity;
    public static double maxBallVelocity;
    public static double passHeightDiff = -shooterHeight;
    public static double hiveHeight = 0.0;
    public static double passHeight = 0.0;
    public static double passLaunchAngle = 0.0;
    public static double scoreLaunchAngle = 0.0;
    public static double tolerance = 100;
    public static double hoodMinTicks = 0.43;
    public static double hoodMinAngle = 32.43;
    public static double hoodMaxAngle = 55;
    public static double hoodMaxTicks = 0.05;
}

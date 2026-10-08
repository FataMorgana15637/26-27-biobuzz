package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class IntakeConstants {
    public static double intakeSpeed = 1.0;
    public static double transferSpeed = intakeSpeed;
    public static double outtakeSpeed = -1.0;
    public static double transferOuttakeSpeed = outtakeSpeed;
    public static double stopSpeed = 0.0;
    public static double transferStopSpeed = stopSpeed;

    public static double servoInit = 0.0;
    public static double servoIntake = 0.0;
    public static double servoUp = 0.0;
    public static int servoDebug = -1;
    public static double holdSpeed = 0.4;
}

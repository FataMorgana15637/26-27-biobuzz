package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake;

import com.bylazar.configurables.annotations.Configurable;

import java.util.function.Supplier;

@Configurable
public class IntakeConstents {
    public static Supplier<Double> intakeSpeed = () -> 1.0;
    public static Supplier<Double> transferSpeed = intakeSpeed;
    public static Supplier<Double> outtakeSpeed = () ->  -1.0;
    public static Supplier<Double> transferOuttakeSpeed = outtakeSpeed;
    public static Supplier<Double> stopSpeed = () -> 0.0;
    public static Supplier<Double> transferStopSpeed = stopSpeed;

    public static double servoInit = 0.0;
    public static double servoIntake = 0.0;
    public static double servoLift = 0.0;
    public static int servoDebug = -1;
}

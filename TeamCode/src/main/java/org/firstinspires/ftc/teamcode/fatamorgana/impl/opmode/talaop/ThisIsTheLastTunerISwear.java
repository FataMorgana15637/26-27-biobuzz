package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.talaop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class ThisIsTheLastTunerISwear extends LinearOpMode {
    public double manualPower = 0.3;
    public boolean closedLoop = false;
    public double targetVelocity = 3000;
    public double kS = 0.0;
    public double kV = 0.0001;
    public double kP = 0.0001;
    public double maxPower = 0.8;
    public double referenceVoltage = 12.0;
    private MotorEx motor;
    private MotorEx motorTwo;
    private VoltageSensor voltageSensor;

    public void runOpMode() {

        motor = hardwareMap.get(MotorEx.class, "yuri");
        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        motor.setRunMode(MotorEx.RunMode.RawPower);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        // Capture battery voltage as initial reference.
        referenceVoltage = voltageSensor.getVoltage();

        telemetry.addLine("Flywheel Tuner Ready");
        telemetry.addData("Reference Voltage", referenceVoltage);
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double batteryVoltage = voltageSensor.getVoltage();
            double currentVelocity = motor.getVelocity();

            // ======================================
            // Calculate requested power
            // ======================================

            double error = targetVelocity - currentVelocity;

            double feedforward =
                    kS * Math.signum(targetVelocity)
                            + kV * targetVelocity;

            double feedback = kP * error;

            double requestedPower;

            if (closedLoop) {
                requestedPower = feedforward + feedback;
            } else {
                requestedPower = manualPower;
            }

            // ======================================
            // Apply output limits
            // ======================================

            double appliedPower = Range.clip(
                    requestedPower,
                    -maxPower,
                    maxPower
            );

            motor.set(appliedPower);

            // ======================================
            // Voltage calculations
            // ======================================

            double motorVoltage = appliedPower * batteryVoltage;

            double feedforwardVoltage =
                    feedforward * referenceVoltage;

            double feedbackVoltage =
                    feedback * referenceVoltage;

            // Convert tuned gains to voltage-domain gains
            double finalKS = kS * referenceVoltage;
            double finalKV = kV * referenceVoltage;
            double finalKP = kP * referenceVoltage;

            // Estimate kV from a manual steady-state test
            double estimatedKV = Double.NaN;

            if (!closedLoop && Math.abs(currentVelocity) > 100) {
                estimatedKV =
                        (appliedPower
                                - kS * Math.signum(currentVelocity))
                                / currentVelocity
                                * referenceVoltage;
            }

            // ======================================
            // Telemetry
            // ======================================

            telemetry.addLine("=== MODE ===");
            telemetry.addData("Mode",
                    closedLoop ? "PID + Feedforward" : "Manual Power");

            telemetry.addLine("=== FLYWHEEL ===");
            telemetry.addData("Target Velocity", targetVelocity);
            telemetry.addData("Current Velocity", currentVelocity);
            telemetry.addData("Error", error);

            telemetry.addLine("=== POWER ===");
            telemetry.addData("Manual Power", manualPower);
            telemetry.addData("Requested Power", requestedPower);
            telemetry.addData("Applied Power", appliedPower);

            telemetry.addLine("=== VOLTAGE ===");
            telemetry.addData("Battery Voltage", batteryVoltage);
            telemetry.addData("Motor Voltage (estimated)", motorVoltage);
            telemetry.addData("Reference Voltage", referenceVoltage);

            telemetry.addLine("=== TUNED GAINS ===");
            telemetry.addData("kS (power)", kS);
            telemetry.addData("kV (power)", kV);
            telemetry.addData("kP (power)", kP);

            telemetry.addLine("=== FINAL VOLTAGE GAINS ===");
            telemetry.addData("kS (voltage)", finalKS);
            telemetry.addData("kV (voltage)", finalKV);
            telemetry.addData("kP (voltage)", finalKP);

            telemetry.addLine("=== MEASURED kV ===");
            telemetry.addData("Estimated kV (V/(tick/s))",
                    estimatedKV);

            telemetry.update();

            idle();
        }

        motor.set(0);
    }
}

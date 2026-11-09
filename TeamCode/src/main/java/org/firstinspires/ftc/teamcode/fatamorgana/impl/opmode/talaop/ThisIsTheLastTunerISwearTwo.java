
package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.talaop;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import java.util.ArrayList;
import java.util.List;

@Configurable
public class ThisIsTheLastTunerISwearTwo extends LinearOpMode {

    private MotorEx motor;
    private VoltageSensor voltageSensor;

    private static final long maxTime = 10000;
    private static final int samples = 30;
    private static final double startPower = 0.3;
    private static final double endPower = 1.0;

    public static double kP = 0.0;
    public static double targetVelocity = 2000;

    private double kS;
    private double kV;

    private final List<Double> velocities = new ArrayList<>();
    private final List<Double> voltages = new ArrayList<>();

    @Override
    public void runOpMode() throws InterruptedException {
        motor = new MotorEx(hardwareMap, "yuri");
        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        waitForStart();

        try {
            if (!opModeIsActive()) return;

            // PHASE 1: Feedforward characterization
            for (int step = 3; step <= 10; step++) {
                double power = step / 10.0;

                telemetry.addLine("Feedforward characterization");
                telemetry.addData("Testing power", power);
                telemetry.update();

                if (!runTest(power)) {
                    telemetry.addLine("Failed to stabilize!");
                    telemetry.update();
                    break;
                }
            }

            if (!opModeIsActive()) return;

            // Calculate kS and kV
            if (velocities.size() < 2 || !calculateRegression()) {
                telemetry.addLine("Not enough valid measurements!");
                telemetry.update();
                return;
            }

            // PHASE 2: Closed-loop tuning
            while (opModeIsActive()) {

                double currentVelocity = motor.getVelocity();
                double error = targetVelocity - currentVelocity;

                double feedforward =
                        Math.signum(targetVelocity) * kS
                                + kV * targetVelocity;

                double feedback = kP * error;

                double targetVoltage = feedforward + feedback;

                double batteryVoltage = voltageSensor.getVoltage();

                double requestedPower = targetVoltage / batteryVoltage;

                double appliedPower = Range.clip(requestedPower, 0, 1);

                motor.set(appliedPower);

                telemetry.addLine("Closed-loop tuning");

                telemetry.addData("kS", kS);
                telemetry.addData("kV", kV);
                telemetry.addData("kP", kP);

                telemetry.addData("Target velocity", targetVelocity);
                telemetry.addData("Current velocity", currentVelocity);
                telemetry.addData("Error", error);

                telemetry.addData("Feedforward voltage", feedforward);
                telemetry.addData("Feedback voltage", feedback);
                telemetry.addData("Requested voltage", targetVoltage);

                telemetry.addData("Battery voltage", batteryVoltage);
                telemetry.addData("Requested power", requestedPower);
                telemetry.addData("Applied power", appliedPower);

                telemetry.update();

                idle();
            }

        } finally {
            motor.set(0);
        }
    }

    private boolean runTest(double power) throws InterruptedException {
        long start = System.currentTimeMillis();

        motor.set(power);

        double lastVelocity = motor.getVelocity();
        int stableSamples = 0;

        // Wait for steady velocity
        while (opModeIsActive()
                && System.currentTimeMillis() - start < maxTime) {

            double currentVelocity = motor.getVelocity();

            if (Math.abs(currentVelocity - lastVelocity) <= 20) {
                stableSamples++;
            } else {
                stableSamples = 0;
            }

            if (stableSamples >= 10) {
                break;
            }

            lastVelocity = currentVelocity;
            sleep(20);
        }

        if (!opModeIsActive() || stableSamples < 10) {
            return false;
        }

        // Collect measurements
        double totalVelocity = 0;
        double totalVoltage = 0;

        for (int i = 0; i < samples; i++) {
            if (!opModeIsActive()) return false;

            double currentVelocity = motor.getVelocity();
            double batteryVoltage = voltageSensor.getVoltage();

            totalVelocity += currentVelocity;
            totalVoltage += power * batteryVoltage;

            sleep(20);
        }

        velocities.add(totalVelocity / samples);
        voltages.add(totalVoltage / samples);

        return true;
    }

    private boolean calculateRegression() {
        int n = velocities.size();

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumXX = 0;

        for (int i = 0; i < n; i++) {
            double x = velocities.get(i);
            double y = voltages.get(i);

            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumXX += x * x;
        }

        double denominator = n * sumXX - sumX * sumX;

        if (denominator == 0) {
            return false;
        }

        kV = (n * sumXY - sumX * sumY) / denominator;
        kS = (sumY - kV * sumX) / n;

        return true;
    }
}

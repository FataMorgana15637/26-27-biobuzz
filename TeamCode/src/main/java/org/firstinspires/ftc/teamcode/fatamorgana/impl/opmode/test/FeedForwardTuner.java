
package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.test;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@TeleOp(name = "FeedForward Tuner", group = "Tuning")
@Configurable
public class FeedForwardTuner extends LinearOpMode {

    private MotorEx motor;
    private MotorEx motorTwo;

    // Test settings - adjust these before running.
    public static double[] POWER_LEVELS = {
            0.20,
            0.35,
            0.50,
            0.65,
            0.80,
            0.90,
            1.00
    };

    public static double TEST_DURATION = 12.0;
    public static double SAMPLE_INTERVAL = 0.05;

    private VoltageSensor voltageSensor;

    private BufferedWriter writer;
    private File logFile;

    @Override
    public void runOpMode() throws InterruptedException {

        motor = new MotorEx(hardwareMap, "yuri");
        motorTwo = new MotorEx(hardwareMap, "yuriTwo");

        motor.setRunMode(Motor.RunMode.RawPower);
        motorTwo.setRunMode(Motor.RunMode.RawPower);

        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        motorTwo.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        motor.setInverted(false);
        motorTwo.setInverted(true);

        voltageSensor = hardwareMap.voltageSensor.iterator().next();

        telemetry.addLine("FeedForward Characterization");
        telemetry.addLine("Remove balls from the shooter.");
        telemetry.addLine("Make sure the flywheel can spin safely.");
        telemetry.addLine("Press START to begin.");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        try {
            createLogFile();

            telemetry.addData("CSV file", logFile.getAbsolutePath());
            telemetry.addLine("Starting test...");
            telemetry.update();

            // Initial stationary period.
            setPower(0);
            sleep(1000);

            // Run the three acceleration / steady-state tests.
            for (int i = 0; i < POWER_LEVELS.length; i++) {
                runTest(i + 1, POWER_LEVELS[i]);
            }

            setPower(0);

            writer.flush();
            writer.close();

            telemetry.addLine("TEST COMPLETE");
            telemetry.addData("Saved CSV", logFile.getAbsolutePath());
            telemetry.update();

            sleep(5000);

        } catch (IOException e) {

            setPower(0);

            telemetry.addData("CSV ERROR", e.getMessage());
            telemetry.update();

        } finally {
            setPower(0);

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    private void createLogFile() throws IOException {

        File directory = AppUtil.ROBOT_DATA_DIR;

        if (!directory.exists()) {
            directory.mkdirs();
        }

        logFile = new File(directory, "feedforward_data.csv");

        writer = new BufferedWriter(new FileWriter(logFile, false));

        writer.write(
                "test,time,power,batteryVoltage,appliedVoltage,velocity,acceleration"
        );

        writer.newLine();
    }

    private void runTest(int testNumber, double power)
            throws IOException, InterruptedException {

        setPower(power);

        long startTime = System.nanoTime();
        long previousTime = startTime;

        double previousVelocity = motor.getVelocity();

        double lastSampleTime = 0;

        while (opModeIsActive()) {

            long currentTime = System.nanoTime();

            double elapsedTime =
                    (currentTime - startTime) / 1e9;

            if (elapsedTime >= TEST_DURATION) {
                break;
            }

            if (elapsedTime - lastSampleTime >= SAMPLE_INTERVAL) {

                double dt = (currentTime - previousTime) / 1e9;

                double velocity = motor.getVelocity();

                double acceleration = 0;

                if (dt > 0) {
                    acceleration =
                            (velocity - previousVelocity) / dt;
                }

                double batteryVoltage = voltageSensor.getVoltage();

                // Approximate voltage commanded to the motor.
                double appliedVoltage = power * batteryVoltage;

                writer.write(String.format(
                        java.util.Locale.US,
                        "%d,%.4f,%.4f,%.4f,%.4f,%.4f,%.4f",
                        testNumber,
                        elapsedTime,
                        power,
                        batteryVoltage,
                        appliedVoltage,
                        velocity,
                        acceleration
                ));

                writer.newLine();

                previousVelocity = velocity;
                previousTime = currentTime;
                lastSampleTime = elapsedTime;

                telemetry.addData("Test", testNumber);
                telemetry.addData("Power", power);
                telemetry.addData("Time", "%.2f / %.2f s",
                        elapsedTime, TEST_DURATION);
                telemetry.addData("Velocity", "%.1f ticks/s", velocity);
                telemetry.addData("Acceleration", "%.1f ticks/s^2",
                        acceleration);
                telemetry.addData("Battery", "%.2f V", batteryVoltage);
                telemetry.update();
            }

            sleep(5);
        }

        writer.flush();

        setPower(0);
        while(opModeIsActive() && Math.abs(motor.getVelocity()) > 10){
            telemetry.addData("waiting for flywheel", motor.getVelocity());
            telemetry.update();
            sleep(50);
        }
    }

    private void setPower(double power) {
        motor.set(power);
        motorTwo.set(power);
    }
}

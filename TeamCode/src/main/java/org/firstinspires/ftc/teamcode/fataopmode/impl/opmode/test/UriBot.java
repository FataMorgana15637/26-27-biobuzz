package org.firstinspires.ftc.teamcode.fataopmode.impl.opmode.test;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "uriBot", group = "test")
@Configurable
public class UriBot extends LinearOpMode {
    private double shooterVel = 0.2;
    @Override
    public void runOpMode() throws InterruptedException {
//         Declare our motors
//         Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
        DcMotor intakeOne = hardwareMap.dcMotor.get("intakeOne");
        DcMotor intakeTwo = hardwareMap.dcMotor.get("intakeTwo");
        DcMotor transfer = hardwareMap.dcMotor.get("transfer");
        DcMotor shooter = hardwareMap.dcMotor.get("shooter");

        intakeOne.setDirection(DcMotorSimple.Direction.REVERSE);

//         Reverse the right side motors. This may be wrong for your setup.
//         If your robot moves backwards when commanded to go forwards,
//         reverse the left side instead.
//         See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.left_bumper){
            intakeOne.setPower(1);
            intakeTwo.setPower(1);
            transfer.setPower(1);
            } else {
                intakeOne.setPower(0);
                intakeTwo.setPower(0);
                transfer.setPower(0);
            }

            if (gamepad1.right_bumper){
                intakeOne.setPower(1);
                intakeTwo.setPower(1);
//                transfer.setPower(1);
            } else {
                intakeOne.setPower(0);
                intakeTwo.setPower(0);
                transfer.setPower(0);
            }

            if (gamepad1.right_trigger > 0.3){
                shooter.setPower(shooterVel);
            } else {
                shooter.setPower(0);
            }

            if (gamepad1.dpad_up){
                shooterVel += 0.05;
                sleep(100);
            }

            if (gamepad1.dpad_down){
                shooterVel -= 0.05;
                sleep(100);
            }

            telemetry.addData("yuri vel", shooterVel);
            telemetry.update();

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

        }
    }
}


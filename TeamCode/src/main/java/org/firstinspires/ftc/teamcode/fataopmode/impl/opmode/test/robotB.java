package org.firstinspires.ftc.teamcode.fataopmode.impl.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class robotB extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
    // Declare our motors
    // Make sure your ID's match your configuration
    DcMotor frontLeftMotor = hardwareMap.dcMotor.get("fl");
    DcMotor backLeftMotor = hardwareMap.dcMotor.get("bl");
    DcMotor frontRightMotor = hardwareMap.dcMotor.get("fr");
    DcMotor backRightMotor = hardwareMap.dcMotor.get("br");
    DcMotor pollenShooter = hardwareMap.dcMotor.get("Pshooter");
    DcMotor nectarShooter = hardwareMap.dcMotor.get("Nshooter");
    DcMotor intake = hardwareMap.dcMotor.get("intake");
    DcMotor transfer = hardwareMap.dcMotor.get("transfer");
    // Reverse the right side motors. This may be wrong for your setup.
    // If your robot moves backwards when commanded to go forwards,
    // reverse the left side instead.
    // See the note about this earlier on this page.
    frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    waitForStart();

    if (isStopRequested()) return;

    while (opModeIsActive()) {
        if(gamepad1.right_bumper) {
            intake.setPower(1);
            transfer.setPower(1);
        } else {
            intake.setPower(0);
            transfer.setPower(0);
        }

        if(gamepad1.left_bumper) {
            pollenShooter.setPower(1);
            nectarShooter.setPower(1);
            transfer.setPower(1);
            intake.setPower(1);
        } else {
            pollenShooter.setPower(0);
            nectarShooter.setPower(0);
            transfer.setPower(0);
            intake.setPower(0);
        }

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

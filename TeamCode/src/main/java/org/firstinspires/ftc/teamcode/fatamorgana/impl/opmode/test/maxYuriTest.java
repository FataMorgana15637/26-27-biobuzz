package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.test;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class maxYuriTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor shooter = hardwareMap.dcMotor.get("yuri");
        DcMotor shooterTwo = hardwareMap.dcMotor.get("yuriTwo");
        double yuriVel = 0.0;
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterTwo.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) return;

        while (!isStopRequested()){
            shooterTwo.setPower(yuriVel);
            shooter.setPower(yuriVel);

            if (gamepad1.dpad_up){
                yuriVel += 0.05;
                sleep(100);
            }

            if (gamepad1.dpad_down) {
                yuriVel -= 0.05;
                sleep(100);
            }
            telemetry.addData("yurivel", yuriVel);
            telemetry.update();
        }
    }
}

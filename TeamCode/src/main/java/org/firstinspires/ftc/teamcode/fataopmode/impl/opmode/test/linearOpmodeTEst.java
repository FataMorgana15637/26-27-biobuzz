package org.firstinspires.ftc.teamcode.fataopmode.impl.opmode.test;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
@Configurable
public class linearOpmodeTEst extends LinearOpMode {
    private static double thing = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        while (!isStopRequested()){
            telemetry.addData("thing", thing);
            if (gamepad1.right_bumper){
                thing += 10;
            }
            telemetry.update();
        }
    }
}

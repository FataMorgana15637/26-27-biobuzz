package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.test;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name = "Webcam Test", group = "Test")
public class WebcamTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        WebcamName webcam = hardwareMap.get(
                WebcamName.class, "Webcam 1"
        );

        VisionPortal visionPortal = new VisionPortal.Builder()
                .setCamera(webcam)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .setCameraResolution(new Size(1280, 800))
                .build();
        FtcDashboard.getInstance().startCameraStream(visionPortal, 60);

        telemetry.addLine("Webcam initialized!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData(
                    "Camera state",
                    visionPortal.getCameraState()
            );
            telemetry.update();
            sleep(100);
        }

        visionPortal.close();
    }
}

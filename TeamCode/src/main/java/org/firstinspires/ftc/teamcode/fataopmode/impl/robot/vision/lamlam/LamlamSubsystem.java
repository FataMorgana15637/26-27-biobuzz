package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.vision.lamlam;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.fataopmode.FataMain;
import org.firstinspires.ftc.teamcode.fataopmode.api.opmode.AllianceColour;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

public class LamlamSubsystem extends Subsystem {
    private static final LamlamSubsystem lamlam = new LamlamSubsystem();

    public static LamlamSubsystem lamlam(){
        return lamlam;
    }

    private boolean isValid = false;
    private Limelight3A lamlamCam;
    private int pipeline = 0;
    @Override
    public void hardwareInit() {
        lamlamCam = getLimelight();

        lamlamCam.setPollRateHz(50);

        lamlamCam.start();
    }

    @Override
    public void opModeInit() {
        if (FataMain.getAllianceColour() == AllianceColour.RED){
            pipeline = 0;
        } else if (FataMain.getAllianceColour() == AllianceColour.BLUE) {
            pipeline = 1;
        }else pipeline = 2;
    }

    @Override
    public void play() {


    }

    @Override
    public void loop() {
        LLResult llResult = lamlamCam.getLatestResult();
        isValid = llResult != null && llResult.isValid();
    }

    @Override
    public void stop() {

    }
}

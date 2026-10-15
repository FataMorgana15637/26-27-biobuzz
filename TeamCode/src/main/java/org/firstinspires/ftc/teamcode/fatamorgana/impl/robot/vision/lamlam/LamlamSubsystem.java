package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.vision.lamlam;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;

import static utility.actionbase.actions.Actions.simply;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.fatamorgana.FataMain;
import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour;
import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.Hive;

import utility.actionbase.Action;

public class LamlamSubsystem extends Subsystem {
    private static final LamlamSubsystem lamlam = new LamlamSubsystem();
    public static LamlamSubsystem lamlam(){
        return lamlam;
    }
    private Limelight3A lamlamCam;
    private boolean isValid = false;
    private final ElapsedTime elapsedTime = new ElapsedTime();
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
        hiveUpdate(llResult, isValid).schedule();
    }

    @Override
    public void stop() {

    }

    private Action hiveUpdate(LLResult llResult, boolean isValid) {
        return simply(() -> {
            Hive hive = drive().getHive();
            if (drive().canSeeHive() && isValid) {
                double aprilTag = llResult.getFiducialResults().get(0).getFiducialId();
                hive.isUp = (aprilTag <= hive.maxApril && aprilTag >= hive.minApril);
            }else hive.isUp = false;
            Hive.BACK_RED.isUp = !Hive.FRONT_RED.isUp;
            Hive.BACK_BLUE.isUp = !Hive.FRONT_BLUE.isUp;
        });
    }
}

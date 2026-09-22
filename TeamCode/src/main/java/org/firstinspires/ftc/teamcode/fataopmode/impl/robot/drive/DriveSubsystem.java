package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.math.Pose;
import org.firstinspires.ftc.teamcode.fataopmode.FataMain;
import org.firstinspires.ftc.teamcode.fataopmode.api.opmode.AllianceColour;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


public class DriveSubsystem extends Subsystem {

    private static DriveSubsystem drive = new DriveSubsystem();

    public static DriveSubsystem drive(){
        return drive;
    }

    public static Follower follower;
    private Pose currentPose;

    @Override
    public void hardwareInit() {
    }

    @Override
    public void opModeInit() {
        follower = Constants.createFollower(FataMain.getCurrentOpMode().hardwareMap);
    }

    @Override
    public void play() {
        FataMain.getTelemetry().addData("pose", follower.pose());
    }

    @Override
    public void loop() {
        follower.update();
        currentPose = follower.pose();

    }

    @Override
    public void stop() {

    }

    public double getHeading(){
        return Math.toDegrees(follower.pose().heading());
    }

    public Pose getPose(){
        return currentPose;
    }

    public double x(){
        return currentPose.x();
    }

    public double y(){
        return currentPose.y();
    }

    public void TeleOpDrive(double forward,double lateral,double turn) {
        DrivePowers powers = ManualDrive.fieldCentric(forward, lateral, turn, Math.toRadians(getHeading()));
        follower.manual(powers);
    }

    public Hive getHive(){
        if (y() <= 72 ) return FataMain.getAllianceColour() == AllianceColour.RED ?
                Hive.FRONT_RED : Hive.BACK_BLUE;

        if (y() > 72 ) return FataMain.getAllianceColour() == AllianceColour.RED ?
                Hive.BACK_RED : Hive.FRONT_BLUE;

        return null;
    }

}

package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.math.Pose;
import com.skeletonarmy.marrow.zones.Point;
import com.skeletonarmy.marrow.zones.PolygonZone;

import org.firstinspires.ftc.teamcode.fatamorgana.FataMain;
import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour;
import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class DriveSubsystem extends Subsystem {
    private static final DriveSubsystem drive = new DriveSubsystem();

    public static DriveSubsystem drive(){
        return drive;
    }

    public static Follower follower;
    private Pose currentPose;

    private final PolygonZone audienceLaunchZone = new PolygonZone(
            new Point(24, 48),
            new Point(24, 24),
            new Point(0,24),
            new Point(0,0),
            new Point(144,0),
            new Point(144-24, 24),
            new Point(144-24, 48),
            new Point(144, 0)
    );
    private final PolygonZone scoringLaunchZone = new PolygonZone(
            new Point(24, 144 - 48),
            new Point(24, 144 - 24),
            new Point(0,144 - 24),
            new Point(0,144),
            new Point(144,144),
            new Point(144-24, 144 - 24),
            new Point(144-24, 144 - 48),
            new Point(144, 144)
    );

    private final PolygonZone robotZone = new PolygonZone(10.3937, 14);

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
        robotZone.setPosition(x(), y());
        robotZone.setRotation(getHeading());
        FataMain.getTelemetry().addData("pose", currentPose);
        FataMain.getTelemetry().addData("hive Pose", yuri().getHiveTarget());
        FataMain.getTelemetry().addData("hive", getHive().name());
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

    public Hive getHive() {
        if (y() <= 72 ) return FataMain.getAllianceColour() ==
                AllianceColour.RED ? Hive.FRONT_RED : Hive.BACK_BLUE;

        if (y() > 72 ) return FataMain.getAllianceColour() ==
                AllianceColour.RED ? Hive.BACK_RED : Hive.FRONT_BLUE;

        return null;
    }

    public boolean canSeeHive(){
        return robotZone.isFullyInside(audienceLaunchZone) ||
                robotZone.isFullyInside(scoringLaunchZone);
    }
    public Pose getPassTarget() {
        return null; //TODO
    }

    public void setPose(Pose pose){
        follower.setPose(pose);
    }

    public void resetHeading(double heading){
        follower.setHeading(heading);
    }
}

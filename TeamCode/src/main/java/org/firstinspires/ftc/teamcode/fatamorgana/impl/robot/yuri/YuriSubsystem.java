package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;

import utility.actionbase.Action;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;
import static utility.actionbase.actions.Actions.simply;
import static utility.actionbase.actions.Actions.waitUntil;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretMode.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretSubsystem.turret;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose.STOP;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions.setHood;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriConstants.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.HoodPose.*;

import java.util.function.Supplier;

public class YuriSubsystem extends Subsystem {
    private MotorEx yuriMotorOne;
    private MotorEx yuriMotorTwo;
    private MotorGroup yuriMotors;
    private ServoEx hood;
    private ServoEx stopper;
    private double power = 0.0;
    private HoodPose hoodPose = HOOD_CLOSED;
    private StopperPose stopperPose = STOP;
    private YuriState yuriState = SEPARATE;
    private boolean shoot = true;

    private final static YuriSubsystem yuri = new YuriSubsystem();
    public static YuriSubsystem yuri() {
        return yuri;
    }

    @Override
    public void hardwareInit() {
        yuriMotorOne = getDcMotorEx("yuri");
        yuriMotorTwo = getDcMotorEx("yuriTwo");
        yuriMotors = getMotorGroup(yuriMotors, yuriMotorTwo);
        hood = getServo("hood", 10, 20);
        stopper = getServo("stopper");
        yuriMotorOne.setInverted(false);
        yuriMotorOne.setRunMode(Motor.RunMode.RawPower);
        yuriMotorOne.stopAndResetEncoder();
    }

    @Override
    public void opModeInit() {

    }

    @Override
    public void play() {

    }

    @Override
    public void loop() {
        if (shoot) {
            scoreCalc().schedule();
            hoodUpdate().schedule();
        }
        stopperUpdate().schedule();
    }

    @Override
    public void stop() {

    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setHoodPose(HoodPose hoodPose) {
        this.hoodPose = hoodPose;
    }

    public HoodPose getHoodPose() {
        return hoodPose;
    }

    public void setStopperPose(StopperPose stopperPose) {
        this.stopperPose = stopperPose;
    }

    public StopperPose getStopperPose() {
        return stopperPose;
    }

    private Action hoodUpdate() {
        return simply(() -> hood.set(hoodDebug == -1 ?
                hoodPose.pose.get() : hoodDebug));
    }

    private Action stopperUpdate() {
        return simply(() -> stopper.set(stopperDebug == -1 ?
                stopperPose.get() : stopperDebug));
    }

    private Action bang(Supplier<Double> target) {
        return simply(() -> {
            if (yuriMotors.getCurrentPosition() < target.get()) {
                yuriMotors.set(maxBangConstants);
            } else yuriMotors.set(-maxBangConstants);
        });
    }

    private Action pf(Supplier<Double> target) {
        return simply(() -> {
            double error = Math.abs(yuriMotors.getCurrentPosition() - target.get());
            yuriMotors.set(f * yuriMotors.getVelocity() *
                    (yuriMotors.getCurrentPosition() < target.get() ? 1 : -1)
                    + p * error);
        });
    }

    private Action bangBangController(Supplier<Double> ofeksMom) {
        return bang(ofeksMom).then(
                        waitUntil(() -> yuriMotors.getVelocity() == ofeksMom.get()))
                .then(pf(ofeksMom));
    }

    private Pose getShooterPose() {
        double theta = drive().getHeading();
        double x = drive().x() - shooterOffset * Math.sin(theta);
        double y = drive().y() + shooterOffset * Math.cos(theta);

        return new Pose(x, y, turret().getTurretAngle());
    }

    private double getTargetDist() {
        Pose hivePose = getHiveTarget();
        Pose passPose = getPassPose();
        Pose shooterPose = getShooterPose();
        boolean pass = turret().getTurretMode() == PASS;
        Pose targetPose = pass ? passPose : hivePose;

        return Math.sqrt(Math.pow(targetPose.x() - shooterPose.x(), 2) +
                Math.pow(targetPose.y() - shooterPose.y(), 2));
    }

    private Pose getPassPose() {
        return drive().getPassTarget();
    }

    private Pose getHiveTarget() {
        return drive().getHive().getTarget(drive().x());
    }

//    private double getHiveHeight() {
//        double hiveHeight;
//        double x = getHiveTarget().x();
//
//        InterpLUT heightLUT;
//        heightLUT = new InterpLUT();
//
//        heightLUT.add(0, 0); // TODO
//        heightLUT.add(1, 0);
//
//        hiveHeight = heightLUT.get(x);
//
//        return hiveHeight;
//    }

//    private double getHiveTrajectoryAngle() {
//        double trajectory;
//        double x = getHiveTarget().x();
//
//        InterpLUT trajectoryLUT;
//        trajectoryLUT = new InterpLUT();
//
//        trajectoryLUT.add(0, 0); // TODO
//        trajectoryLUT.add(1, 0);
//
//        trajectory = trajectoryLUT.get(x);
//
//        return trajectory;
//    }


    private double calcHoodAngle() {
        boolean pass = turret().getTurretMode() == PASS;
        double height = pass ? hiveHeight : passHeight;
        double heightDiff =  -shooterHeight;

        return Math.atan(2 * heightDiff / getTargetDist() -
                Math.tan(pass ? passLaunchAngle : scoreLaunchAngle)
        );
    }

    public double getCalcHoodAngle() {
        return calcHoodAngle();
    }

    private double calcScoreBallVelocity() {
        boolean pass = turret().getTurretMode() == PASS;
        double height = pass ? hiveHeight : passHeight;
        double heightDiff =  -shooterHeight;
        double hoodAngle = calcHoodAngle();

        return (Math.sqrt(g * getTargetDist() * getTargetDist() /
                (2 * Math.pow(Math.cos(hoodAngle), 2)) * getTargetDist() * (Math.tan(hoodAngle) - heightDiff)
        ));
    }

    private double ballToYuriVelocity(Supplier<Double> ballVelocity) {
        InterpLUT ballToYuriVel;
        ballToYuriVel = new InterpLUT();

        ballToYuriVel.add(0, 0);
        ballToYuriVel.add(1, 0);

        double clippedBallVelocity;
        clippedBallVelocity = Range.clip(minBallVelocity, maxBallVelocity, ballVelocity.get());

        return ballToYuriVel.get(clippedBallVelocity);
    }

    private Action scoreCalc() {
        return bangBangController(() -> ballToYuriVelocity(
                this::calcScoreBallVelocity)
        ).also(setHood(CALC));
    }

    public Action setYuriState(YuriState yuriState) {
        return simply(() -> this.yuriState = yuriState);
    }

    public YuriState getYuriState() {
        return yuriState;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isShooting() {
        return shoot;
    }
}

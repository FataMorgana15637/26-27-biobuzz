package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.fatamorgana.FataMain;
import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;

import utility.actionbase.Action;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;
import static utility.actionbase.actions.Actions.simply;
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
    private final InterpLUT distanceToMotorLUT = new InterpLUT();
    private Supplier<Double> yuriTarget = () -> 0.0;

    private final static YuriSubsystem yuri = new YuriSubsystem();

    public static YuriSubsystem yuri() {
        return yuri;
    }

    private YuriSubsystem() {
        distanceToMotorLUT.add(50 * cmToInches, 310);
        distanceToMotorLUT.add(100 * cmToInches, 390);
        distanceToMotorLUT.add(150 * cmToInches, 500);
        distanceToMotorLUT.add(200 * cmToInches, 580);
        distanceToMotorLUT.add(250 * cmToInches, 657);
        distanceToMotorLUT.add(300 * cmToInches, 780);

        distanceToMotorLUT.createLUT();
    }

    @Override
    public void hardwareInit() {
        yuriMotorOne = getDcMotorEx("yuri");
        yuriMotorTwo = getDcMotorEx("yuriTwo");
        yuriMotors = getMotorGroup(yuriMotorOne, yuriMotorTwo);
        hood = getServo("hood");
        stopper = getServo("stopper");
        yuriMotorOne.setInverted(true);
        yuriMotorOne.setRunMode(Motor.RunMode.RawPower);
        yuriMotorOne.stopAndResetEncoder();
        stopper.setInverted(true);
        hood.setInverted(false);
    }

    @Override
    public void opModeInit() {

    }

    @Override
    public void play() {

    }

    @Override
    public void loop() {
        scoreCalc().schedule();
        hoodUpdate().schedule();
        stopperUpdate().schedule();
        motorUpdate().schedule();
        telemetryUpdate().schedule();
    }

    @Override
    public void stop() {

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
                angleToHood(
                        Range.clip(
                                Math.toDegrees(hoodPose.pose.get()),
                                Math.toDegrees(hoodMinAngle),
                                Math.toDegrees(hoodMaxAngle)
                        )
                ) : hoodDebug
        ));
    }

    private double angleToHood(double angle) {
        double t = (angle - Math.toDegrees(hoodMinAngle)) /
                (Math.toDegrees(hoodMaxAngle) - Math.toDegrees(hoodMinAngle));
        return hoodMinTicks + t * (hoodMaxTicks - hoodMinTicks);
    }

    private Action stopperUpdate() {
        return simply(() -> stopper.set(stopperDebug == -1 ?
                stopperPose.get() : stopperDebug));
    }

    private Action bang(Supplier<Double> target) {
        return simply(() -> {
            double error = Math.abs(yuriMotors.getVelocity() - target.get());
            if (error > tolerance) {
                if (yuriMotors.getVelocity() < target.get()) {
                    yuriMotors.set(maxBangConstants);
                } else yuriMotors.set(-maxBangConstants);
            }
        });
    }

    private Action pf(Supplier<Double> target) {
        return simply(() -> {
            double error = target.get() - yuriMotors.getVelocity();
            yuriMotors.set(f * target.get()
                    + p * error);
        });
    }



    private Action bangBangController(Supplier<Double> ofeksMom) {
//        return bang(ofeksMom).then(
//                        waitUntil(() -> yuriMotors.getVelocity() == ofeksMom.get()))
        return pf(ofeksMom);
    }

    private Pose getShooterPose() {
        double theta = Math.toRadians(drive().getHeading());
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

    public Pose getHiveTarget() {
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
        double targetHeight = pass ? passHeight : hiveHeight;
        double heightDiff = targetHeight - shooterHeight;

        return /*Math.toDegrees*/(Math.atan(2 * heightDiff / getTargetDist() -
                Math.tan(pass ? passTrajectoryAngle : scoreTrajectoryAngle))
        );
    }

    public double getCalcHoodAngle() {
        return calcHoodAngle();
    }



    private double calcScoreBallVelocity() {
        boolean pass = turret().getTurretMode() == PASS;
        double targetHeight = pass ? passHeight : hiveHeight;
        double heightDiff = targetHeight - shooterHeight;
        double hoodAngle = calcHoodAngle();

        return (Math.sqrt((g * getTargetDist() * getTargetDist()) /
                (2 * Math.pow(Math.cos(hoodAngle), 2)
                * (getTargetDist() * Math.tan(hoodAngle) - heightDiff))
        ));
    }

    private double ballVelocityToDistance(double velocity) {
        return (velocity * Math.cos(hoodMinAngle) / g) *
                (velocity * Math.sin(hoodMinAngle) +
                        Math.sqrt(
                                Math.pow(velocity * Math.sin(hoodMinAngle), 2)
                                        + 2 * g * shooterHeight
                        ));
    }

    private double ballToYuriVelocity(double ballVelocity) {
        return distanceToMotorLUT.get(Range.clip(ballVelocityToDistance(ballVelocity), 50*cmToInches+0.01, 300*cmToInches-0.01));
    }

    private Action scoreCalc() {
        return setYuriTarget(
                () -> ballToYuriVelocity(calcScoreBallVelocity()))
                .also(setHood(CALC));
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

    public Supplier<Double> getYuriTarget() {
        return yuriTarget;
    }

    public Action setYuriTarget(Supplier<Double> yuriTarget) {
        return simply(() -> this.yuriTarget = yuriTarget);
    }

    private Action motorUpdate() {
        return power == 0 ?
                bangBangController(yuriTarget) :
                simply(() -> yuriMotors.set(power));
    }

    public double getYuriVelocity() {
        return yuriMotors.getVelocity();
    }

    private Action telemetryUpdate() {
        return simply(() -> {
            FataMain.getTelemetry().addData("target", yuriTarget.get());
            FataMain.getTelemetry().addData("currentVelocity", getYuriVelocity());
            FataMain.getTelemetry().addData("stopper", stopper.get());
            FataMain.getTelemetry().addData("hood", hood.get());
            FataMain.getTelemetry().addData("ballVelocity", calcScoreBallVelocity());
            FataMain.getTelemetry().addData("yuriVElocity", ballToYuriVelocity(calcScoreBallVelocity()));
            FataMain.getTelemetry().addData("targetDist", getTargetDist());
            FataMain.getTelemetry().addData("natural hood angle", calcHoodAngle());
            FataMain.getTelemetry().addData("angle hood", Math.toDegrees(calcHoodAngle()));
            FataMain.getTelemetry().addData("turret mode", turret().getTurretMode());
        });
    }

    public double getPower() {
        return power;
    }

    public Action setPower(double power) {
        return simply(() -> this.power = power);
    }
}

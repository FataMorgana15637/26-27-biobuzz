package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import utility.actionBase.Action;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive.DriveSubsystem.drive;
import static utility.actionBase.actions.Actions.simply;
import static utility.actionBase.actions.Actions.waitUntil;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret.TurretSubsystem.turret;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions.YuriActions.setHood;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriConstents.*;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.HoodPose.*;

import java.util.function.Supplier;

public class YuriSubsystem extends Subsystem {
    private MotorEx  yuriMotor;
    private ServoEx hood;
    private double power = 0.0;
    private static final YuriSubsystem yuri = new YuriSubsystem();
    private HoodPose hoodPose = HOOD_CLOSED;
    public static YuriSubsystem yuri() {
    return yuri;
    }

    @Override
    public void hardwareInit(){
        yuriMotor = getDcMotorEx("yuri");
        hood = getServo("hood");
        yuriMotor.setInverted(false);
        yuriMotor.setRunMode(Motor.RunMode.RawPower);
        yuriMotor.stopAndResetEncoder();
    }

    @Override
    public void opModeInit(){}

    @Override
    public void play(){}

    @Override
    public void loop(){
        scoreCalc().schedule();
    }

    @Override
    public void stop(){}

    public void setPower(double power){
        this.power = power;
    }
    public void setHoodPose(HoodPose hoodPose){
        this.hoodPose = hoodPose;
    }

    public HoodPose getHoodPose(){
        return hoodPose;
    }

    private Action hoodUpdate() {
        return simply(() -> {
            hood.set(hoodDebug == -1 ? hoodPose.pose.get() : hoodDebug);
        });
    }

    private Action bang(Supplier<Double> target) {
        return simply(() -> {
        if (yuriMotor.getCurrentPosition() < target.get()) {
            yuriMotor.set(maxBangConstents);
        } else yuriMotor.set(-maxBangConstents);
        });
    }

    private Action pf(Supplier<Double> target) {
        return simply(() -> {
        double error = Math.abs(yuriMotor.getCurrentPosition() - target.get());
        yuriMotor.set(f * yuriMotor.getVelocity() *
                (yuriMotor.getCurrentPosition() < target.get() ? 1 : -1)
                + p * error);
        });
    }

    private Action bangBangController(Supplier<Double> ofeksMom) {
        return bang(ofeksMom).then(
                waitUntil(() -> yuriMotor.getVelocity() == ofeksMom.get()))
                .then(pf(ofeksMom));
    }

    private Pose getShooterPose() {
        double theta = drive().getHeading();
        double x = drive().x() - shooterOffset * Math.sin(theta);
        double y = drive().y() + shooterOffset * Math.cos(theta);
        return new Pose(x, y, turret().getTurretAngle());
    }

    private double getHiveDist() {
        Pose hivePose = getHiveTarget();
        Pose shooterPose = getShooterPose();
        return Math.sqrt(Math.pow(hivePose.x() - shooterPose.x(), 2) + Math.pow(hivePose.y() - shooterPose.x(), 2));
    }

    private Pose getHiveTarget() {
        return drive().getHive().getTarget(drive().x());
    }

    private double getHiveHight() {
        double hiveHight;
        double x = getHiveTarget().x();

        InterpLUT hightLUT;
        hightLUT = new InterpLUT();

        hightLUT.add(0,0); // TODO

        hiveHight = hightLUT.get(x);
        return hiveHight;
    }

    private double getHiveTrajectoryAngle(){
        double trajectory;
        double x = getHiveTarget().x();

        InterpLUT trajectoryLUT;
        trajectoryLUT = new InterpLUT();

        trajectoryLUT.add(0,0); // TODO

        trajectory = trajectoryLUT.get(x);
        return trajectory;
    }


    private Supplier<Double> calcScoreHoodAngle(){
        double hightDiff = getHiveHight() - shooterHight;
        return () -> Math.atan(
                2 * hightDiff/
                        getHiveDist() - Math.tan(getHiveTrajectoryAngle())
        );
    }
//    private Supplier<Double> calcPassHoodAngle(){
//        double hightDiff = passHightDiff;
//        return () -> Math.atan(
//                2 * hightDiff/
//                        getHiveDist() - Math.tan(getHiveTrajectoryAngle())
//        );
//    }
    public Supplier<Double> getCalcHoodAngle(){
        return calcScoreHoodAngle();
    }

    private Supplier<Double> calcScoreBallVelocity(){
        double highDiff = getHiveHight() - shooterHight;
        double hoodAngle = calcScoreHoodAngle().get();
        return () ->(Math.sqrt(g * getHiveDist() * getHiveDist() /
                (2*Math.pow(Math.cos(hoodAngle), 2)) * getHiveDist() * (Math.tan(hoodAngle) - highDiff)
        ));
    }

    private Supplier<Double> ballToYuriVelocity(Supplier<Double> ballVelocity){
        InterpLUT ballToYuriVel;
        ballToYuriVel = new InterpLUT();

        ballToYuriVel.add(0,0);

        double clippedBallVelocity;
        clippedBallVelocity = Range.clip(minBallVelocity, maxBallVelocity, ballVelocity.get());
        return () -> ballToYuriVel.get(clippedBallVelocity);
    }

    private Action scoreCalc() {
            return bangBangController(
                    ballToYuriVelocity(calcScoreBallVelocity())
            ).also(
                    setHood(CALC));
    }


}

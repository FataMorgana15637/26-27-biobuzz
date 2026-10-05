package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;

import utility.actionbase.Action;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;
import static utility.actionbase.actions.Actions.simply;
import static utility.actionbase.actions.Actions.waitUntil;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretSubsystem.turret;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions.setHood;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriConstants.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.HoodPose.*;

import java.util.function.Supplier;

public class YuriSubsystem extends Subsystem {
    private MotorEx yuriMotor;
    private ServoEx hood;
    private double power = 0.0;
    private HoodPose hoodPose = HOOD_CLOSED;
    private YuriState yuriState = SEPARATE;

    private final static YuriSubsystem yuri = new YuriSubsystem();
    public static YuriSubsystem yuri() {
        return yuri;
    }

    @Override
    public void hardwareInit() {
        yuriMotor = getDcMotorEx("yuri");
        hood = getServo("hood", 10, 20);
        yuriMotor.setInverted(false);
        yuriMotor.setRunMode(Motor.RunMode.RawPower);
        yuriMotor.stopAndResetEncoder();
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
//        hoodUpdate().schedule();
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

    private Action hoodUpdate() {
        return simply(() -> hood.set(hoodDebug == -1 ?
                hoodPose.pose.get() : hoodDebug));
    }

    private Action bang(Supplier<Double> target) {
        return simply(() -> {
            if (yuriMotor.getCurrentPosition() < target.get()) {
                yuriMotor.set(maxBangConstants);
            } else yuriMotor.set(-maxBangConstants);
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

        return Math.sqrt(Math.pow(hivePose.x() - shooterPose.x(), 2) +
                Math.pow(hivePose.y() - shooterPose.x(), 2));
    }

    private Pose getHiveTarget() {
        return drive().getHive().getTarget(drive().x());
    }

    private double getHiveHeight() {
        double hiveHeight;
        double x = getHiveTarget().x();

        InterpLUT heightLUT;
        heightLUT = new InterpLUT();

        heightLUT.add(0, 0); // TODO
        heightLUT.add(1, 0);

        hiveHeight = heightLUT.get(x);

        return hiveHeight;
    }

    private double getHiveTrajectoryAngle() {
        double trajectory;
        double x = getHiveTarget().x();

        InterpLUT trajectoryLUT;
        trajectoryLUT = new InterpLUT();

        trajectoryLUT.add(0, 0); // TODO
        trajectoryLUT.add(1, 0);

        trajectory = trajectoryLUT.get(x);

        return trajectory;
    }


    private double calcScoreHoodAngle() {
        double heightDiff = getHiveHeight() - shooterHight;

        return Math.atan(2 * heightDiff / getHiveDist() -
                Math.tan(getHiveTrajectoryAngle())
        );
    }

    public double getCalcHoodAngle() {
        return calcScoreHoodAngle();
    }

    private double calcScoreBallVelocity() {
        double highDiff = getHiveHeight() - shooterHight;
        double hoodAngle = calcScoreHoodAngle();

        return (Math.sqrt(g * getHiveDist() * getHiveDist() /
                (2 * Math.pow(Math.cos(hoodAngle), 2)) * getHiveDist() * (Math.tan(hoodAngle) - highDiff)
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

    public void setYuriState(YuriState yuriState) {
        this.yuriState = yuriState;
    }

    public YuriState getYuriState() {
        return yuriState;
    }
}

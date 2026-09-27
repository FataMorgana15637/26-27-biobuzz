package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive.DriveSubsystem.drive;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.YuriNectarConstents.f;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.YuriNectarConstents.maxBangConstents;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.YuriNectarConstents.p;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.YuriNectarConstents.shooterOffset;
import static utility.actionBase.actions.Actions.simply;
import static utility.actionBase.actions.Actions.waitUntil;

import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class YuriNectarSubsystem extends Subsystem {
    private MotorEx yuriMotor;
    private ServoEx hood;
    private Supplier<Double> targetVelocity =()-> 0.0;
    private Supplier<Double> hoodTarget = () -> 0.0;
    private static final YuriNectarSubsystem yuriNectar = new YuriNectarSubsystem();
    private NectarHoodPose hoodPose = NectarHoodPose.HOOD_CLOSED.HOOD_CLOSED;


    public static YuriNectarSubsystem yuri() {
        return yuriNectar;
    }

    @Override
    public void hardwareInit() {
        yuriMotor = getDcMotorEx("yuriPollen");
        yuriMotor.setInverted(false);
        yuriMotor.setRunMode(Motor.RunMode.RawPower);
        yuriMotor.stopAndResetEncoder();
        hood = getServo("hoodPollen");
    }

    @Override
    public void opModeInit() {
    }

    @Override
    public void play() {
    }

    @Override
    public void loop() {
        hoodUpdate().schedule();
        yuriUpdate().schedule();
    }

    @Override
    public void stop() {
    }

    public void setTargetVelocity(Supplier <Double> power) {
        this.targetVelocity = power;
    }

    public double getTargetVelocity() {
        return targetVelocity.get();
    }

    private Action bang(double target, MotorEx motor) {
        return simply(() -> {
            if (motor.getCurrentPosition() < target) {
                motor.set(maxBangConstents);
            } else motor.set(-maxBangConstents);
        });
    }

    private Action pf(double target, MotorEx motor) {
        return simply(() -> {
            double error = Math.abs(motor.getCurrentPosition() - target);
            motor.set(f * motor.getVelocity() *
                    (motor.getCurrentPosition() < target ? 1 : -1)
                    + p * error);
        });
    }

    public Action bangBangController(double ofeksMom, MotorEx motor) {
        return bang(ofeksMom, motor).then(
                        waitUntil(() -> motor.getVelocity() == ofeksMom))
                .then(pf(ofeksMom, motor));
    }

    private Pose getShooterPose(){
        double theta = drive().getHeading();
        double x = drive().getX() - shooterOffset * Math.sin(theta);
        double y = drive().getY() + shooterOffset * Math.cos(theta);
        return new Pose(x, y, drive().getHeading());
    }

    public void setHoodTarget(Supplier<Double> target){
        hoodTarget = target;
    }

    public Supplier<Double> getHoodTarget() {
        return hoodTarget;
    }

    private Action hoodUpdate(){
        return simply(() ->
                hood.set(hoodTarget.get())
        );
    }

    private Action yuriUpdate() {
        return simply(() ->
                yuriMotor.set(targetVelocity.get())
        );
    }
}


















































// אני טורו מפוצץ בדינרו
 // מחולל ניסים כמו סבא גפטו
 //בוא תראה מה קורה פה

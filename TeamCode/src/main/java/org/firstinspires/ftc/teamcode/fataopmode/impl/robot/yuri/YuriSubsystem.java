package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri;

import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;
import utility.actionBase.Action;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive.DriveSubsystem.drive;
import static utility.actionBase.actions.Actions.simply;
import static utility.actionBase.actions.Actions.waitUntil;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret.TurretSubsystem.turret;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriConstents.*;

public class YuriSubsystem extends Subsystem {
    private MotorEx  yuriMotor;
    private ServoEx hood;
    private double power = 0.0;
    private static final YuriSubsystem yuri = new YuriSubsystem();
    private HoodPose hoodPose = HoodPose.HOOD_CLOSED;

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
    public void loop(){}

    @Override
    public void stop(){}

    private Action hoodUpdate() {
        return null;
//    return simply(() ->);
    }
    public void setPower(double power){
        this.power = power;
    }

    private Action bang(double target){
        return simply(() -> {
        if (yuriMotor.getCurrentPosition() < target) {
            yuriMotor.set(maxBangConstents);
        } else yuriMotor.set(-maxBangConstents);
        });
    }

    private Action pf(double target){
        return simply(() -> {
        double error = Math.abs(yuriMotor.getCurrentPosition() - target);
        yuriMotor.set(f * yuriMotor.getVelocity() *
                (yuriMotor.getCurrentPosition() < target ? 1 : -1)
                + p * error);
        });
    }

    public Action bangBangController(double ofeksMom) {
        return bang(ofeksMom).then(
                waitUntil(() -> yuriMotor.getVelocity() == ofeksMom))
                .then(pf(ofeksMom));
    }

    private Pose getShooterPose(){
        double theta = drive().getHeading();
        double x = drive().getX() - shooterOffset * Math.sin(theta);
        double y = drive().getY() + shooterOffset * Math.cos(theta);
        return new Pose(x, y, turret().getTurretAngle());
    }


}

package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri;

import android.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import org.firstinspires.ftc.teamcode.fataopmode.FataMain;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;
import utility.actionBase.Action;
import utility.actionBase.actions.WaitUntilAction;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriConstents.hoodDebug;
import static utility.actionBase.actions.Actions.simply;
import static utility.actionBase.actions.Actions.waitUntil;

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
            yuriMotor.set(YuriConstents.maxBangConstents);
        } else yuriMotor.set(-YuriConstents.maxBangConstents);
        });
    }

    private Action pf(double target){
        return simply(() -> {
        double error = Math.abs(yuriMotor.getVelocity() - target);
        yuriMotor.set(YuriConstents.f * yuriMotor.getVelocity() *
                (yuriMotor.getCurrentPosition() < target ? 1 : -1)
                + YuriConstents.p * error);
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
        return new Pose(x, y, drive().getHeading()); // fix heading TODO
    }


}

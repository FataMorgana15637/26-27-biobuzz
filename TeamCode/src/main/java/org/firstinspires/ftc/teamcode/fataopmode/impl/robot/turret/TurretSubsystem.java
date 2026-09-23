package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive.DriveSubsystem.drive;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret.TurretMode.PASS;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret.TurretMode.SCORE;

import static utility.actionBase.actions.Actions.simply;

import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class TurretSubsystem extends Subsystem {
    private static final TurretSubsystem turret = new TurretSubsystem();

    private ServoEx servoOne;
    private ServoEx servoTwo;

    private TurretMode turretMode = SCORE;
    private ServoExGroup turretServos;

    private Supplier<Double> target = () -> 0.0;

    public static TurretSubsystem turret(){
        return turret;
    }

    @Override
    public void hardwareInit() {
        servoOne = getServo("servo one", 0.0, 360.0);
        servoTwo = getServo("two", 0.0, 360.0);
        turretServos = getServoGroup(servoOne, servoTwo);
    }

    @Override
    public void opModeInit() {

    }

    @Override
    public void play() {

    }

    @Override
    public void loop() {
        if (turretMode == SCORE) {
            aimToGoal().schedule();
        }

        if (turretMode == PASS) {
            pass().schedule();
        }
    }

    @Override
    public void stop() {

    }

    public double getTurretAngle(){
        return turretServos.get();
    }

    private double getDegreesTo(Pose target){
         double angle = Math.toDegrees(
                 Math.atan2(
                         target.y() - drive().y(),
                         target.x() - drive().x()
                 ) - Math.toRadians(drive().getHeading())
         );

         if(angle > 180) angle -= 360;
         if (angle < 180) angle += 360;

         angle = (angle + 180) % 360 -180;

        return angle;
    }
    public void setTarget(Supplier<Double> target){
        this.target = target;
    }
    public double getTarget(){
        return target.get();
    }

    private Action aimToGoal(){
        return simply(() -> {
            setTarget(() ->
                    turret().getDegreesTo(
                            drive().getHive()
                                    .getTarget(drive().x())
                    )
            );
        });
    }

    private Action pass(){
        return simply(() -> {

        });
    }

    public void SetTurretMode(TurretMode turretMode) {
        this.turretMode = turretMode;
    }


}
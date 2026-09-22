package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive.DriveSubsystem.drive;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import java.util.function.Supplier;

public class TurretSubsystem extends Subsystem {
    private static final TurretSubsystem turret = new TurretSubsystem();

    private ServoEx servoOne;
    private ServoEx servoTwo;
    private ServoExGroup turretServos;

    private Supplier<Double> target = () -> 0.0;

    public static TurretSubsystem turret(){
        return turret;
    }

    @Override
    public void hardwareInit() {
        servoOne = getServo("servo one", 0.0, 360.0);
        servoTwo = getServo("two", 0.0, 360);
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
                         target.y() - drive().getY(),
                         target.x() - drive().getX()
                 )
         );

        if (angle < 0)
            angle += 360;

        return angle;
    }

    public void setTarget(Supplier<Double> target){
        this.target = target;
    }

    public double getTarget(){
        return target.get();
    }

}

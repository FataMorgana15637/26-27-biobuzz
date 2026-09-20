package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.turret;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

public class TurretSubsystem extends Subsystem {
    private static final TurretSubsystem turret = new TurretSubsystem();

    private ServoEx servoOne;
    private ServoEx servoTwo;
    private ServoExGroup turretServos;

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
}

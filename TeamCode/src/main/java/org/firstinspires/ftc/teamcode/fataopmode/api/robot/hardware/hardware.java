package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.seattlesolvers.solverslib.hardware.SensorColor;
import com.seattlesolvers.solverslib.hardware.SensorRevColorV3;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.fataopmode.FataMain;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy.DecoyDigitalChannel;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy.DecoyLimelight3A;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy.DecoyNormalizedColourSensor;

public interface hardware {

    default boolean isHardwareEnabled() {
        return true;
    }

    default MotorEx getDcMotorEx(String name) {
        return getDcMotorEx(name, isHardwareEnabled(), Motor.GoBILDA.RPM_435);
    }

    default MotorEx getDcMotorEx(String name, boolean enabled){
         return getDcMotorEx(name, enabled, Motor.GoBILDA.RPM_435);
    }

    default MotorEx getDcMotorEx(String name, boolean enabled, Motor.GoBILDA motorType) {
        MotorEx motor = new MotorEx(FataMain.getCurrentOpMode().hardwareMap, name, motorType);
        if (!enabled){
            motor.disable();
        }
        return motor;
    }

    default ServoEx getServo(String name) {
        return getServo(name, isHardwareEnabled());
    }

    default ServoEx getServo(String name, boolean enabled) {
        ServoEx servoEx = new ServoEx(FataMain.getCurrentOpMode().hardwareMap, name);
        if (!enabled){
            servoEx.disable();
        }
        return servoEx;
    }

    default ServoEx getServo(String name, boolean enabled, double min, double max){
        ServoEx servoEx = new ServoEx(FataMain.getCurrentOpMode().hardwareMap, name, min, max);
        if (!enabled){
            servoEx.disable();
        }
        return servoEx;
    }

    default CRServo getCRServo(String name) {
        return getCRServo(name,isHardwareEnabled());
    }

    default CRServo getCRServo(String name, boolean enabled) {
        CRServo crServo = new CRServo(FataMain.getCurrentOpMode().hardwareMap, name);
        if (!enabled){
            crServo.disable();
        }
        return crServo;
    }

//    default CRServoEx geCRServoEX(String name, boolean enabled, String encodreID, )

    default DigitalChannel getDigitalChannel(String name) {
        return getDigitalChannel(name,isHardwareEnabled());
    }

    default DigitalChannel getDigitalChannel(String name, boolean enabled) {
        return enabled ? FataMain.getCurrentOpMode()
                .hardwareMap.digitalChannel.get(name) : new DecoyDigitalChannel();
    }

    default Limelight3A getLimelight() {
        return getLimelight(isHardwareEnabled());
    }

    default Limelight3A getLimelight(boolean enabled) {
        return enabled ? FataMain.getCurrentOpMode()
                .hardwareMap.get(Limelight3A.class, "lamlam") : DecoyLimelight3A.create();
    }

    default NormalizedColorSensor getNormalizedColorSensor(String name) {
        return getNormalizedColorSensor(name, isHardwareEnabled());
    }

    default NormalizedColorSensor getNormalizedColorSensor(String name, boolean enabled) {
        return enabled ? (NormalizedColorSensor) FataMain.getCurrentOpMode()
                .hardwareMap.colorSensor.get(name) : new DecoyNormalizedColourSensor() {
        };
    }

    default SensorRevColorV3 getRevColourSensor(String name) {
        return getRevColourSensor(name, isHardwareEnabled(), DistanceUnit.MM);
    }
    default SensorRevColorV3 getRevColourSensor(String name, boolean enabled, DistanceUnit distanceUnit) {
        SensorRevColorV3 colorV3 = new SensorRevColorV3(FataMain.getCurrentOpMode().hardwareMap, name, distanceUnit);
        if(!enabled) {
            colorV3.disable();
        }
        return colorV3;
    }

    default SensorColor getColourSensor(String name) {
        return getColourSensor(name, isHardwareEnabled());
    }
    default SensorColor getColourSensor(String name, boolean enabled) {
        SensorColor colorV3 = new SensorColor(FataMain.getCurrentOpMode().hardwareMap, name);
        if(!enabled){
            colorV3.disable();
        }
        return colorV3;
    }

}

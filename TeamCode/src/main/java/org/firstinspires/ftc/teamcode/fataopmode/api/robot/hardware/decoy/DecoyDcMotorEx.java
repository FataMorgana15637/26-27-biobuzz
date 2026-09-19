package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class DecoyDcMotorEx implements DcMotorEx {
    @Override
    public void setMotorEnable() {}

    @Override
    public void setMotorDisable() {}

    @Override
    public boolean isMotorEnabled() { return false; }

    @Override
    public void setVelocity(double v) {}

    @Override
    public void setVelocity(double v, AngleUnit angleUnit) {}

    @Override
    public double getVelocity() { return 0; }

    @Override
    public double getVelocity(AngleUnit angleUnit) { return 0; }

    @Override
    public void setPIDCoefficients(RunMode runMode, PIDCoefficients pidCoefficients) {}

    @Override
    public void setPIDFCoefficients(RunMode runMode, PIDFCoefficients pidfCoefficients) {}

    @Override
    public void setVelocityPIDFCoefficients(double v, double v1, double v2, double v3) {}

    @Override
    public void setPositionPIDFCoefficients(double v) {}

    @Override
    public PIDCoefficients getPIDCoefficients(RunMode runMode) { return null; }

    @Override
    public PIDFCoefficients getPIDFCoefficients(RunMode runMode) { return null; }

    @Override
    public void setTargetPositionTolerance(int i) {}

    @Override
    public int getTargetPositionTolerance() { return 0; }

    @Override
    public double getCurrent(CurrentUnit currentUnit) { return 0; }

    @Override
    public double getCurrentAlert(CurrentUnit currentUnit) { return 0; }

    @Override
    public void setCurrentAlert(double v, CurrentUnit currentUnit) {}

    @Override
    public boolean isOverCurrent() { return false; }

    @Override
    public MotorConfigurationType getMotorType() { return new MotorConfigurationType(); }

    @Override
    public void setMotorType(MotorConfigurationType motorConfigurationType) {}

    @Override
    public DcMotorController getController() { return null; }

    @Override
    public int getPortNumber() { return 0; }

    @Override
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {}

    @Override
    public ZeroPowerBehavior getZeroPowerBehavior() { return null; }

    @Override
    public void setPowerFloat() {}

    @Override
    public boolean getPowerFloat() { return false; }

    @Override
    public void setTargetPosition(int i) {}

    @Override
    public int getTargetPosition() { return 0; }

    @Override
    public boolean isBusy() { return false; }

    @Override
    public int getCurrentPosition() { return 0; }

    @Override
    public void setMode(RunMode runMode) {}

    @Override
    public RunMode getMode() { return null; }

    @Override
    public void setDirection(Direction direction) {}

    @Override
    public Direction getDirection() { return null; }

    @Override
    public void setPower(double v) {}

    @Override
    public double getPower() { return 0; }

    @Override
    public Manufacturer getManufacturer() { return null; }

    @Override
    public String getDeviceName() { return ""; }

    @Override
    public String getConnectionInfo() { return ""; }

    @Override
    public int getVersion() { return 0; }

    @Override
    public void resetDeviceConfigurationForOpMode() {}

    @Override
    public void close() {}
}

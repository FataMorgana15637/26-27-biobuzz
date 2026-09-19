package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class DecoyServo implements Servo {
    @Override
    public ServoController getController() { return null; }

    @Override
    public int getPortNumber() { return 0; }

    @Override
    public void setDirection(Direction direction) {}

    @Override
    public Direction getDirection() { return null; }

    @Override
    public void setPosition(double v) {}

    @Override
    public double getPosition() { return 0; }

    @Override
    public void scaleRange(double v, double v1) {}

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

package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoController;

public class DecoyCRServo implements CRServo {
    @Override
    public ServoController getController() { return null; }

    @Override
    public int getPortNumber() { return 0; }

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

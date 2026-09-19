package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

public class DecoyDigitalChannel implements DigitalChannel {
    @Override
    public Mode getMode() { return null; }

    @Override
    public void setMode(Mode mode) {}

    @Override
    public boolean getState() { return false; }

    @Override
    public void setState(boolean b) {}

    @Override
    public void setMode(DigitalChannelController.Mode mode) {}

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

package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class DecoyNormalizedColourSensor implements NormalizedColorSensor {
    @Override
    public NormalizedRGBA getNormalizedColors() {
        return null;
    }

    @Override
    public float getGain() {
        return 0;
    }

    @Override
    public void setGain(float v) {

    }

    @Override
    public Manufacturer getManufacturer() {
        return null;
    }

    @Override
    public String getDeviceName() {
        return "";
    }

    @Override
    public String getConnectionInfo() {
        return "";
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {

    }

    @Override
    public void close() {

    }
}

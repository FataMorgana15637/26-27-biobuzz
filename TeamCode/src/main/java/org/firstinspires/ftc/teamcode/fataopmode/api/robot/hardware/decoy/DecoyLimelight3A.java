package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import com.qualcomm.hardware.limelightvision.*;
import com.qualcomm.robotcore.util.SerialNumber;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DecoyLimelight3A extends Limelight3A {
    private DecoyLimelight3A() throws UnknownHostException {
        super(SerialNumber.createFake(), "DecoyLimelight3A", InetAddress.getLocalHost());
    }

    public static Limelight3A create() {
        try {
            return new DecoyLimelight3A();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void start() {}

    @Override
    public synchronized void pause() {}

    @Override
    public synchronized void stop() {}

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public synchronized void setPollRateHz(int rateHz) {}

    @Override
    public long getTimeSinceLastUpdate() {
        return Long.MAX_VALUE;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public LLResult getLatestResult() {
        return null;
    }

    @Override
    public LLStatus getStatus() {
        return new LLStatus();
    }

    @Override
    public boolean reloadPipeline() {
        return false;
    }

    @Override
    public boolean pipelineSwitch(int index) {
        return false;
    }

    @Override
    public boolean captureSnapshot(String snapname) {
        return false;
    }

    @Override
    public boolean deleteSnapshots() {
        return false;
    }

    @Override
    public boolean deleteSnapshot(String snapname) {
        return false;
    }

    @Override
    public boolean updatePythonInputs(double[] inputs) {
        return false;
    }

    @Override
    public boolean updateRobotOrientation(double yaw) {
        return false;
    }

    @Override
    public boolean uploadPipeline(String jsonString, Integer index) {
        return false;
    }

    @Override
    public boolean uploadFieldmap(LLFieldMap fieldmap, Integer index) {
        return false;
    }

    @Override
    public boolean uploadPython(String pythonString, Integer index) {
        return false;
    }

    @Override
    public LLResultTypes.CalibrationResult getCalDefault() {
        return new LLResultTypes.CalibrationResult();
    }

    @Override
    public LLResultTypes.CalibrationResult getCalFile() {
        return new LLResultTypes.CalibrationResult();
    }

    @Override
    public LLResultTypes.CalibrationResult getCalEEPROM() {
        return new LLResultTypes.CalibrationResult();
    }

    @Override
    public LLResultTypes.CalibrationResult getCalLatest() {
        return new LLResultTypes.CalibrationResult();
    }


}

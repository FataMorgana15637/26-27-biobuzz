package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.decoy;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.qualcomm.robotcore.util.SerialNumber;
import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraCharacteristics;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.jetbrains.annotations.NotNull;

public class DecoyWebcamName implements WebcamName {
    @NonNull
    @NotNull
    @Override
    public SerialNumber getSerialNumber() {
        return SerialNumber.createFake();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public String getUsbDeviceNameIfAttached() { return ""; }

    @Override
    public boolean isAttached() { return false; }

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

    @Override
    public boolean isWebcam() { return false; }

    @Override
    public boolean isCameraDirection() { return false; }

    @Override
    public boolean isSwitchable() { return false; }

    @Override
    public boolean isUnknown() { return false; }

    @Override
    public void asyncRequestCameraPermission(Context context, Deadline deadline, Continuation<? extends Consumer<Boolean>> continuation) {}

    @Override
    public boolean requestCameraPermission(Deadline deadline) { return false; }

    @Override
    public CameraCharacteristics getCameraCharacteristics() { return null; }
}

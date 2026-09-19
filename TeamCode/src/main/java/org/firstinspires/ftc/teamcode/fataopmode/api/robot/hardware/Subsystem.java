package org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware;

public abstract class Subsystem implements hardware {
    protected boolean enabled = true;

    @Override
    public boolean isHardwareEnabled() {
        return enabled;
    }

    public abstract void hardwareInit();

    public abstract void opModeInit();

    public abstract void play();

    public abstract void loop();

    public abstract void stop();

    public boolean isEnabled(){
        return enabled;
    };

    public Subsystem disable(){
        enabled = false;
        return this;
    }

}

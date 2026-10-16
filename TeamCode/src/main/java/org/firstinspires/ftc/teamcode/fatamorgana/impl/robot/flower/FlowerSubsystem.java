package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.flower;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.flower.FlowerConstants.flowerDebug;
import static utility.actionbase.actions.Actions.simply;

import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;

import utility.actionbase.Action;

public class FlowerSubsystem extends Subsystem {

    private ServoEx flowerServo;
    private FlowerPose flowerPose = FlowerPose.UP;
    private static final FlowerSubsystem flower = new FlowerSubsystem();

    public static FlowerSubsystem flower() {
        return flower;
    }

    @Override
    public void hardwareInit() {
        flowerServo = getServo("flower");
    }

    @Override
    public void opModeInit() {
    }

    @Override
    public void play() {
    }

    @Override
    public void loop() {
        FlowerUpdate().schedule();
    }

    @Override
    public void stop() {
    }

    private Action FlowerUpdate() {
        return simply(() -> flowerServo.set(flowerDebug == -1 ?
        flowerPose.pose.get() : flowerDebug));
    }

    public Action setTarget(FlowerPose flowerPose) {
        return simply(() -> this.flowerPose = flowerPose);
    }

    public FlowerPose getFlowerPose() {
        return flowerPose;
    }
}

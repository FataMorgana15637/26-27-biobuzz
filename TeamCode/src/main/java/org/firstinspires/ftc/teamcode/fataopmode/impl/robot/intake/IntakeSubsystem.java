package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake;

import static utility.actionBase.actions.Actions.simply;

import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class IntakeSubsystem extends Subsystem {
    private MotorEx intakeMotorTop;
    private MotorEx intakeMotorBottom;
    private MotorGroup intakeMotors;
    private MotorEx transferMotor;
    private Supplier<Double> intakePower = () -> 0.0;
    private Supplier<Double> transferPower = () -> 0.0;
    private static final IntakeSubsystem intake = new IntakeSubsystem();

    public static IntakeSubsystem intake() {
        return intake;
    }

    @Override
    public void hardwareInit() {
        intakeMotorTop = getDcMotorEx("firstIntake");
        intakeMotorBottom = getDcMotorEx("secIntake");
        intakeMotors = getMotorGroup(intakeMotorTop, intakeMotorBottom);

        transferMotor = getDcMotorEx("transfer");

        intakeMotorTop.setInverted(true);
        intakeMotorBottom.setInverted(false);
    }

    @Override
    public void opModeInit() {

    }

    @Override
    public void play() {

    }

    @Override
    public void loop() {
        intakeUpdate().schedule();
        transferUpdate().schedule();
    }

    @Override
    public void stop() {

    }

    public void setIntakePower(Supplier<Double> power) {
        intakePower = power;
    }

    public void setTransferPower(Supplier<Double> power) {
        transferPower = power;
    }

    public double getIntakePower() {
        return intakePower.get();
    }

    public double getTransferPower() {
        return transferPower.get();
    }

    private Action intakeUpdate(){
        return simply(() -> intakeMotors.set(getIntakePower()));
    }

    private Action transferUpdate(){
        return simply(() -> transferMotor.set(getTransferPower()));
    }
}

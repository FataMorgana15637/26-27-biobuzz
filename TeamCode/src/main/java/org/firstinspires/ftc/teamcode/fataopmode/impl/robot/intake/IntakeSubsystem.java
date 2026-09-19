package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake;

import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

public class IntakeSubsystem extends Subsystem {
    private MotorEx intakeMotor;
    private MotorEx intakeSecMotor;
//    private MotorEx transferMotor;
    private double power = 0.0;
    private static final IntakeSubsystem intake = new IntakeSubsystem();

    public static IntakeSubsystem intake(){
        return intake;
    }

    @Override
    public void hardwareInit() {
        intakeMotor = getDcMotorEx("firstIntake");
        intakeSecMotor = getDcMotorEx("secIntake");
//        transferMotor = getDcMotorEx("transfer");

        intakeMotor.setInverted(true);
        intakeSecMotor.setInverted(true);
    }

    @Override
    public void opModeInit() {

    }

    @Override
    public void play() {

    }

    @Override
    public void loop() {
        intakeMotor.set(power);
        intakeSecMotor.set(power);
//        transferMotor.set(power);
    }

    @Override
    public void stop() {

    }

    public void setPower(double power){
        intakeMotor.set(power);
        intakeSecMotor.set(power);
    }
}

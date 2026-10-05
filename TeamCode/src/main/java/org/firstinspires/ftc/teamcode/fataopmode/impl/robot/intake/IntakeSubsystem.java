package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.servoDebug;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.transferSpeed;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeState.*;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriConstents.hoodDebug;
import static utility.actionBase.actions.Actions.simply;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import java.util.function.Supplier;

import utility.actionBase.Action;

public class IntakeSubsystem extends Subsystem {
    private MotorEx intakeMotorTop;
    private MotorGroup intakeMotors;
    private MotorEx transferMotor;
    private ServoEx intakeServo;
    private DigitalChannel intakeBeamFront;
    private DigitalChannel intakeBeamBack;
    private DigitalChannel outtakeBeamTop;
    private DigitalChannel outtakeBeamBottom;
    private Supplier<Double> intakePower = () -> 0.0;
    private IntakeServoPose intakePose = IntakeServoPose.INIT;
    private int ballCount = 0;
    private Supplier<Boolean> intakeBeamState = () -> false;
    private Supplier<Boolean> outtakeBeamState = () -> false;
    private boolean wasLastDetected = false;
    private IntakeState intakeState = Separate;

    private static final IntakeSubsystem intake = new IntakeSubsystem();

    public static IntakeSubsystem intake() {
        return intake;
    }

    @Override
    public void hardwareInit() {
        intakeMotorTop = getDcMotorEx("intake");
        transferMotor = getDcMotorEx("transfer");


//        intakeServo = getServo("intake");

        intakeBeamFront = getDigitalChannel("intakeBeamFront", false);
        intakeBeamBack = getDigitalChannel("intakeBeamBack", false);
        outtakeBeamBottom = getDigitalChannel("outtakeBeamBack", false);
        outtakeBeamTop = getDigitalChannel("outtakeBeamBack", false);

        intakeMotorTop.setInverted(true);
        transferMotor.setInverted(false);
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
//        servoUpdate().schedule();
        beamUpdate().schedule();
        ballUpdate().schedule();
    }

    @Override
    public void stop() {

    }

    public void setIntakePower(Supplier<Double> power) {
        intakePower = power;
    }

    public void setTransfer(Supplier<Double> power){
        transferSpeed = power;
    }


    public double getIntakePower() {
        return intakePower.get();
    }


    public void setIntakePose(IntakeServoPose intakePose){
        this.intakePose = intakePose;
    }

    public IntakeServoPose getIntakePose(){
        return intakePose;
    }

    private Action servoUpdate(){
        return simply(() -> {
            intakeServo.set( servoDebug== -1 ? intakePose.pose.get() : servoDebug);
        });
    }

    private Action intakeUpdate(){
        return simply(() -> intakeMotors.set(getIntakePower()));
    }

    private Action beamUpdate(){
        return simply(() ->{
            intakeBeamState = () -> intakeBeamFront.getState() || intakeBeamBack.getState();
            outtakeBeamState = () -> outtakeBeamTop.getState() || outtakeBeamBottom.getState();
        });
    }

    private Action ballUpdate(){
        return simply(() ->{
            if(intakeBeamState.get() && ballCount <= 4){
                ballCount ++;
            }
        }).after(10);
    }

    public void empty(){
        ballCount = 0;
    }

    public boolean isEmpty(){
        return ballCount == 0 && !outtakeBeamState.get();
    }

    public boolean isFull(){
        return ballCount == 4;
    }

    public int getBallCount(){
        return ballCount;
    }

    public IntakeState getIntakeState(){
        return intakeState;
    }
}

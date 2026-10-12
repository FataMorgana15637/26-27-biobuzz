package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.servoDebug;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeState.*;
import static utility.actionbase.actions.Actions.simply;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.teamcode.fatamorgana.FataMain;
import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.FataOpMode;
import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;

import utility.actionbase.Action;

public class IntakeSubsystem extends Subsystem {
    private MotorEx intakeMotor;
    private MotorEx transferMotor;
    private ServoEx intakeServo;
    private DigitalChannel intakeBeamFront;
    private DigitalChannel intakeBeamBack;
    private DigitalChannel outtakeBeamTop;
    private DigitalChannel outtakeBeamBottom;
    private double intakePower = 0.0, transferPower = 0.0;
    private IntakeServoPose intakePose = IntakeServoPose.INIT;
    private int ballCount = 0;
    private boolean intakeBeamState = false, outtakeBeamState = false;
    private boolean lastResult = false;
    private boolean wasLastDetected = false;
    private IntakeState intakeState = Separate;
    private final ElapsedTime elapsedTime = new ElapsedTime();

    private static final IntakeSubsystem intake = new IntakeSubsystem();

    public static IntakeSubsystem intake() {
        return intake;
    }

    @Override
    public void hardwareInit() {
        intakeMotor = getDcMotorEx("intake");
        transferMotor = getDcMotorEx("transfer");

//        intakeServo = getServo("intake");

        intakeBeamFront = getDigitalChannel("intakeBeamFront");
        intakeBeamBack = getDigitalChannel("intakeBeamBack");
        outtakeBeamBottom = getDigitalChannel("outtakeBeamBack", false);
        outtakeBeamTop = getDigitalChannel("outtakeBeamBack", false);

        intakeMotor.setInverted(true);
        transferMotor.setInverted(true);
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
        telemtryUpdate().schedule();
    }

    @Override
    public void stop() {

    }

    public void setIntakePower(double power) {
        intakePower = power;
    }

    public void setTransferPower(double power) {
        transferPower = power;
    }

    public double getIntakePower() {
        return intakePower;
    }

    public double getTransferPower() {
        return transferPower;
    }

    public void setIntakePose(IntakeServoPose intakePose) {
        this.intakePose = intakePose;
    }

    public IntakeServoPose getIntakePose() {
        return intakePose;
    }

    private Action servoUpdate() {
        return simply(() -> intakeServo.set(servoDebug == -1 ?
                intakePose.pose.get() : servoDebug));
    }

    private Action intakeUpdate() {
        return simply(() -> {
            intakeMotor.set(getIntakePower());
            transferMotor.set(getTransferPower());
        });
    }

    private Action beamUpdate() {
        return simply(() -> {
            intakeBeamState = !intakeBeamFront.getState() || !intakeBeamBack.getState();
            if (intakeBeamState){
                if (elapsedTime.milliseconds() <= IntakeConstants.waitTime) {
                    intakeBeamState = false;
                    return;
                };

                if (!lastResult && intakeBeamState && ballCount < 4){
                    ballCount ++;
                }
                elapsedTime.reset();
            }
        });
    }
    public Action empty() {
        return simply(() -> ballCount = 0);
    }

    public boolean isEmpty() {
        return ballCount == 0;
    }

    public boolean isFull() {
        return ballCount == 4;
    }

    public int getBallCount() {
        return ballCount;
    }

    public IntakeState getIntakeState() {
        return intakeState;
    }

    public Action brakeTransfer() {
        return simply(() ->
                transferMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)
        );
    }
    public Action floatTransfer() {
        return simply(() ->
                transferMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT)
        );
    }

    private Action telemtryUpdate(){
        return simply(() -> {
            FataMain.getTelemetry().addData("intakeTarget", intakePower);
            FataMain.getTelemetry().addData("beamFrontState", !intakeBeamFront.getState());
            FataMain.getTelemetry().addData("beamBackState", !intakeBeamBack.getState());
            FataMain.getTelemetry().addData("ball count", ballCount);
        });
    }
}

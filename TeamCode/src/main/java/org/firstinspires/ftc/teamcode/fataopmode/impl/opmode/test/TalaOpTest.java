package org.firstinspires.ftc.teamcode.fataopmode.impl.opmode.test;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.fataopmode.FataMain;
import org.firstinspires.ftc.teamcode.fataopmode.api.opmode.FataOpMode;
import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.actions.IntakeActions;

import static org.firstinspires.ftc.teamcode.fataopmode.api.fataUtil.action.GamepadFactory.button;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.drive.DriveSubsystem.drive;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;
import static utility.actionBase.actions.Actions.perpetually;
import static utility.actionBase.actions.Actions.simply;

@TeleOp
@Configurable
public class TalaOpTest extends FataOpMode {
    public static double thing = 0;
    private DcMotor yuriMotor;
    @Override
    protected void onInit() {
        super.onInit();
//        yuriMotor = FataMain.getCurrentOpMode().hardwareMap.dcMotor.get("motor");

    }

    protected void onPlay() {
        super.onPlay();

//        button(() -> gamepad1.right_bumper)
    }
//
    protected void onLoop() {
        super.onLoop();
        simply(() -> FataMain.getTelemetry().addLine("name")).schedule();

        FataMain.getTelemetry().addData("config",TestConfig.configVal);
        FataMain.getTelemetry().addData("buttonTest", thing);

        drive().TeleOpDrive(
                gamepad1.left_stick_y / (gamepad1.right_trigger > 0.3 ? 4.5 : 1),
                gamepad1.left_stick_x / (gamepad1.right_trigger > 0.3 ? 4.5 : 1),
                -gamepad1.right_stick_x / (gamepad1.right_trigger > 0.3 ? 4.5 : 1)
        );
    }
}

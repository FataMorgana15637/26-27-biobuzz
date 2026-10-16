package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.talaop;

import static org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.gamepad.GamepadFactory.button;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.fatamorgana.FataMain;
import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.AllianceColour;
import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.FataOpMode;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions.RobotActions;

@Configurable
@TeleOp
public class BangpfCalibration extends FataOpMode {
    public static double target = 0.0;
    public static double targetB = 0.0;

    @Override
    protected void onInit() {
        FataMain.setAllianceColor(AllianceColour.BLUE);
    }

    @Override
    protected void onPlay() {
        button(() -> gamepad1.right_bumper).whenPressed(
                () -> yuri().setYuriTarget(() -> 1000.0)).create();

        button(() -> gamepad1.left_bumper).whenPressed(
                () -> yuri().setYuriTarget(() -> target)
        ).create();

        button(() -> gamepad1.triangle).whenPressed(
                () -> yuri().setYuriTarget(() -> targetB)
        ).create();

        button(() -> gamepad1.circle).whenPressed(() -> yuri().setPower(1)).create();
        button(() -> gamepad1.cross).whenPressed(() -> yuri().setPower(0)).create();
        button(() -> gamepad1.square).whenPressed(() -> RobotActions.separateTransfer()).create();

    }


}

package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.talaop;

import static org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.gamepad.GamepadFactory.button;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions.RobotActions.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretMode.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.turretactions.TurretActions.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions.*;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.FataOpMode;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions.RobotActions;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions.SeparateIntakeAction;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretMode;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.turretactions.TurretActions;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions.YuriActions;

public class TalaOp extends FataOpMode {
    @Override
    protected void onInit() {
        drive().setPose(new Pose(144, 72, 180));
    }
    @Override
    protected void initLoop() {

    }
    @Override
    protected void onPlay() {
        button(() -> gamepad1.right_bumper).whenPressed(() ->
                        setTurretMode(SCORE)
                                .also(separateTransfer())
        ).create();

        button(() -> gamepad1.left_bumper).whenPressed(() ->
                    setTurretMode(PASS)
                            .also(separateTransfer())
        ).create();

        button(() -> gamepad1.right_trigger > 0.3).whenPressed(() ->
                        separateIntake()
                );
    }
    @Override
    protected void onLoop() {

    }
    @Override
    protected void onStop() {

    }
}

package org.firstinspires.ftc.teamcode.fatamorgana.impl.opmode.talaop;

import static org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.gamepad.GamepadFactory.button;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions.RobotActions.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem.drive;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretMode.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretSubsystem.turret;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.turretactions.TurretActions.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState.*;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import static utility.actionbase.Action.empty;
import static utility.actionbase.actions.Actions.observe;

import com.pedropathing.math.Pose;

import org.firstinspires.ftc.teamcode.fatamorgana.api.opmode.FataOpMode;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.actions.RobotActions;

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
                turret().setTurretMode(SCORE)
                                .also(observe(
                                        () -> yuri().getYuriState() == SEPARATE,
                                        () -> separateTransfer(),
                                        () -> empty()
                                ))
        ).create();

        button(() -> gamepad1.left_bumper).whenPressed(() ->
                turret().setTurretMode(PASS)
                        .also(observe(
                                () -> yuri().getYuriState() == SEPARATE,
                                () -> separateTransfer(),
                                () -> empty()
                        ))
        ).whenReleased(() ->
                observe(
                        () -> yuri().getYuriState() == SEPARATE,
                        () -> empty(),
                        () -> turret().setTurretMode(SCORE)
                )).create();

        button(() -> gamepad1.right_trigger > 0.3).whenPressed(() ->
                        separateIntake()
                );

        button(() -> gamepad1.triangle).whenPressed(() ->
            yuri().setYuriState(CONTINUOUS)
                    .also(constantTransfer())
        );
        button(() -> gamepad1.circle).whenPressed(() ->
                yuri().setYuriState(SEPARATE)
                );
    }
    @Override
    protected void onLoop() {

    }
    @Override
    protected void onStop() {

    }
}

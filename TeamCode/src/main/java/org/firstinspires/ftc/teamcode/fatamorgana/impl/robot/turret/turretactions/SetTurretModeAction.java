package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.turretactions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretSubsystem.turret;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretMode;

import utility.actionbase.actions.SimpleAction;

public class SetTurretModeAction extends SimpleAction {
    SetTurretModeAction(TurretMode mode) {
        super(
                () -> turret().setTurretMode(mode)
        );
    }
}

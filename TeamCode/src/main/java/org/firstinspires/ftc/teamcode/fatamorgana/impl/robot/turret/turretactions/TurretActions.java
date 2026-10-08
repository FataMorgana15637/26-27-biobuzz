package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.turretactions;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretMode;

public class TurretActions {
    public static SetTurretModeAction setTurretMode(TurretMode mode){
        return new SetTurretModeAction(mode);
    }
}

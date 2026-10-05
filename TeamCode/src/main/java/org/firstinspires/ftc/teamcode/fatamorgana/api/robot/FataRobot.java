package org.firstinspires.ftc.teamcode.fatamorgana.api.robot;

import org.firstinspires.ftc.teamcode.fatamorgana.api.robot.hardware.Subsystem;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.turret.TurretSubsystem;
import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem;

public class FataRobot {
    private final Subsystem[] subsystems = {
           IntakeSubsystem.intake(),
            DriveSubsystem.drive(),
            YuriSubsystem.yuri(),
            TurretSubsystem.turret()
    };

    public void init() {
        for (Subsystem s : subsystems)
            s.hardwareInit();
        for (Subsystem s : subsystems) if (s.isEnabled()) s.opModeInit();

//        PhotonCore.CONTROL_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
//        PhotonCore.EXPANSION_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
//        PhotonCore.experimental.setMaximumParallelCommands(8);
//        PhotonCore.PARALLELIZE_SERVOS = false;
    }

    public void play() {
        for (Subsystem s : subsystems) if (s.isEnabled()) s.play();
    }

    public void loop() {
        for (Subsystem s : subsystems) if (s.isEnabled()) s.loop();
//        PhotonCore.CONTROL_HUB.clearBulkCache();
//        PhotonCore.EXPANSION_HUB.clearBulkCache();
    }

    public void stop() {
        for (Subsystem s : subsystems) if (s.isEnabled()) s.stop();
    }

}

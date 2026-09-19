package org.firstinspires.ftc.teamcode.fataopmode.api.robot;

import com.qualcomm.hardware.lynx.LynxModule;
import com.seattlesolvers.solverslib.photon.PhotonCore;
import org.firstinspires.ftc.teamcode.fataopmode.FataMain;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.hardware.Subsystem;

import java.util.List;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeSubsystem.intake;

public class FataRobot {
    private final Subsystem[] subsystems= {
        intake()
    };

    public void init() {
        for (Subsystem s : subsystems)
            s.hardwareInit();
        for (Subsystem s : subsystems) if (s.isEnabled()) s.opModeInit();

        PhotonCore.CONTROL_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
//        PhotonCore.EXPANSION_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        PhotonCore.experimental.setMaximumParallelCommands(8);
        PhotonCore.PARALLELIZE_SERVOS = false;
    }

    public void play() {
        for (Subsystem s : subsystems) if (s.isEnabled()) s.play();
    }

    public void loop() {
        for (Subsystem s : subsystems) if (s.isEnabled()) s.loop();
        PhotonCore.CONTROL_HUB.clearBulkCache();
//        PhotonCore.EXPANSION_HUB.clearBulkCache();
    }

    public void stop() {
        for (Subsystem s : subsystems) if (s.isEnabled()) s.stop();
    }

}

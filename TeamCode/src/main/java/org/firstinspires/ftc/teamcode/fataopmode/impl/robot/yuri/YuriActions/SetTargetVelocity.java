package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.nectarYuri.nectarYuriActions.NectarYuriActions.*;

import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.pollenShooter.pollenYuriActions.PollenYuriActions;

import java.util.function.Supplier;

import utility.actionBase.runners.SequentialActionRunner;

public class SetTargetVelocity extends SequentialActionRunner {

    SetTargetVelocity(Supplier<Double> velocity) {
        setNecterTargetVelocity(velocity).also(PollenYuriActions.setTargetVelocity(velocity));
    }
}

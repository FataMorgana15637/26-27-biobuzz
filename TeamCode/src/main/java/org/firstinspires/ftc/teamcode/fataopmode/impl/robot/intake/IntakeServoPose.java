package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.servoInit;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.servoIntake;
import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.intake.IntakeConstents.servoLift;

import java.util.function.Supplier;

public enum IntakeServoPose {
    INIT(() -> servoInit),
    INTAKE(() -> servoIntake),
    LIFT(() -> servoLift);
    public Supplier<Double> pose;
    IntakeServoPose(Supplier<Double> pose){
        this.pose = pose;
    }
}

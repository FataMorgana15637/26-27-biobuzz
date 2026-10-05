package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.servoInit;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.servoIntake;
import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.intake.IntakeConstants.servoUp;

import java.util.function.Supplier;

public enum IntakeServoPose {
    INIT(() -> servoInit),
    INTAKE(() -> servoIntake),
    UP(() -> servoUp);
    public Supplier<Double> pose;
    IntakeServoPose(Supplier<Double> pose){
        this.pose = pose;
    }
}

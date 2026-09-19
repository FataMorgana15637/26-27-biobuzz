package org.firstinspires.ftc.teamcode.fataopmode.api.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public enum OpModeType {
    AUTONOMOUS,
    TELEOP;

    public static OpModeType getOpModeTypeFor(FataOpMode opMode) {
        Class<? extends FataOpMode> asClass = opMode.getClass();

        if (asClass.isAnnotationPresent(Autonomous.class)) return AUTONOMOUS;
        if (asClass.isAnnotationPresent(TeleOp.class)) return TELEOP;

        throw new IllegalStateException("No OpModeType for class "
                + asClass.getName());

    }
}

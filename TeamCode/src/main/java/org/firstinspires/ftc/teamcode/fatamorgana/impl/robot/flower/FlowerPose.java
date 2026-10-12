package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.flower;

import java.util.function.Supplier;

public enum FlowerPose {

    INTAKE(() -> FlowerConstants.FlowerIntake),
    UP(() -> FlowerConstants.FlowerUp);


    public Supplier<Double> pose;
    FlowerPose(Supplier<Double> pose){
        this.pose = pose;
    }}

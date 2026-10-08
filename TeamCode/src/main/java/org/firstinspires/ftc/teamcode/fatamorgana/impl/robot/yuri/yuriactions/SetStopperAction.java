package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.StopperPose;

import utility.actionbase.actions.SimpleAction;

public class SetStopperAction extends SimpleAction {
    SetStopperAction(StopperPose stopperPose){
        super(
                () -> yuri().setStopperPose(stopperPose)
        );
    }
}

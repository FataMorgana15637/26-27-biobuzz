package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import static utility.actionbase.actions.Actions.simply;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.HoodPose;

import utility.actionbase.runners.SequentialActionRunner;

public class SetHoodAction extends SequentialActionRunner {
    SetHoodAction(HoodPose hoodPose){
        super(
                simply( () -> {
                    yuri().setHoodPose(hoodPose);
                })
        );
    }
}

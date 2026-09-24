package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriSubsystem.yuri;

import static utility.actionBase.actions.Actions.simply;

import org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.HoodPose;

import java.util.function.Supplier;

import utility.actionBase.runners.SequentialActionRunner;

public class SetHoodAction extends SequentialActionRunner {
    SetHoodAction(HoodPose hoodPose){
        super(
                simply( () -> {
                    yuri().setHoodPose(hoodPose);
                })
        );
    }
}

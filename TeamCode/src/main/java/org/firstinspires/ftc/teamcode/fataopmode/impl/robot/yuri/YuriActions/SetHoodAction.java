package org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriActions;

import static org.firstinspires.ftc.teamcode.fataopmode.impl.robot.yuri.YuriSubsystem.yuri;

import static utility.actionBase.actions.Actions.simply;

import java.util.function.Supplier;

import utility.actionBase.runners.SequentialActionRunner;

public class SetHoodAction extends SequentialActionRunner {
    SetHoodAction(Supplier<Double> target){
        super(
                simply( () -> {
                    yuri().setHoodTarget(target);
                })
        );
    }
}

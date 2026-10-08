package org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.yuriactions;

import static org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriSubsystem.yuri;

import org.firstinspires.ftc.teamcode.fatamorgana.impl.robot.yuri.YuriState;

import utility.actionbase.actions.SimpleAction;

public class SetYuriState extends SimpleAction {
    SetYuriState(YuriState yuriState){
        super(
                () -> yuri().setYuriState(yuriState)
        );
    }
}

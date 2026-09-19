package org.firstinspires.ftc.teamcode.fataopmode;

//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.fataopmode.api.fataUtil.MultipleTelemetry;
import org.firstinspires.ftc.teamcode.fataopmode.api.opmode.AllianceColour;
import org.firstinspires.ftc.teamcode.fataopmode.api.opmode.FataOpMode;
import org.firstinspires.ftc.teamcode.fataopmode.api.robot.FataRobot;
import utility.actionBase.ActionScheduler;
//import com.pedropathing.util.PoseHistory;

public class FataMain {
    private static FataOpMode currentOpMode;
    private static AllianceColour allianceColour;
    private static ActionScheduler actionScheduler = new ActionScheduler();
    private static final FataRobot robot = new FataRobot();

//    private static PoseHistory poseHistory;

    public static FataOpMode getCurrentOpMode() {
        return currentOpMode;
    }

    public static Telemetry getTelemetry() {
        return currentOpMode.telemetry;
    }

    public static Gamepad getGamepad1() {
        return currentOpMode.gamepad1;
    }

    public static Gamepad getGamepad2() {
        return currentOpMode.gamepad2;
    }

    public static AllianceColour getAllianceColor() {
        return allianceColour;
    }

    public static void setAllianceColor(AllianceColour allianceColour) {
        FataMain.allianceColour = allianceColour;
    }

    public static ActionScheduler getActionScheduler() {
        return actionScheduler;
    }

    public static void init(FataOpMode opMode){
        actionScheduler.clear();

        currentOpMode = opMode;

        currentOpMode.telemetry = new MultipleTelemetry(
                PanelsTelemetry.INSTANCE.getFtcTelemetry(),
                currentOpMode.telemetry
        );

        robot.init();

    }

    public static void initLoop(){
        PanelsTelemetry.INSTANCE.getTelemetry().update(currentOpMode.telemetry);
    }

    public static void play(){
        robot.play();
    }

    public static void loop(){
        actionScheduler.update();

        robot.loop();

//        PanelsTelemetry.INSTANCE.getTelemetry().update(currentOpMode.telemetry);
    }

    public static void stop(){
        actionScheduler.clear();

        robot.stop();

        currentOpMode = null;
    }

}

package org.firstinspires.ftc.teamcode.fataopmode.api.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.fataopmode.FataMain;

public class FataOpMode extends LinearOpMode {

    private final OpModeType type;
    private OpModeStage stage;
    private boolean endOpmode = false;

    public FataOpMode(){
        this.type = OpModeType.getOpModeTypeFor(this);
    }

    @Override
    public void runOpMode() throws InterruptedException {
//            INIT
        stage = OpModeStage.INIT;
        FataMain.init(this);
        init();
        onInit();

        while (opModeInInit()) {
//            INIT_LOOP
            stage = OpModeStage.INIT_LOOP;
            FataMain.initLoop();
            initLoop();
            FataMain.getTelemetry().update();
        }

        waitForStart();

        if(!isStopRequested()) {
//            PLAY
            stage = OpModeStage.PLAY;
            FataMain.play();
            onPlay();
        }

        while (opModeIsActive() && !isStopRequested() && ! endOpmode) {
//            LOOP
            stage = OpModeStage.LOOP;
            FataMain.loop();
            onLoop();
            FataMain.getTelemetry().update();
        }
        // STOP
        stage = OpModeStage.STOP;
        FataMain.stop();
        stop();
    }

    protected void onInit() {}

    protected void initLoop() {}

    protected void onPlay() {}

    protected void onLoop(){}

    protected void onStop() {}

    public OpModeStage getStage() {
        return stage;
    }

    public OpModeType getType() {
        return type;
    }

    public void endOpMode() {
        endOpmode = true;
    }
}

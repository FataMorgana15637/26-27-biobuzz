package utility.actionbase;

import org.firstinspires.ftc.teamcode.fatamorgana.FataMain;
import utility.actionbase.runners.ParallelActionRunner;
import utility.actionbase.runners.RaceActionRunner;
import utility.actionbase.runners.SequentialActionRunner;

import java.util.function.BooleanSupplier;

import static utility.actionbase.actions.Actions.*;


public interface Action {
     boolean isStarted();
     boolean isFinished();
     boolean isInterruptible();

     void start();
     void update();
     void stop();

     void setStarted(boolean started);

     void setFinished(boolean finished);

     default void interrupt() {
          if (isInterruptible()) {
               setFinished(true);
          }
     }

     default Action then(Action next){
          return new SequentialActionRunner(this, next);
     }

     default Action also(Action other){
          return new ParallelActionRunner(this, other);
     }

     default Action race(Action competition){
          return new RaceActionRunner(this, competition);
     }

     default Action timeout(int time){
          return new RaceActionRunner(this, delay(time));
     }

     default Action inturreptIf(BooleanSupplier condition){
          return new RaceActionRunner(this, waitUntil(condition));
     }

     default void schedule(){
          FataMain.getActionScheduler().schedule(this);
     }

     default Action after(int time){
          return delay(time).then(this);
     }

     static Action empty() {
          return simply(() -> {});
     }

}
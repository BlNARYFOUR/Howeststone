package game;

import formatters.ColorFormats;

import java.util.Timer;
import java.util.TimerTask;

public class TurnTimer {

    private Timer turnTimer;
    private int countDownTurnTimer;
    private int secondsLeftThisTurn;
    private boolean countdownTurnTimerFinished;
    private boolean isRunning = false;

    public TurnTimer() {
        turnTimer = new Timer();
    }

    public void startTurnTimer() {
        secondsLeftThisTurn = countDownTurnTimer;
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeftThisTurn--;
                if (secondsLeftThisTurn == 0) {
                    turnTimer.cancel();
                    setFlag();
                }
            }
        }, 0, 1000);
    }

    public void startTurnTimer(Runnable runnable) {
        secondsLeftThisTurn = countDownTurnTimer;
        isRunning = true;
        turnTimer = new Timer();
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeftThisTurn--;

                if(secondsLeftThisTurn%10 == 0)
                    System.out.println(ColorFormats.magenta(Integer.toString(secondsLeftThisTurn)));
                
                if (secondsLeftThisTurn == 0) {
                    turnTimer.cancel();
                    setFlag();

                    runnable.run();
                }
            }
        }, 0, 1000);
    }

    private void setFlag() {
        countdownTurnTimerFinished = true;
    }

    public void setCountDownTurnTimer(int seconds) {
        this.countDownTurnTimer = seconds;
    }

    public int getSecondsLeftThisTurn() {
        return secondsLeftThisTurn;
    }

    public boolean done() {
        return countdownTurnTimerFinished;
    }

    public void stop() {
        if(isRunning)
            turnTimer.cancel();

        isRunning = false;
    }
}

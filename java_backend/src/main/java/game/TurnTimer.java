package game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class TurnTimer {

    private Timer turnTimer;
    private int countDownTurnTimer;
    private int secondsLeftThisTurn;
    private boolean countdownTurnTimerFinished;

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

    public void startTurnTimer(Method method) {
        final Object[] paramsObj = {};

        secondsLeftThisTurn = countDownTurnTimer;
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeftThisTurn--;
                if (secondsLeftThisTurn == 0) {
                    turnTimer.cancel();
                    setFlag();
                    try {
                        final Object iClass = method.getClass().newInstance();
                        method.invoke(iClass, paramsObj);
                    } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
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
        turnTimer.cancel();
    }
}

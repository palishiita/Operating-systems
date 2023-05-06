package Memory;

public class Timer {

    private int time;
    // getter
    public int getTime() {
        return time;
    }

    public void resetTime() {
        time = 0;
    }
    // constructor
    private Timer() {
        resetTime();
    }

    private static Timer timerInstance = null;
    public static Timer getInstance() {
        if (timerInstance == null) {
            timerInstance = new Timer();
        }
        return timerInstance;
    }

    public void increaseTime(int timePeriod) {
        time = time + timePeriod;
    }


}

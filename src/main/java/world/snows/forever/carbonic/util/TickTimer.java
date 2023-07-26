package world.snows.forever.carbonic.util;

public class TickTimer {
    private final int delay;
    private long currentTime;

    public TickTimer(int ticks) {
        this.delay = ticks * 10;
        this.currentTime = System.currentTimeMillis();
    }

    public boolean isElapsed() {
        return System.currentTimeMillis() > this.currentTime + this.delay;
    }

    public void reset() {
        this.currentTime = System.currentTimeMillis();
    }
}

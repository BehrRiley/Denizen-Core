package com.denizenscript.denizencore.utilities.scheduling;

public class RepeatingSchedulable extends Schedulable {

    public RepeatingSchedulable(Runnable runnable, float fireRate) {
        run = runnable;
        if (fireRate > 0 && fireRate < 0.05f) {
            fireRate = 0.05f;
        }
        fireEverySeconds = fireRate;
        secondsLeft = fireRate;
    }

    public float fireEverySeconds;

    @Override
    public boolean tick(float seconds) {
        if (cancelled) {
            return false;
        }
        secondsLeft -= seconds;
        if (secondsLeft <= 0) {
            run.run();
            secondsLeft += fireEverySeconds;
        }
        return true;
    }
}

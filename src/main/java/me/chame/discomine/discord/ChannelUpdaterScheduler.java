package me.chame.discomine.discord;

import me.chame.discomine.utils.*;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

/**
 * This class should provide updates to the channel description after a fixed
 * amount of time, it could also be used to stop JDA after the MixIn call to
 * setServerStopping().
 */

// TODO
public class ChannelUpdaterScheduler {
    public static void beepAfterInterval() {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Example code.
        final ScheduledFuture<?> beeper = scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                System.out.println("Beep");

            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
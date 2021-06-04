package me.chame.discomine.discord;

import me.chame.discomine.utils.*;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
//TODO
public class ChannelUpdaterScheduler {
    public static void beepAfterInterval() {
        //initialize the scheduler service
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //schedule a task to execute after every 5 seconds
        final ScheduledFuture<?> beeper = scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            // this is just a sample. Any repeatitive task such as connection
                        // health monitoring can be done here
                System.out.println("Beep");
    
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
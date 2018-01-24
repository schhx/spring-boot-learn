package org.schhx.springbootlearn.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedDelay = 5000)
    public void now() {
        System.out.println("now: " + format.format(new Date()));
    }

    @Scheduled(fixedRate = 10000)
    public void now2() {
        System.out.println("now2: " + format.format(new Date()));
    }

    @Scheduled(cron = "0/15 * * * * ?")
    public void now3() {
        System.out.println("now3: " + format.format(new Date()));
    }
}

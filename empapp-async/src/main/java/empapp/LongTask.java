package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LongTask {

    @Async
    public void doLongTask() {
        try {
            Thread.sleep(10_000);
            log.info("Long task end: {}", Thread.currentThread().getName());
        } catch (InterruptedException ie) {
            throw new IllegalStateException("Interrupted", ie);
        }
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void doInLoop() {
        log.info("Scheduled task {}", Thread.currentThread().getName());
    }
}

package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggerEventHandler {

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        log.info("Event has come");
    }
}

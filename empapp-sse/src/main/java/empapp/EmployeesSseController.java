package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@AllArgsConstructor
public class EmployeesSseController {

    private final List<SseEmitter> emitters = new ArrayList<>();

    @GetMapping("/api/employees/messages")
    public SseEmitter getMessages() {
        log.info("getMessages" + Thread.currentThread().getName());
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        try {
            emitter.send("Connected");
        } catch (IOException ioe) {
            log.error("Error", ioe);
        }
        // Ciklusban, késleltetetten
        return emitter;
    }

    @EventListener
    public void handleEvent(EmployeeHasCreatedEvent event) {
        List<SseEmitter> badEmitters = new ArrayList<>();
        for (SseEmitter emitter: emitters) {
            try {

                SseEmitter.SseEventBuilder builder = SseEmitter.event()
                        .name("created-message")
                        .comment("Employee has created")
                        .id(UUID.randomUUID().toString())
                        .reconnectTime(10_000)
                        // JSON marshal
                        .data(event);

                emitter.send(builder);
            } catch (Exception ioe) {
                log.info("Emitter has died");
                badEmitters.add(emitter);
            }
        }
        emitters.removeAll(badEmitters);
    }

    // @Scheduled(fixedRate = 2000)
    public void sendMessages() {
        List<SseEmitter> badEmitters = new ArrayList<>();
        for (SseEmitter emitter: emitters) {
            try {
                emitter.send("scheduled");
            } catch (Exception ioe) {
                log.info("Emitter has died");
                badEmitters.add(emitter);
            }
        }
        emitters.removeAll(badEmitters);
    }

}

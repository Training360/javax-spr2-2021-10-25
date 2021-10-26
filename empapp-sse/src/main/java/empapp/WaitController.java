package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class WaitController {

    @GetMapping("/wait")
    public String getWait() {
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException ioe) {
            log.error("error", ioe);
        }
        return "hello";
    }
}

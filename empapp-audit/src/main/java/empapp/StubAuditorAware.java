package empapp;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class StubAuditorAware implements AuditorAware<String> {

    private Random random = new Random();

        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of("admin " + random.nextInt(100));
            // SecurityContextHolder.getSecu...
        }
}
